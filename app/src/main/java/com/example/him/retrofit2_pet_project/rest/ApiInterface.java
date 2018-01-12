package com.example.him.retrofit2_pet_project.rest;

import com.example.him.retrofit2_pet_project.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by him on 1/12/2018.
 */

public interface ApiInterface {
    //API call
    @GET("json")
    Call<Example> getSunrise(@Query("lat") Float lat,@Query("lng") Float lng);

}
