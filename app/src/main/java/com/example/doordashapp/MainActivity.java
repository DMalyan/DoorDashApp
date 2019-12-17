package com.example.doordashapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doordashapp.adapter.RestaurantAdapter;
import com.example.doordashapp.model.GetDataService;
import com.example.doordashapp.model.Restaurant;
import com.example.doordashapp.network.RetrofitClientInstance;
import com.example.doordashapp.utilities.LocationManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private RestaurantAdapter mAdapter;
    private Context mContext;
    private ArrayList<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        LoadData();
    }

    private void LoadData() {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        GetDataService data = retrofit.create(GetDataService.class);

        Call<List<Restaurant>> call = data.getRestaurants(
                LocationManager.getInstance().getCurrentLatitude(),
                LocationManager.getInstance().getCurrentLongitude());

        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(mContext, response.code(), Toast.LENGTH_LONG);
                    return;
                }

                restaurantList = (ArrayList<Restaurant>) response.body();

                listView = findViewById(R.id.restaurant_list);
                mAdapter = new RestaurantAdapter(mContext, restaurantList);
                listView.setAdapter(mAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                        Restaurant item = (Restaurant) listView.getItemAtPosition(position); //
                        Toast.makeText(mContext, item.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG);
            }
        });
    }
}