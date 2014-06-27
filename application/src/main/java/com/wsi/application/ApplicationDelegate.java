package com.wsi.application;

import android.app.Application;

import com.wsi.sdk.provider.ActiveFireDataProvider;

/**
 * Created by Ievgen Usenko
 * Date: 6/26/14.
 */
public class ApplicationDelegate extends Application {
    private static ActiveFireDataProvider DATA_PROVIDER;

    @Override
    public void onCreate() {
        DATA_PROVIDER = new ActiveFireDataProvider(getApplicationContext());
        super.onCreate();
    }
}
