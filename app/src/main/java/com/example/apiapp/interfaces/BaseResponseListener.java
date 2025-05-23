package com.example.apiapp.interfaces;

public interface BaseResponseListener {
    public void onRawJsonResponse(String json);
    public void handleResponseCode(int code, String errorMessage);
}