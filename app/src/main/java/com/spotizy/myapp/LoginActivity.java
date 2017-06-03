package com.spotizy.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends LoginActivityView{

    boolean otpVisivilityStatus=false;
    private static boolean loggedOn = false;
    private String otpText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (loggedOn) {
            finish();
        }
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

        btnActive.setOnClickListener(new View.OnClickListener() {
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

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String phone = etPhnNo.getText().toString();
                String otp = etOTP.getText().toString();
                //LinkedHashMap<String, String> postParams = new LinkedHashMap<>();
                JSONObject postParams = new JSONObject();
                try {
                    postParams.put("username", name);
                    postParams.put("imsi", phone);
                    postParams.put("otp", otp);

                } catch (JSONException e) {
                    System.out.println("Error in JSON");
                }

                WebApiDataTask webDataFetcher = new WebApiDataTask(LoginActivity.this);
                try {

                    //check whether the msg empty or not

                    String postURL = postParams.toString();//ServerDataRetriever.createPostURL(postParams);
                    webDataFetcher.execute("POST", "otpconfirm", postURL);
                } catch (Exception e) {
                    webDataFetcher.cancel(true);
                }
            }
        });
    }


    public void otpConfirm(boolean result) {
        if (result) {
            loggedOn = true;
            finish();
        }
        else {
            //do again
        }
    }

    public static boolean getLoginStatus () {
        return loggedOn;
    }

    @Override
    protected void setListenerToViews() {

    }
}
