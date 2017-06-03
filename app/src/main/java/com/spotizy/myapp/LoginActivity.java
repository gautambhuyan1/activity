package com.spotizy.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;



public class LoginActivity extends LoginActivityView{

    boolean otpVisivilityStatus=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        tvOTPText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otpVisivilityStatus) {
                    otpVisivilityStatus=false;
                    llOTP.setVisibility(View.GONE);
                }
                else {
                    otpVisivilityStatus=true;
                    llOTP.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    @Override
    protected void setListenerToViews() {

    }
}
