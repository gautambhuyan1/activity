package com.spotizy.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


public class MyActivity extends MyActivityView{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myactivity);
        init();
        rlArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void setListenerToViews() {

    }
}
