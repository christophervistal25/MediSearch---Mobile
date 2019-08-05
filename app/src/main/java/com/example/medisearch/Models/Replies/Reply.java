package com.example.medisearch.Models.Replies;

import com.example.medisearch.Models.User.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reply {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("comment_id")
    @Expose
    private String commentId;

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("user")
    @Expose
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}