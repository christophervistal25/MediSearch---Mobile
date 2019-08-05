package com.example.medisearch.Models.User;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserStoreCommentResponse {

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("id")
    @Expose
    private Integer id;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}