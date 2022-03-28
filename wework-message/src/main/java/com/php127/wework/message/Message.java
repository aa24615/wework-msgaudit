/*
 * This file is part of the zyan/wework-msgaudit.
 *
 * (c) 读心印 <aa24615@qq.com>
 *
 * This source file is subject to the MIT license that is bundled
 * with this source code in the file LICENSE.
 */

package com.php127.wework.message;

import com.tencent.wework.Finance;
import com.php127.wework.utils.RSAEncrypt;

import org.json.JSONObject;
import org.json.JSONArray;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.*;

public class Message {

    protected String prikey = null;
    protected String corpid = null;
    protected String proxy = "";
    protected String passwd = "";
    protected long seq = 0;
    protected long timeout = 60;
    protected long sdk;

    protected void setProxy(String proxy) {
        this.proxy = proxy;
    }

    protected void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    protected void setSdk(long sdk) {
        this.sdk = sdk;
    }

    protected void setPrikey(String prikey) {
        this.prikey = prikey;
    }

    protected void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    protected void setTimeout(long timeout) {
        this.timeout = timeout;
    }


    public Message(String corpid, String secret, String prikey, String proxy, String passwd, long timeout) {
        this.sdk = Finance.NewSdk();

        this.setCorpid(corpid);
        this.setPasswd(passwd);
        this.setProxy(proxy);
        this.setTimeout(timeout);
        this.setPrikey(prikey);

        int state = Finance.Init(this.sdk, corpid, secret);

        if (state != 0) {
            try {
                throw new Exception("初始化失败");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //解密
    protected String decryptData(String encrypt_random_key, String encrypt_msg) {

        try {

            String encrypt_key = RSAEncrypt.decrypt(encrypt_random_key, this.prikey);

            long message = Finance.NewSlice();
            int ret = Finance.DecryptData(this.sdk, encrypt_key, encrypt_msg, message);
            if (ret != 0) {
                System.out.println("解密失败:" + ret);
                return "";
            }

            String text = Finance.GetContentFromSlice(message);
            Finance.FreeSlice(message);

            return text;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    //获取列表
    public Object[] getList(long seq, int limit) throws Exception {


        long slice = Finance.NewSlice();
        int ret = Finance.GetChatData(this.sdk, seq, limit, this.proxy, this.passwd, this.timeout, slice);
        if (ret != 0) {
            throw new Exception("获取失败");
        }

        String json = Finance.GetContentFromSlice(slice);
        JSONObject data = new JSONObject(json);

        String errmsg = data.getString("errmsg");
        int errcode = data.getInt("errcode");


        if (errcode != 0) {
            System.out.println("获取失败:" + this.corpid);
            System.out.println("errcode:" + errcode + ":" + errmsg);
            throw new Exception("获取失败");

        }


        System.out.println("获取成功:" + errmsg);
        JSONArray chatdata = data.getJSONArray("chatdata");

        System.out.println("消息数:" + chatdata.length());


        Object[] list = new Object[chatdata.length()];

        for (int i = 0; i < chatdata.length(); i++) {

            String items = chatdata.get(i).toString();
            JSONObject item = new JSONObject(items);
            String encrypt_random_key = item.getString("encrypt_random_key");
            String encrypt_chat_msg = item.getString("encrypt_chat_msg");
            long publickey_ver = item.getLong("publickey_ver");
            String msgid = item.getString("msgid");

            String messageData = this.decryptData(encrypt_random_key, encrypt_chat_msg);

            list[i] = this.getMessage(msgid, seq, publickey_ver, messageData);
        }

        //关闭
        Finance.FreeSlice(slice);

        return list;
    }

    //保存消息
    public Object getMessage(String msgid, long seq, long publickey_ver, String message) {


        JSONObject json;

        Data data = new Data();

        data.setMsgid(msgid);
        data.setSeq(seq);

        try {
            json = new JSONObject(message);
        } catch (Exception e) {
            return data;
        }

        try {
            data.setAction(json.getString("action"));
        } catch (Exception ignored) {

        }

        long msgtime = 0;
        try {
            msgtime = json.getLong("msgtime");
        } catch (Exception e) {
            try {
                msgtime = json.getLong("time");
            } catch (Exception ignored) {

            }
        }

        if (msgtime < 2000000000) {
            msgtime = msgtime * 1000;
        }

        data.setMsgtime(msgtime);


        try {
            data.setMsgfrom(json.getString("from"));
        } catch (Exception ignored) {

        }

        try {
            data.setRoomid(json.getString("roomid"));
        } catch (Exception ignored) {

        }

        //接收人 [user1,user2...]
        try {
            JSONArray tolistList = json.getJSONArray("tolist");
            int len = tolistList.length();
            String[] tolistArray = new String[len];
            for (int i = 0; i < len; i++) {
                tolistArray[i] = tolistList.get(i).toString();
            }
            String tolist = "";

            tolist = StringUtils.join(tolistArray, ",");
            data.setTolist(tolist);
        } catch (Exception e) {
            //System.out.println("失败:"+e.getMessage());
        }

        String media_path = "";


        String sdkfileid = this.getSdkfileid(json);

        data.setSdkfileid(sdkfileid);

        data.setMsgdata(this.getMsgdata(json));

        data.setMsgtype(this.getMsgType(json));

        data.setText(this.getText(json));

        if (!sdkfileid.equals("")) {

            String ext = this.getExt(json);

            media_path = "./msgfile/" + this.corpid + "/" + seq + "." + ext;

            this.downMedia(sdkfileid, media_path);
        }

        data.setMediaPath(media_path);

        return data;
    }


    protected String getExt(JSONObject data) {
        String ext = "";
        String msgtype = "";

        try {
            msgtype = data.getString("msgtype");
        } catch (Exception ignored) {

        }

        if (!msgtype.equals("")) {
            try {
                JSONObject content = this.getContent(data);

                //图片
                if (msgtype.equals("image")) {
                    ext = "png";
                }
                //视频
                if (msgtype.equals("video")) {
                    ext = "mp4";
                }
                //语音
                if (msgtype.equals("voice")) {
                    ext = "amr";
                }
                //语音通话
                if (msgtype.equals("meeting_voice_call")) {
                    ext = "mp4";
                }
                //表情
                if (msgtype.equals("emotion")) {

                    int type = content.getInt("type");
                    if (type == 1) { //动态表情
                        ext = "gif";
                    }
                    if (type == 2) { //静态表情
                        ext = "png";
                    }
                }
                //文件
                if (msgtype.equals("file")) {
                    ext = content.getString("fileext");
                }
            } catch (Exception ignored) {
            }

        }

        return ext;
    }


    protected JSONObject getContent(JSONObject data) {

        String msgtype = this.getMsgType(data);
        JSONObject content;

        if (msgtype.equals("docmsg")) {
            content = data.getJSONObject("doc");
        } else if (msgtype.equals("external_redpacket")) {
            content = data.getJSONObject("redpacket");
        } else {
            content = data.getJSONObject(msgtype);
        }

        return content;
    }

    protected String getMsgType(JSONObject data) {

        String msgtype = "";

        try {
            msgtype = data.getString("msgtype");
        } catch (Exception ignored) {

        }

        return msgtype;
    }

    protected String getText(JSONObject data) {

        JSONObject content = this.getContent(data);
        String msgtype = this.getMsgType(data);

        String text = "";

        if (msgtype.equals("text")) {
            text = content.getString("content");
        }

        return text;

    }

    protected String getSdkfileid(JSONObject data) {
        JSONObject content = this.getContent(data);

        String sdkfileid = "";

        try {
            sdkfileid = content.getString("sdkfileid");
        } catch (Exception ignored) {

        }

        return sdkfileid;

    }


    public String getMsgdata(JSONObject data) {

        JSONObject content = this.getContent(data);
        String msgdata = "";
        try {
            msgdata = content.toString();
        } catch (Exception ignored) {
        }

        return msgdata;
    }


    public String downMedia(String sdkField, String media_path) {
        String indexbuf = "";
        while (true) {
            long media_data = Finance.NewMediaData();
            int ret = Finance.GetMediaData(this.sdk, indexbuf, sdkField, this.proxy, this.passwd, this.timeout, media_data);
            if (ret != 0) {
                return "";
            }

            try {
                FileOutputStream outputStream = new FileOutputStream(new File(media_path), true);
                outputStream.write(Finance.GetData(media_data));
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (Finance.IsMediaDataFinish(media_data) == 1) {
                Finance.FreeMediaData(media_data);
                break;
            } else {
                indexbuf = Finance.GetOutIndexBuf(media_data);
                Finance.FreeMediaData(media_data);
            }
        }
        return media_path;
    }
}
