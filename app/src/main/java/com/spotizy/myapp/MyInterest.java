package com.spotizy.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;



public class MyInterest extends MyInterestView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinterest);
        init();
    }

    @Override
    protected void setListenerToViews() {

    }
}
