package com.example.doordashapp.model;

public class Restaurant {

    private String id;
    private String name;
    private String description;
    private String cover_img_url = "https://cdn.doordash.com/media/restaurant/cover/Armadillo-Willys-BBQ.png";
    private String status;
    private int delivery_fee;
    private String status_type;

    public Restaurant(String id, String coverImageUrl, String name, String description, String status, int delivery_fee, String status_type)
    {
        this.id = id;
        if(!(coverImageUrl == null || coverImageUrl == ""))
            this.cover_img_url = coverImageUrl;
        this.name = name;
        this.description = description;
        this.status = status;
        this.delivery_fee = delivery_fee;
        this.status_type = status_type;
    }

    public String getCover_img_url() {
        return cover_img_url;
    }

    public int getDelivery_fee() {
        return delivery_fee;
    }

    public String getStatus_type() {
        return status_type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus(){
        return status;
    }

}