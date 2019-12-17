package com.example.doordashapp.utilities;

public class LocationManager {


    private static LocationManager locationManager;

    public LocationManager()
    {
    }

    public static LocationManager getInstance() {

        if (locationManager == null)
            locationManager = new LocationManager();

        return locationManager;
    }

    private double latitude = 37.422740;
    private double longitude = -122.139956;
    public double getCurrentLatitude()
    {
        return  latitude;
    }

    public double getCurrentLongitude()
    {
        return  longitude;
    }
}
