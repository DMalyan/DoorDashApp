package com.example.doordashapp.model;

import com.example.doordashapp.network.RetrofitClientInstance;
import com.example.doordashapp.utilities.LocationManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ModelProvider {

    //region FIELDS
    //endregion

    //region CONSTRUCTORS
    public ModelProvider()
    {}
    //endregion

    //region SETTERS and GETTERS
    //endregion

    //region PUBLIC


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

    public void FetchRestaurant(int restaurantId, final ModelCallback<Restaurant> callback)
    {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        GetDataService data = retrofit.create(GetDataService.class);

        Call<Restaurant> call = data.getRestaurant(restaurantId);

        call.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                if (!response.isSuccessful()) {
                    callback.onFailure("ERROR: " + response.code());
                    return;
                }
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                callback.onFailure("ERROR: " + t.getMessage());
            }
        });
    }
    //endregion

    //region PRIVATE/PROTECTED
    //endregion

    //region OVERRIDDEN
    //endregion

}
