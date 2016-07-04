package com.tolyaolya.mygoals;

import android.app.Application;

/**
 * Created by 111 on 04.07.2016.
 */
public class BdApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DbHelper.init(getApplicationContext());
    }
}
