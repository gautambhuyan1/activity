package com.spotizy.myapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;



public abstract class MenuActivityView extends AppCompatActivity {
    protected LinearLayout ll_profile_name_number, ll_slider, ll_menu_list;
    protected TextView tv_no, tv_name, tv_profile, tv_activity, tv_interest;
    protected ImageView iv_profileimage;

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
    protected void initView() {
        ll_slider = (LinearLayout) findViewById(R.id.ll_slider);
        ll_menu_list = (LinearLayout) findViewById(R.id.ll_menu_list);
        ll_profile_name_number = (LinearLayout) findViewById(R.id.ll_profile_name_number);
        tv_profile = (TextView) findViewById(R.id.tv_profile);
        tv_activity = (TextView) findViewById(R.id.tv_activity);
        tv_interest = (TextView) findViewById(R.id.tv_interest);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_no = (TextView) findViewById(R.id.tv_no);
        iv_profileimage = (ImageView) findViewById(R.id.iv_profileimage);
    }

    /**
     * Set responsive dimens to the views from layout.
     */
    protected void setSize() {

        LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 75 / 100, LinearLayout.LayoutParams.MATCH_PARENT);
        ll_slider.setLayoutParams(llparams);
        llparams = new LinearLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 18 / 100, DeviceResolution.getScreenWidth(this) * 18 / 100);
        llparams.setMargins(DeviceResolution.getScreenWidth(this) * 5 / 100, 0, 0, 0);
        iv_profileimage.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        llparams.setMargins(DeviceResolution.getScreenWidth(this) * 6 / 100, 0, DeviceResolution.getScreenWidth(this) * 6 / 100, 0);
        ll_menu_list.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins(0, DeviceResolution.getScreenHeight(this) * 4 / 100, 0, DeviceResolution.getScreenHeight(this) * 1 / 100);
        tv_profile.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins(0, DeviceResolution.getScreenHeight(this) * 3 / 100, 0, DeviceResolution.getScreenHeight(this) * 1 / 100);
        tv_activity.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins(0, DeviceResolution.getScreenHeight(this) * 3 / 100, 0, DeviceResolution.getScreenHeight(this) * 1 / 100);
        tv_interest.setLayoutParams(llparams);


    }

    /**
     * Set text size for text view, edit text, buttons etc.
     */
    protected void setTextSize() {
        tv_profile.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.2));
        tv_activity.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.2));
        tv_interest.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.2));
        tv_name.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.5));
        tv_no.setTextSize((float) (DeviceResolution.getScreenInches(this) * 2.8));

    }


    /**
     * Set font type for text in different views.
     *
     * @param activity
     */
    private void setFont(Activity activity) {

        Typeface faceRegular = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Regular.ttf");
        tv_name.setTypeface(faceRegular);
        tv_profile.setTypeface(faceRegular);
        tv_activity.setTypeface(faceRegular);
        tv_interest.setTypeface(faceRegular);

        Typeface faceLight = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Light.ttf");
        tv_no.setTypeface(faceLight);
    }


}
