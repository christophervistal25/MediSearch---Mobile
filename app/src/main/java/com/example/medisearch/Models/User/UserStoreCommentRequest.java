package com.example.medisearch.Models.User;

import com.google.gson.annotations.SerializedName;

public class UserStoreCommentRequest {

    @SerializedName("store_id")
    private long store_id;

    @SerializedName("user_id")
    private int user_id;

    @SerializedName("body")
    private String body;

    public long getStore_id() {
        return store_id;
    }

    public void setStore_id(long store_id) {
        this.store_id = store_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
