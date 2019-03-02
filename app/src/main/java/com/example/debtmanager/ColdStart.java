package com.example.debtmanager;

import android.app.Application;
import android.os.SystemClock;

public class ColdStart extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(3000);
    }
}
