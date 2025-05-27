package com.example.apiapp.interfaces;

public interface BaseResponseListener {
    public void onRawJsonResponse(String json);
    public void handleResponseCode(String errorMessage);
    public void handleResponseCode(int code);
}