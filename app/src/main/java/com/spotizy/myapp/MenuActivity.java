package com.spotizy.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;



public class MenuActivity extends MenuActivityView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
    }

    @Override
    protected void setListenerToViews() {

    }



}
