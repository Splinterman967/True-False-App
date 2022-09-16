package com.example.true_false.controllersingleton;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.true_false.MainActivity;

public class request extends Application {

    private static request instance;
    private RequestQueue requestQueue;


 public request(){}

    public static synchronized request getInstance() {

        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            //Contexi app e yapıyo boylelıkle cihaza sadece bır kere olusturuyor bır daha olusturmasına gerek kalmıyor
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {

     getRequestQueue().add(req);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
}


