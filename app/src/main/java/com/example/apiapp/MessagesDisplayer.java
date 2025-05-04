package com.example.apiapp;

import android.content.Context;
import android.widget.Toast;

public class MessagesDisplayer {
    public static void printMessage(Context context, String message, int duration){
        if(duration != 0 && duration != 1){
            printMessage(context, "Invalid toast duration", Toast.LENGTH_SHORT);
        }
        Toast.makeText(context, message, duration).show();
    }
}
