package com.rayashari.barcode;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by rayasha on 2/12/2017.
 */

public class MyBanjar extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
