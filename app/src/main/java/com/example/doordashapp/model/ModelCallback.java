package com.example.doordashapp.model;


public interface ModelCallback<T> {
    void onSuccess(T data);
    void onFailure(String error);
}