package com.spotizy.myapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.spotizy.myapp.*;


public abstract class MapActivityView extends AppCompatActivity {

    protected GoogleMap mMap;
    protected SupportMapFragment mapFragment;
    protected RelativeLayout rlHeader, rlSearch;
    protected ImageView ivMenu, ivActivity, ivSeparator, ivInterestHover, ivAdd;
    //protected AutoCompleteTextView atvSearch;
    protected RelativeLayout rlMain, rlActivity, rlInterest;
    protected View vwSeperator;
    protected PlaceAutocompleteFragment autocompleteFragment;
    protected ListView mDrawerList;
    protected ListView lv, ll;
    protected ArrayAdapter<String> mAdapter;

    protected ActionBarDrawerToggle mDrawerToggle;
    protected DrawerLayout mDrawerLayout;

    //New drawerlayout content
    protected LinearLayout ll_profile_name_number, ll_slider, ll_menu_list;
    protected TextView tv_no, tv_name, tv_profile, tv_activity, tv_interest;
    protected ImageView iv_profileimage;


    protected void init() {
        initView();
        setSize();

        setTextSize();
        setFont(this);
        setListenerToViews();

        initDrawerComponetsView();
        setDrawerComponetsSize();
        setDrawerComponetsTextSize();
        setDrawerComponetsFont(this);

    }

    protected abstract void setListenerToViews();

    /**
     * Binding layout view with the view variables.
     */
    private void initView() {
        rlHeader = (RelativeLayout) findViewById(R.id.rlHeader);
        rlMain = (RelativeLayout) findViewById(R.id.rlMain);
        rlSearch = (RelativeLayout) findViewById(R.id.rlSearch);
        ivMenu = (ImageView) findViewById(R.id.ivMenu);
        ivActivity = (ImageView) findViewById(R.id.ivActivity);
        ivSeparator = (ImageView) findViewById(R.id.ivSeparator);
        ivInterestHover = (ImageView) findViewById(R.id.ivInterestHover);
        ivAdd = (ImageView) findViewById(R.id.ivAdd);
        //atvSearch = (AutoCompleteTextView) findViewById(R.id.atvSearch);
        ivActivity = (ImageView) findViewById(R.id.ivActivity);
        rlActivity = (RelativeLayout) findViewById(R.id.rlActivity);
        rlInterest = (RelativeLayout) findViewById(R.id.rlInterest);
        vwSeperator = (View) findViewById(R.id.vwSeperator);
        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.atvSearch);
        lv = (ListView)findViewById(R.id.lv);
        ll = (ListView)findViewById(R.id.ll);


    }

    /**
     * Set responsive dimens to the views from layout.
     */
    private void setSize() {

        RelativeLayout.LayoutParams rlparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, DeviceResolution.getScreenHeight(this) * 10 / 100);
        rlHeader.setLayoutParams(rlparams);
        rlHeader.setPadding((int) (DeviceResolution.getScreenWidth(this) * 0.00), (int) (DeviceResolution.getScreenWidth(this) * 0.010), (int) (DeviceResolution.getScreenWidth(this) * 0.010), 0);

        rlparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, DeviceResolution.getScreenHeight(this) * 50 / 100);
        rlparams.addRule(RelativeLayout.BELOW, R.id.rlHeader);
        rlActivity.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, DeviceResolution.getScreenHeight(this) * 50 / 100);
        rlparams.addRule(RelativeLayout.BELOW, R.id.rlHeader);
        rlInterest.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.040), (int) (DeviceResolution.getScreenHeight(this) * 0.020), (int) (DeviceResolution.getScreenWidth(this) * 0.040), 0);
        rlMain.setLayoutParams(rlparams);


        rlparams = new RelativeLayout.LayoutParams((int) (DeviceResolution.getScreenWidth(this) * 0.06), (int) (DeviceResolution.getScreenWidth(this) * 0.06));
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.040), 0, 0, 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        ivMenu.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 55 / 100, DeviceResolution.getScreenHeight(this) * 10 / 100);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.005), 0, 0, 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.RIGHT_OF, R.id.ivMenu);
        rlSearch.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 55 / 100, DeviceResolution.getScreenHeight(this) * 10 / 100);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.005), 0, 0, 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        //atvSearch.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams((int) (DeviceResolution.getScreenWidth(this) * 0.06), (int) (DeviceResolution.getScreenWidth(this) * 0.06));
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.010), 0, (int) (DeviceResolution.getScreenWidth(this) * 0.005), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.LEFT_OF, R.id.ivSeparator);
        ivActivity.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams((int) (DeviceResolution.getScreenWidth(this) * 0.06), (int) (DeviceResolution.getScreenWidth(this) * 0.06));
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.010), 0, (int) (DeviceResolution.getScreenWidth(this) * 0.005), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.LEFT_OF, R.id.ivInterestHover);
        ivSeparator.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (int) (DeviceResolution.getScreenHeight(this) * 0.001));
        rlparams.setMargins(0, (int) (DeviceResolution.getScreenHeight(this) * 0.030), 0,0);
        rlparams.addRule(RelativeLayout.BELOW, R.id.ivMenu);
        vwSeperator.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams((int) (DeviceResolution.getScreenWidth(this) * 0.06), (int) (DeviceResolution.getScreenWidth(this) * 0.06));
        rlparams.setMargins(0, 0, (int) (DeviceResolution.getScreenWidth(this) * 0.040), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        ivInterestHover.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 18 / 100, DeviceResolution.getScreenWidth(this) * 18 / 100);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.030), 0, 0, (int) (DeviceResolution.getScreenHeight(this) * 0.050));
        rlparams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        ivAdd.setLayoutParams(rlparams);
        ivAdd.setPadding(10, 10, 10, 10);
    }

    /**
     * Set text size for text view, edit text, buttons etc.
     */
    private void setTextSize() {
        //atvSearch.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3));
    }


    /**
     * Set font type for text in different views.
     *
     * @param activity
     */
    private void setFont(Activity activity) {
        Typeface faceLight = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Light.ttf");
        //atvSearch.setTypeface(faceLight);
    }

    //Drawer content manipulators



    protected void initDrawerComponetsView() {
        ll_slider = (LinearLayout) findViewById(R.id.ll_slider);
        ll_menu_list = (LinearLayout) findViewById(R.id.ll_menu_list);
        ll_profile_name_number = (LinearLayout) findViewById(R.id.ll_profile_name_number);
        tv_profile = (TextView) findViewById(R.id.tv_profile);
        tv_activity = (TextView) findViewById(R.id.tv_activity);
        tv_interest = (TextView) findViewById(R.id.tv_interest);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_no = (TextView) findViewById(R.id.tv_no);
        //iv_profileimage = (ImageView) findViewById(R.id.iv_profileimage);
    }


    protected void setDrawerComponetsSize() {

        LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 75 / 100, LinearLayout.LayoutParams.MATCH_PARENT);
        ll_slider.setLayoutParams(llparams);
        llparams = new LinearLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 18 / 100, DeviceResolution.getScreenWidth(this) * 18 / 100);
        llparams.setMargins(DeviceResolution.getScreenWidth(this) * 5 / 100, 0, 0, 0);
        //iv_profileimage.setLayoutParams(llparams);

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


    protected void setDrawerComponetsTextSize() {
        tv_profile.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.2));
        tv_activity.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.2));
        tv_interest.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.2));
        tv_name.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.5));
        tv_no.setTextSize((float) (DeviceResolution.getScreenInches(this) * 2.8));

    }



    private void setDrawerComponetsFont(Activity activity) {

        Typeface faceRegular = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Regular.ttf");
        tv_name.setTypeface(faceRegular);
        tv_profile.setTypeface(faceRegular);
        tv_activity.setTypeface(faceRegular);
        tv_interest.setTypeface(faceRegular);

        Typeface faceLight = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Light.ttf");
        tv_no.setTypeface(faceLight);
    }

}
