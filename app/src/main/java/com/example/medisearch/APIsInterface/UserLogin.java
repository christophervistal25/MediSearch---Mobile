package com.example.medisearch.APIsInterface;

import com.example.medisearch.WebService.User.UserLoginRequest;
import com.example.medisearch.WebService.User.UserLoginResponse;
import com.example.medisearch.WebService.User.UserRegisterRequest;
import com.example.medisearch.WebService.User.UserRegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserLogin {
    @POST("user/login")
    Call<UserLoginResponse> login(@Body UserLoginRequest userLoginRequest);
}
