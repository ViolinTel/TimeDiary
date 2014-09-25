package com.tel.test;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by violintel on 7/15/14.
 */
public class MidApp extends Application {
    public String name;

    @Override
    public void onCreate() {
        super.onCreate();
        setName("First");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
