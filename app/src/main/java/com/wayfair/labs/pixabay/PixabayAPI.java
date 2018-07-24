package com.wayfair.labs.pixabay;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayAPI {
    @GET("api")
    Call<Photos> retrievePhotos(@Query("q") String query);
}
