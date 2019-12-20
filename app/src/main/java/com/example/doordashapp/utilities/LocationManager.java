package com.example.doordashapp.utilities;

public class LocationManager {

    //region FIELDS
    private static LocationManager locationManager;
    private double latitude = 37.422740;
    private double longitude = -122.139956;
    //endregion

    //region CONSTRUCTORS

    public LocationManager() {
    }

    //endregion

    //region SETTERS and GETTERS

    public double getCurrentLatitude() {
        return latitude;
    }

    public double getCurrentLongitude() {
        return longitude;
    }
    //endregion

    //region PUBLIC
    public static LocationManager getInstance() {

        if (locationManager == null)
            locationManager = new LocationManager();

        return locationManager;
    }
    //endregion

    //region PRIVATE/PROTECTED
    //endregion

    //region OVERRIDDEN
    //endregion
}