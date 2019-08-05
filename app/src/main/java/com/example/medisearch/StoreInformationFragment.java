package com.example.medisearch;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medisearch.APIsInterface.Store.Comments;
import com.example.medisearch.Models.Replies.ReplyRequest;
import com.example.medisearch.Models.Replies.ReplyResponse;
import com.example.medisearch.Models.Service;
import com.example.medisearch.Models.Stores.Comment;
import com.example.medisearch.Models.Replies.Reply;
import com.example.medisearch.Models.User.UserStoreCommentRequest;
import com.example.medisearch.Models.User.UserStoreCommentResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreInformationFragment extends Fragment {


    public StoreInformationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        long storeId = bundle.getLong("store_id", 1);
        String storeName = bundle.getString("store_name", "STORE_NAME");
        String storeAddress = bundle.getString("store_address", "STORE_ADDRESS");

        TextView txtStoreName = view.findViewById(R.id.storeName);
        TextView txtStoreAddress = view.findViewById(R.id.storeAddress);
        EditText txtComment = view.findViewById(R.id.userComment);
        EditText txtReply = view.findViewById(R.id.userReply);
        Button btnWriteComment = view.findViewById(R.id.btnAddComment);
        Button btnWriteReply = view.findViewById(R.id.btnAddReply);

        btnWriteComment.setOnClickListener(v -> this.requestAddComment(storeId, txtComment, btnWriteComment));

        btnWriteReply.setOnClickListener(v -> {
            btnWriteReply.setEnabled(false);
            Retrofit retrofit           = Service.RetrofitInstance(getContext());
            Comments services             = retrofit.create(Comments.class);
            ReplyRequest replyRequest = new ReplyRequest();

            // TODO change the user id dynamically.
            replyRequest.setUserId(1);
            // TODO change the comment id dynamically.
            replyRequest.setCommentId(1);
            replyRequest.setBody(txtReply.getText().toString());

            Call<ReplyResponse> replyResponseCall = services.addReply(replyRequest);


            replyResponseCall.enqueue(new Callback<ReplyResponse>() {
                @Override
                public void onResponse(Call<ReplyResponse> call, Response<ReplyResponse> response) {
                    if (response.isSuccessful() && response.code() == 201) {

                        // Clear the Textfield
                        txtReply.setText(null);

                        btnWriteReply.setEnabled(true);

                        // Push the comment to display.
                        Toast.makeText(getContext(), "Succesfully add your reply.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ReplyResponse> call, Throwable t) {
                    btnWriteReply.setEnabled(true);
                }
            });
        });



        this.getComments(storeId, storeName, storeAddress, txtStoreName, txtStoreAddress);




        super.onViewCreated(view, savedInstanceState);
    }

    private void requestAddComment(long storeId, EditText txtComment, Button btnWriteComment) {
        btnWriteComment.setEnabled(false);
        Retrofit retrofit           = Service.RetrofitInstance(getContext());
        Comments services             = retrofit.create(Comments.class);
        UserStoreCommentRequest userStoreCommentRequest = new UserStoreCommentRequest();

        userStoreCommentRequest.setStore_id(storeId);
        // TODO change the user id dynamically.
        userStoreCommentRequest.setUser_id(1);
        userStoreCommentRequest.setBody(txtComment.getText().toString());

        Call<UserStoreCommentResponse> userStoreCommentRequestCall = services.addComment(userStoreCommentRequest);

        userStoreCommentRequestCall.enqueue(new Callback<UserStoreCommentResponse>() {
            @Override
            public void onResponse(Call<UserStoreCommentResponse> call, Response<UserStoreCommentResponse> response) {
                if (response.isSuccessful() && response.code() == 201) {
                    // Clear the Textfield
                    txtComment.setText(null);

                    btnWriteComment.setEnabled(true);

                    // Push the comment to display.
                    Toast.makeText(getContext(), "Succesfully add your comment.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserStoreCommentResponse> call, Throwable t) {
                btnWriteComment.setEnabled(true);
            }
        });
    }

    private void getComments(long storeId, String storeName, String storeAddress, TextView txtStoreName, TextView txtStoreAddress) {
        ProgressDialog progressdialog = new ProgressDialog(getContext());
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCancelable(false);
        progressdialog.show();

        Retrofit retrofit           = Service.RetrofitInstance(getContext());
        Comments services             = retrofit.create(Comments.class);
        Call<List<Comment>> commentsCall = services.getStoreComments(storeId);

        commentsCall.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if  ( response.isSuccessful() ) {

                    txtStoreName.setText(storeName);
                    txtStoreAddress.setText(storeAddress);

                    for(Comment c: response.body())
                    {
                        Log.d("SAMPLE_COMMENT", c.getBody());

                        for(Reply r : c.getReplies())
                        {
                            Log.d("SAMPLE_COMMENT", r.getBody() + " " + r.getUser().getFullname());
                        }
                    }

                    progressdialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                progressdialog.dismiss();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store_information, container, false);
    }

}
