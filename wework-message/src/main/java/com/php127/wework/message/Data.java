package com.php127.wework.message;

import org.json.JSONObject;

public class Data {

    protected String msgid = "";
    protected String msgfrom = "";
    protected String roomid = "";
    protected String msgtype = "";
    protected String msgdata = "";
    protected long msgtime = 0;
    protected long seq = 0;
    protected String tolist = "";
    protected String sdkfileid = "";
    protected String text = "";
    protected String action = "";
    protected String media_path = "";

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public void setMediaPath(String media_path) {
        this.media_path = media_path;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setMsgdata(String msgdata) {
        this.msgdata = msgdata;
    }

    public void setMsgfrom(String msgfrom) {
        this.msgfrom = msgfrom;
    }

    public void setMsgtime(long msgtime) {
        this.msgtime = msgtime;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public void setSdkfileid(String sdkfileid) {
        this.sdkfileid = sdkfileid;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTolist(String tolist) {
        this.tolist = tolist;
    }


    public Object getData(){
        return this;
    }
}
