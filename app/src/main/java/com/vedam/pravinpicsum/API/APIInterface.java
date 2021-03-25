package com.vedam.pravinpicsum.API;

import com.vedam.pravinpicsum.API.Models.Pics;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/list")
    Call<List<Pics>> getPosts();
}
