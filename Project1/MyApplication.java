package com.example.thisproject1;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;
import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }

}