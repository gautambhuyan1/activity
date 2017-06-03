package com.spotizy.myapp;

import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.GoogleMap;
import com.spotizy.myapp.*;


public abstract class CreateActivityView extends AppCompatActivity {

    protected RelativeLayout rlHeader,rlArrow;
    protected LinearLayout llSearch,llInputData,llActivityName,llInterest,llInterestArrow,llDateTime,llDateIcon,llInterestSelection,llDateTimeSelection;
    protected ImageView ivArrow,ivInterestArrow,ivDateIcon;
    protected TextView tvHeader,tvInterest,tvDateTime;
    protected EditText etActivityName;
    protected Button btnGo;
    protected GoogleMap mapView;
    protected ListView scrollItem;
    protected View viewInterestSelection;
    protected View viewDateTimeSelection;
    protected PlaceAutocompleteFragment etSearchLocation;


    protected void init() {
        initView();
        setSize();
        setTextSize();
        setListenerToViews();
    }

    protected abstract void setListenerToViews();

    /**
     * Binding layout view with the view variables.
     */
    private void initView() {

        rlHeader=(RelativeLayout)findViewById(R.id.rlHeader);
        rlArrow=(RelativeLayout)findViewById(R.id.rlArrow);

        ivArrow=(ImageView) findViewById(R.id.ivArrow);
        //ivSearch=(ImageView) findViewById(R.id.ivSearch);
        ivInterestArrow=(ImageView) findViewById(R.id.ivInterestArrow);
        ivDateIcon=(ImageView) findViewById(R.id.ivDateIcon);

        etSearchLocation = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.etSearchLocation);
        etActivityName=(EditText) findViewById(R.id.etActivityName);

        llSearch=(LinearLayout) findViewById(R.id.llSearch);
        //llSearchIcon=(LinearLayout) findViewById(R.id.llSearchIcon);
        llInputData=(LinearLayout) findViewById(R.id.llInputData);
        llActivityName=(LinearLayout) findViewById(R.id.llActivityName);
        llInterest=(LinearLayout) findViewById(R.id.llInterest);
        llInterestArrow=(LinearLayout) findViewById(R.id.llInterestArrow);
        llDateTime=(LinearLayout) findViewById(R.id.llDateTime);
        llDateIcon=(LinearLayout) findViewById(R.id.llDateIcon);
        llInterestSelection=(LinearLayout) findViewById(R.id.llInterestSelection);
        llDateTimeSelection=(LinearLayout) findViewById(R.id.llDateTimeSelection);

        tvHeader=(TextView) findViewById(R.id.tvHeader);
        tvInterest=(TextView) findViewById(R.id.tvInterest);
        tvDateTime=(TextView) findViewById(R.id.tvDateTime);
        tvDateTime=(TextView) findViewById(R.id.tvDateTime);
        viewInterestSelection=(View) findViewById(R.id.viewInterestSelection);
        viewDateTimeSelection=(View) findViewById(R.id.viewDateTimeSelection);
        scrollItem=(ListView) findViewById(R.id.scrollItem);

        btnGo=(Button) findViewById(R.id.btnGo);
    }

    /**
     * Set responsive dimens to the views from layout.
     */
    private void setSize() {

        LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DeviceResolution.getScreenHeight(this) * 8 / 100);
        rlHeader.setLayoutParams(llparams);

        RelativeLayout.LayoutParams rlparams = new RelativeLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 12 / 100, DeviceResolution.getScreenHeight(this) * 12 / 100);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.030), 0, 0, 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        rlArrow.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 6 / 100, DeviceResolution.getScreenHeight(this) * 6 / 100);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        ivArrow.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        tvHeader.setLayoutParams(rlparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DeviceResolution.getScreenHeight(this) * 8 / 100);
        llSearch.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.040), 0, (int) (DeviceResolution.getScreenWidth(this) * 0.010),0);
        //etSearchLocation.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 5 / 100, DeviceResolution.getScreenWidth(this) * 5 / 100);
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.030), 0, (int) (DeviceResolution.getScreenWidth(this) * 0.010),0);
        //ivSearch.setLayoutParams(llparams);

        FrameLayout.LayoutParams flparams = new FrameLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        flparams.setMargins(0, (int) (DeviceResolution.getScreenHeight(this) * 0.030), 0,(int) (DeviceResolution.getScreenHeight(this) * 0.022));
        flparams.gravity= Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL;
        llInputData.setLayoutParams(flparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.035), (int) (DeviceResolution.getScreenHeight(this) * 0.040), (int) (DeviceResolution.getScreenWidth(this) * 0.035),(int) (DeviceResolution.getScreenHeight(this) * 0.030));
        etActivityName.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.035), (int) (DeviceResolution.getScreenHeight(this) * 0.030), (int) (DeviceResolution.getScreenWidth(this) * 0.030),(int) (DeviceResolution.getScreenHeight(this) * 0.030));
        tvInterest.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 4 / 100, DeviceResolution.getScreenWidth(this) * 4 / 100);
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.005), (int) (DeviceResolution.getScreenHeight(this) * 0.030), (int) (DeviceResolution.getScreenWidth(this) * 0.040),(int) (DeviceResolution.getScreenHeight(this) * 0.030));
        ivInterestArrow.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 90 / 100, DeviceResolution.getScreenHeight(this) * 25 / 100);
        llInterestSelection.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.035), (int) (DeviceResolution.getScreenHeight(this) * 0.040), (int) (DeviceResolution.getScreenWidth(this) * 0.030),(int) (DeviceResolution.getScreenHeight(this) * 0.030));
        tvDateTime.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 5 / 100, DeviceResolution.getScreenWidth(this) * 5 / 100);
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.005), (int) (DeviceResolution.getScreenHeight(this) * 0.040), (int) (DeviceResolution.getScreenWidth(this) * 0.040),(int) (DeviceResolution.getScreenHeight(this) * 0.030));
        ivDateIcon.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 90 / 100, DeviceResolution.getScreenHeight(this) * 25 / 100);
        llDateTimeSelection.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 22 / 100, DeviceResolution.getScreenHeight(this) * 7 / 100);
        llparams.setMargins(0, (int) (DeviceResolution.getScreenHeight(this) * 0.030), 0,(int) (DeviceResolution.getScreenHeight(this) * 0.020));
        btnGo.setLayoutParams(llparams);
    }

    /**
     * Set text size for text view, edit text, buttons etc.
     */
    private void setTextSize() {

        tvHeader.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.5));
        //etSearchLocation.setTextSize((float) (DeviceResolution.getScreenInches(this) * 2.8));
        etActivityName.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3));
        tvInterest.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3));
        tvDateTime.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3));
        btnGo.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3));
    }

}
