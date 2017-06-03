package com.spotizy.myapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;




public abstract class LoginActivityView extends AppCompatActivity {

    protected LinearLayout llLogoName, llDetails, llOTP;
    protected ImageView ivLogo;
    protected TextView tvAppName, tvOTPText;
    protected EditText etName, etPhnNo, etOTP;
    protected Button btnActive, btnSubmit;


    protected void init() {
        initView();
        setSize();
        setTextSize();
        setListenerToViews();
        setFont(this);
    }

    protected abstract void setListenerToViews();

    /**
     * Binding layout view with the view variables.
     */
    private void initView() {

        llLogoName = (LinearLayout) findViewById(R.id.llLogoName);
        llDetails = (LinearLayout) findViewById(R.id.llDetails);
        llOTP = (LinearLayout) findViewById(R.id.llOTP);

        ivLogo = (ImageView) findViewById(R.id.ivLogo);

        tvAppName = (TextView) findViewById(R.id.tvAppName);
        tvOTPText = (TextView) findViewById(R.id.tvOTPText);

        etName = (EditText) findViewById(R.id.etName);
        etPhnNo = (EditText) findViewById(R.id.etPhnNo);
        etOTP = (EditText) findViewById(R.id.etOTP);

        btnActive = (Button) findViewById(R.id.btnActive);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

    }

    /**
     * Set responsive dimens to the views from layout.
     */
    private void setSize() {

        LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 28 / 100, DeviceResolution.getScreenWidth(this) * 28 / 100);
        llparams.setMargins(0, (int) (DeviceResolution.getScreenHeight(this) * 0.040), 0, 0);
        ivLogo.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins(0, (int) (DeviceResolution.getScreenHeight(this) * 0.030), 0, 0);
        tvAppName.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.060), (int) (DeviceResolution.getScreenHeight(this) * 0.050), (int) (DeviceResolution.getScreenWidth(this) * 0.060), (int) (DeviceResolution.getScreenHeight(this) * 0.015));
        etName.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.060), (int) (DeviceResolution.getScreenHeight(this) * 0.040), (int) (DeviceResolution.getScreenWidth(this) * 0.060), (int) (DeviceResolution.getScreenHeight(this) * 0.015));
        etPhnNo.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DeviceResolution.getScreenHeight(this) * 8 / 100);
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.060), (int) (DeviceResolution.getScreenHeight(this) * 0.045), (int) (DeviceResolution.getScreenWidth(this) * 0.060), (int) (DeviceResolution.getScreenHeight(this) * 0.015));
        btnActive.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.055), (int) (DeviceResolution.getScreenHeight(this) * 0.030), (int) (DeviceResolution.getScreenWidth(this) * 0.055), 0);
        tvOTPText.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.050), (int) (DeviceResolution.getScreenHeight(this) * 0.050), (int) (DeviceResolution.getScreenWidth(this) * 0.050), (int) (DeviceResolution.getScreenHeight(this) * 0.030));
        llOTP.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.030), 0, (int) (DeviceResolution.getScreenWidth(this) * 0.040), (int) (DeviceResolution.getScreenHeight(this) * 0.015));
        etOTP.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 24 / 100, DeviceResolution.getScreenHeight(this) * 7 / 100);
        llparams.gravity = Gravity.CENTER;
        btnSubmit.setLayoutParams(llparams);
    }

    /**
     * Set text size for text view, edit text, buttons etc.
     */
    private void setTextSize() {
        tvAppName.setTextSize((float) (DeviceResolution.getScreenInches(this) * 4));
        etName.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.5));
        etPhnNo.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.5));
        btnActive.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.5));
        tvOTPText.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3));
        etOTP.setTextSize((float) (DeviceResolution.getScreenInches(this) * 4));
        btnSubmit.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.5));
    }

    /**
     * Set font type for text in different views.
     *
     * @param activity
     */

    private void setFont(Activity activity) {

        Typeface faceBlack = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Black.ttf");
        btnActive.setTypeface(faceBlack);
        btnSubmit.setTypeface(faceBlack);

        Typeface faceThin = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Thin.ttf");
        tvAppName.setTypeface(faceThin);

        Typeface faceLight = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Light.ttf");
        etName.setTypeface(faceLight);
        etPhnNo.setTypeface(faceLight);
        tvOTPText.setTypeface(faceLight);
        etOTP.setTypeface(faceLight);

    }
}
