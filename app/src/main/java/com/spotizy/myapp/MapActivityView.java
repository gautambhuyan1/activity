package com.spotizy.myapp;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

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

    protected void init() {
        initView();
        setSize();
        setListenerToViews();
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
}
