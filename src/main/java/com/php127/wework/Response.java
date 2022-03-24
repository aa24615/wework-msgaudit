package com.php127.wework;

public class Response {

    private int code;
    private String msg;
    private Object data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public static Object error(String msg) {
        Response response = new Response();
        response.setCode(0);
        response.setMsg(msg);
        return response;
    }

    public static Object error(String msg, int code) {
        Response response = new Response();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static Object success(Object data) {
        Response response = new Response();
        response.setCode(1);
        response.setMsg("ok");
        response.setData(data);
        return response;
    }
}
