package com.example.doordashapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private ListView mListView;
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
        Log.d(this.getClass().toString(), "Loading Restaurant List Data Started");
        provider.FetchRestaurants(
            new ModelCallback<List<Restaurant>>() {
                @Override
                public void onSuccess(List<Restaurant> restaurantList) {
                    mListView = findViewById(R.id.restaurant_list);
                    mAdapter = new RestaurantAdapter(mContext, 1, restaurantList);
                    mListView.setAdapter(mAdapter);

                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                            Restaurant item = (Restaurant) mListView.getItemAtPosition(position); //
                            Toast.makeText(mContext, item.getName(), Toast.LENGTH_SHORT).show();

                            Intent startDetailActivity = new Intent(MainActivity.this, DetailedActivity.class);
                            startDetailActivity.putExtra("ID", item.getId());
                            startActivity(startDetailActivity);
                        }
                    });

                    Log.d(this.getClass().toString(), "Loading Restaurant List Data Succeeded");
                }

                @Override
                public void onFailure(String error) {
                    Log.d(this.getClass().toString(), "Loading Restaurant List Data Failed");
                    Toast.makeText(mContext, error, Toast.LENGTH_LONG).show();
                }
            }
        );
    }
}
