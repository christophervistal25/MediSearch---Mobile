package com.example.medisearch.APIsInterface.Store;

import com.example.medisearch.Models.Replies.ReplyRequest;
import com.example.medisearch.Models.Replies.ReplyResponse;
import com.example.medisearch.Models.Stores.Comment;
import com.example.medisearch.Models.User.UserStoreCommentRequest;
import com.example.medisearch.Models.User.UserStoreCommentResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Comments {

    @GET("store/{id}/comments")
    Call<List<Comment>> getStoreComments(@Path("id") long storeId);

    @POST("store/comment")
    Call<UserStoreCommentResponse> addComment(@Body UserStoreCommentRequest userStoreCommentRequest);

    @POST("comment/reply")
    Call<ReplyResponse> addReply(@Body ReplyRequest replyRequest);

}
