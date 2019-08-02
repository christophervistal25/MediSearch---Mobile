package com.example.medisearch.APIsInterface;


import com.example.medisearch.Models.User.UserLoginRequest;
import com.example.medisearch.Models.User.UserLoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserLogin {
    @POST("user/login")
    Call<UserLoginResponse> login(@Body UserLoginRequest userLoginRequest);
}
