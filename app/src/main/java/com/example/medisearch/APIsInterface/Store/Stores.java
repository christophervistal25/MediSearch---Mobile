package com.example.medisearch.APIsInterface.Store;

import com.example.medisearch.Models.Stores.Store;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Stores {
    @GET("stores")
    Call<List<Store>> stores();
}
