package com.example.apiapp;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static Client singleton = null;
    private final ApiController controller;

    private Client(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(MainActivity.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        controller = retrofit.create(ApiController.class);
    }

    public static void createClient(Context context){
        if(singleton != null){
            MessagesDisplayer.printMessage(context, "A client has already been instantiated!", 0);
            return;
        }
        singleton = new Client();
    }

    public static Client getClient(){
        return singleton;
    }

    public ApiController getController(){
        return controller;
    }
}
