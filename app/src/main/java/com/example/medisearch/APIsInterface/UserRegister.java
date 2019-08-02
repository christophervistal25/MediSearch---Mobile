package com.example.medisearch.APIsInterface;


import com.example.medisearch.Models.User.UserRegisterRequest;
import com.example.medisearch.Models.User.UserRegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserRegister {
    @POST("user/register")
    Call<UserRegisterResponse> register(@Body UserRegisterRequest userRegisterRequest);
}
