package com.example.doordashapp;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.doordashapp.adapter.RestaurantAdapter;
import com.example.doordashapp.model.ModelCallback;
import com.example.doordashapp.model.ModelProvider;
import com.example.doordashapp.model.Restaurant;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ModelProviderUnitTest {

    public ModelProviderUnitTest()
    {

    }

    CountDownLatch lock = new CountDownLatch(1);
    List<Restaurant> mReceivedList = null;
    Restaurant mRestaurant = null;

    @Test
    public void testFetchRestaurants() throws Exception {


        ModelProvider provider = new ModelProvider();
        provider.FetchRestaurants(
                new ModelCallback<List<Restaurant>>() {
                   @Override
                    public void onSuccess(List<Restaurant> restaurantList) {
                       mReceivedList = restaurantList;
                       lock.countDown();
                    }

                    @Test
                    @Override
                    public void onFailure(String error) {
                        lock.countDown();
                    }
                }
        );

        lock.await(20, TimeUnit.SECONDS);

        Assert.assertNotNull(mReceivedList);
        Assert.assertTrue(mReceivedList.size() > 10);
    }

    @Test
    public void testFetchRestaurant() throws Exception {

        String actualID = "445";
        ModelProvider provider = new ModelProvider();
        provider.FetchRestaurant(actualID,
                new ModelCallback<Restaurant>() {
                    @Override
                    public void onSuccess(Restaurant restaurant) {
                        mRestaurant = restaurant;
                        lock.countDown();
                    }

                    @Test
                    @Override
                    public void onFailure(String error) {
                        lock.countDown();
                    }
                }
        );

        lock.await(20, TimeUnit.SECONDS);

        Assert.assertNotNull(mRestaurant);
        String expectedID = mRestaurant.getId();
        Assert.assertTrue(expectedID.equals(actualID));
    }




}

