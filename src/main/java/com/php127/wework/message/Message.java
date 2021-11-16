/*
 * This file is part of the zyan/wework-msgaudit.
 *
 * (c) 读心印 <aa24615@qq.com>
 *
 * This source file is subject to the MIT license that is bundled
 * with this source code in the file LICENSE.
 */

package com.php127.wework.message;

import com.php127.wework.DB;
import com.tencent.wework.Finance;
import com.php127.wework.utils.RSAEncrypt;
import com.php127.wework.utils.Audio;
import org.json.JSONObject;
import org.json.JSONArray;

import org.springframework.util.StringUtils;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.*;

public class Message {

    public String prikey = null;
    public String corpid = null;
    public long seqs = 0;
    public long sdk;

    public String tableName = null;

    public Message(String corpid, String secret, String prikey){

        this.sdk = Finance.NewSdk();

        this.corpid = corpid;
        this.tableName = "message_"+this.corpid;
        int state = Finance.Init(sdk,corpid,secret);
        System.out.println("状态:"+state);
        this.prikey = prikey;
    }


    //解密
    public String decryptData(String encrypt_random_key, String encrypt_msg){

        try {

            String encrypt_key = RSAEncrypt.decrypt(encrypt_random_key,this.prikey);

            long message = Finance.NewSlice();
            int ret = Finance.DecryptData(this.sdk,encrypt_key, encrypt_msg, message);
            if (ret != 0) {
                System.out.println("解密失败:" + ret);
                return "";
            }

            String text =  Finance.GetContentFromSlice(message);
            Finance.FreeSlice(message);

            return text;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public long getSeq(){

        if(this.seqs>0){
            System.out.println("大于0");
            return this.seqs;
        }

        String sql = String.format("SELECT count(*) FROM %s",this.tableName);

        int count = DB.getJdbcTemplate().queryForObject(sql,Integer.class);
        if(count>0){
            sql = String.format("SELECT seq FROM %s order by seq desc LIMIT 1",this.tableName);
            long seq = DB.getJdbcTemplate().queryForObject(sql,Integer.class);
            System.out.println("初始seq:"+seq);
            this.seqs = seq;
            return seq;
        }

        return  0;
    }
    //获取列表
    public void getList() throws Exception{


        System.out.println("======================================");


        long seqs = this.getSeq();
        int limit = 1000;
        long slice = Finance.NewSlice();
        System.out.println("起始数:"+seqs);
        int ret = Finance.GetChatData(this.sdk, seqs, limit, "", "", 100, slice);
        if (ret != 0) {
            System.out.println("失败:" + ret);
            return;
        }

        String json = Finance.GetContentFromSlice(slice);
        JSONObject jo = new JSONObject(json);

        String errmsg = jo.getString("errmsg");
        int errcode = jo.getInt("errcode");

//            System.out.println("原始:"+json);

        if(errcode==0){
            System.out.println("获取成功:"+errmsg);
            JSONArray chatdata = jo.getJSONArray("chatdata");
            System.out.println("消息数:"+chatdata.length());
            for (int i = 0; i < chatdata.length(); i++) {
                String item = chatdata.get(i).toString();
                JSONObject data = new JSONObject(item);
                String encrypt_random_key = data.getString("encrypt_random_key");
                String encrypt_chat_msg = data.getString("encrypt_chat_msg");
                long seq = data.getLong("seq");
//                    System.out.println("密钥:"+encrypt_random_key);
//                    System.out.println("密文:"+encrypt_chat_msg);
                String message = this.decryptData(encrypt_random_key,encrypt_chat_msg);
                System.out.println("消息内容:"+message);
                if(this.saveMessage(seq,message)){
                    if(this.seqs<seq){
                        this.seqs=seq;
                    }
                }
            }
        }else{
            System.out.println("获取失败:"+this.corpid);
            System.out.println("errcode:"+errcode+":"+errmsg);
            throw new Exception("获取失败");
        }

        //关闭
        Finance.FreeSlice(slice);

    }


    //保存消息
    public boolean saveMessage(long seq,String message){

        System.out.println("----------------------------------");

        JSONObject json = new JSONObject(message);

        String msgfrom = "";
        String roomid = "";
        String msgtype = "";
        String msgdata = "";
        long msgtime = 0;
        String tolist = "";
        String sdkfield = "";
        String text = "";
        String ext = "";

        String msgid = json.getString("msgid");
        String action = json.getString("action");

        try {
            msgtime = json.getLong("msgtime");
        }catch (Exception e){
            msgtime = json.getLong("time");
        }
        if(msgtime<2000000000){
            msgtime = msgtime*1000;
        }

        try {
            msgfrom = json.getString("from");
        }catch (Exception e){

        }

        try {
            roomid = json.getString("roomid");
        }catch (Exception e){

        }

        try {
            msgtype = json.getString("msgtype");
        }catch (Exception e){

        }


        if(!msgtype.equals("")) {
            try {
                JSONObject content;
                if(msgtype.equals("docmsg")){
                    content = json.getJSONObject("doc");
                }else if(msgtype.equals("external_redpacket")){
                    content = json.getJSONObject("redpacket");
                }else{
                    content = json.getJSONObject(msgtype);
                }

                if(msgtype.equals("text")) {
                    text = content.getString("content");
                }

                try {
                    sdkfield = content.getString("sdkfileid");
                }catch (Exception e){
                    System.out.println("sdkfield获取失败");
                }

                try {
                    msgdata = content.toString();
                }catch (Exception e){
                    System.out.println("data获取失败");
                }

                if(msgtype.equals("image")){
                    ext = "png";
                }

                if(msgtype.equals("meeting_voice_call")){
                    ext = "mp4";
                }

                if(msgtype.equals("emotion")){
                    int type = content.getInt("type");
                    if(type==1){
                        ext = "gif";
                    }

                    if(type==2){
                        ext = "png";
                    }

                }

                if(msgtype.equals("file")){
                    String fileext = content.getString("fileext");
                    ext = fileext;
                }

            }catch (Exception e){
                System.out.println("获取失败:"+e.toString());
            }

        }

        //接收人 [user1,user2...]
        try {
            JSONArray tolistList = json.getJSONArray("tolist");
            int len = tolistList.length();
            String[] tolistArray = new String[len];
            for (int i = 0; i < len; i++) {
                tolistArray[i] = tolistList.get(i).toString();
            }
            tolist = StringUtils.arrayToCommaDelimitedString(tolistArray);
        }catch (Exception e){
            //System.out.println("失败:"+e.getMessage());
        }


        System.out.println("seq:"+seq);
//        System.out.println("msgid:"+msgid);
//        System.out.println("action:"+action);
//        System.out.println("from:"+from);
//        System.out.println("tolist:"+tolist);
//        System.out.println("roomid:"+roomid);
//        System.out.println("msgtime:"+msgtime);
//        System.out.println("msgtype:"+msgtype);
//        System.out.println("text:"+text);
//        System.out.println("sdkfileid:"+sdkfileid);
        System.out.println("data:"+msgdata);
//        System.out.println("msgdata:"+Base64Coded.encode(data));


        String media_path = "";

        if(!sdkfield.equals("")){
            media_path =  "/msgfile/"+this.corpid+"/"+seq+"."+ext;
        }

        Date Now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String created = ft.format(Now);

        //入库啦
        String sql;
        sql = String.format("SELECT count(*) FROM %s WHERE msgid='%s'",this.tableName,msgid);

        if(DB.getJdbcTemplate().queryForObject(sql,Integer.class)==0){
            sql = String.format("INSERT INTO %s " +
                    "(msgid,seq,`action`,msgfrom,tolist,roomid,msgtime,msgtype,text,sdkfield,msgdata,created,media_path) " +
                    "VALUES " +
                    "(?,?,?,?,?,?,?,?,?,?,?,?,?)",this.tableName);
            try {
                int res = DB.getJdbcTemplate().update(sql,msgid,seq,action,msgfrom,tolist,roomid,msgtime,msgtype,text,sdkfield,msgdata,created,media_path);
                System.out.println("插入状态:"+res);
                if(res>=1){

                    if(!sdkfield.equals("")){
                        this.downMedia(sdkfield,media_path, ext);
                    }
                    return true;
                }
                return false;
            }catch (Exception e){
                System.out.println("插入失败:"+e.getMessage());
                return false;
            }

        }else{
            System.out.println("已存在:"+msgid);
            return true;
        }

    }


    public void downMedia(String sdkField,String media_path, String ext){
        System.out.println("下载附件");
        String indexbuf = "";
        while(true) {
            long media_data = Finance.NewMediaData();
            int ret = Finance.GetMediaData(this.sdk, indexbuf, sdkField, "", "", 60, media_data);
            if (ret != 0) {
                System.out.println("获取失败");
                return;
            }
            System.out.printf("getmediadata outindex len:%d, data_len:%d, is_finis:%d\n", Finance.GetIndexLen(media_data), Finance.GetDataLen(media_data), Finance.IsMediaDataFinish(media_data));
            try {
                FileOutputStream outputStream = new FileOutputStream(new File("."+media_path), true);
                outputStream.write(Finance.GetData(media_data));
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (Finance.IsMediaDataFinish(media_data) == 1) {
                Finance.FreeMediaData(media_data);
                String sql = String.format("UPDATE %s SET media_code=1 WHERE sdkfield=?",this.tableName);
                DB.getJdbcTemplate().update(sql,sdkField);
                System.out.println("获取结束");

                if(ext.equals("amr")){
                    try {
                        Audio.toMp3("."+media_path,"."+media_path+".mp3");
                    }catch (Exception e){

                    }
                }

                break;
            } else {
                indexbuf = Finance.GetOutIndexBuf(media_data);
                Finance.FreeMediaData(media_data);
            }
        }
    }
}
