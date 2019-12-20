package com.example.doordashapp.model;


public class Restaurant {

    //region FIELDS
    private String id;
    private String name;
    private String description;
    private String cover_img_url;
    private String status;
    private String delivery_fee;
    private String status_type;
    //private String cover_img_placeholder_url="https://pngimage.net/wp-content/uploads/2018/06/image-placeholder-png-6.png";

    //endregion

    //region CONSTRUCTORS
    public Restaurant(String name, String coverImageUrl, String description, String status)
    {
        this.name = name;
        this.cover_img_url = coverImageUrl;
        this.description = description;
        this.status = status;
    }

    public Restaurant(String id, String coverImageUrl, String name, String description, String status, String delivery_fee, String status_type)
    {
        this.id = id;
        this.cover_img_url = coverImageUrl;
        this.name = name;
        this.description = description;
        this.status = status;
        this.delivery_fee = delivery_fee;
        this.status_type = status_type;
    }
    //endregion

    //region SETTERS and GETTERS

    /*
        public String getPlaceholderUrl()
        {
            return cover_img_placeholder_url;
        }
    */
    public String getCover_img_url() {
        return cover_img_url;
    }

    public String getDelivery_fee() {
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
    //endregion

    //region PUBLIC
    //endregion

    //region PRIVATE/PROTECTED
    //endregion

    //region OVERRIDDEN
    //endregion



}