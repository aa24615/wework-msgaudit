package com.php127.wework.service;

public class BaseService {

    protected String errorMessage = "";

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
