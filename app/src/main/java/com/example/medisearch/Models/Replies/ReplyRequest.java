package com.example.medisearch.Models.Replies;

import com.google.gson.annotations.SerializedName;

public class ReplyRequest {
    @SerializedName("user_id")
    private int userId;

    @SerializedName("comment_id")
    private int commentId;

    @SerializedName("body")
    private String body;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
