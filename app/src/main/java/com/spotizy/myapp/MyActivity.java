package com.spotizy.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;



public class MyActivity extends MyActivityView{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myactivity);
        init();
    }

    @Override
    protected void setListenerToViews() {

    }
}
