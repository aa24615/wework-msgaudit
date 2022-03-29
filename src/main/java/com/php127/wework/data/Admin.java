package com.php127.wework.data;

public class Admin {
    private int id;
    private String username;
    private int state;

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
