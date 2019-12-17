package com.example.doordashapp.model;

import com.example.doordashapp.network.RetrofitClientInstance;
import com.example.doordashapp.utilities.LocationManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ModelProvider {

    public ModelProvider()
    {}

    public void FetchRestaurants(final ModelCallback<List<Restaurant>> callback)
    {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        GetDataService data = retrofit.create(GetDataService.class);

        Call<List<Restaurant>> call = data.getRestaurants(
                LocationManager.getInstance().getCurrentLatitude(),
                LocationManager.getInstance().getCurrentLongitude());

        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                if (!response.isSuccessful()) {
                    callback.onFailure("ERROR: " + response.code());
                    return;
                }
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                callback.onFailure("ERROR: " + t.getMessage());
            }
        });
    }
}
