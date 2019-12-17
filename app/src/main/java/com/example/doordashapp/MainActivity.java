package com.example.doordashapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import android.widget.Toast;

import com.example.doordashapp.adapter.RestaurantAdapter;
import com.example.doordashapp.model.ModelProvider;
import com.example.doordashapp.model.ModelCallback;
import com.example.doordashapp.model.Restaurant;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private RestaurantAdapter mAdapter;
    private Context mContext;
    private ModelProvider provider ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        provider = new ModelProvider();
        LoadData();
    }

    private void LoadData() {
        provider.FetchRestaurants(
            new ModelCallback<List<Restaurant>>() {
                @Override
                public void onSuccess(List<Restaurant> restaurantList) {
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
                public void onFailure(String error) {
                    Toast.makeText(mContext, error, Toast.LENGTH_LONG).show();
                }
            }
        );
    }
}
