package com.example.doordashapp.model;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("/v2/restaurant/")
    Call<List<Restaurant>> getRestaurants(@Query("lat") double LAT,
                                          @Query("lng") double LNG);

    @GET("/v2/restaurant/{id}")
    Call<Restaurant> getRestaurant(@Path("id") int id);

}