package com.example.medisearch.WebService.User;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLoginResponse {
    @SerializedName("login")
    @Expose
    private boolean login;

    @SerializedName("message")
    @Expose
    private String message;

    public void setLogin(boolean login) {
        this.login = login;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isLogin() {
        return login;
    }

    public String getMessage() {
        return message;
    }

}
