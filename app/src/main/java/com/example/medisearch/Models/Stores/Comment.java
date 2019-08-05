package com.example.medisearch.Models.Stores;

import com.example.medisearch.Models.Replies.Reply;
import com.example.medisearch.Models.User.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comment {

    @SerializedName("id")
    @Expose
    private Integer id;

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

    @SerializedName("replies")
    @Expose
    private List<Reply> replies = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

}
