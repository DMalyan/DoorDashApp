package com.example.doordashapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doordashapp.adapter.RestaurantAdapter;
import com.example.doordashapp.model.GetDataService;
import com.example.doordashapp.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private ListView listView;
    private RestaurantAdapter mAdapter;
private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
mContext = this;

        String baseURL = "https://api.doordash.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetDataService data = retrofit.create(GetDataService.class);

        Call<List<Restaurant>> call = data.getRestaurants(37.422740, -122.139956);



        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                listView = findViewById(R.id.restaurant_list);
                ArrayList<Restaurant> restaurantList = new ArrayList<>();

                List<Restaurant> restaurants = response.body();
                for (Restaurant r : restaurants) {

                    restaurantList.add(new Restaurant(r.getId(),
                            r.getCover_img_url(), r.getName(), r.getDescription(),
                            r.getStatus()));

                }

                mAdapter = new RestaurantAdapter(mContext, restaurantList);
                listView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {

                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG);
            }
        });


    }
}