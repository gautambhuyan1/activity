package com.spotizy.myapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.GoogleMap;
//import com.map.activity.R;
import com.spotizy.myapp.LoopView;
import com.spotizy.myapp.DeviceResolution;

import java.util.ArrayList;
import java.util.ListIterator;

public abstract class CreateActivityView extends AppCompatActivity {

    protected RelativeLayout rlHeader,rlArrow;
    protected LinearLayout llSearch,llSearchIcon,llInputData,llActivityName,llInterest,llInterestArrow,llDateTime,llDateIcon,llInterestSelection,llDateTimeSelection;
    protected ImageView ivArrow,ivSearch,ivInterestArrow,ivDateIcon;
    protected TextView tvHeader,tvInterest,tvDateTime;
    protected EditText etActivityName;
    protected Button btnGo;
    protected GoogleMap mapView;
    protected View viewInterestSelection,viewDateTimeSelection;
    protected PlaceAutocompleteFragment etSearchLocation;
    protected ArrayList<String> interestList;
    /**
     * Interest picker
     */
    protected LoopView loop_viewInterest;
    /**
     * Date picker
     */
    protected LoopView loop_viewDate;
    /**
     * Time picker
     */
    protected LoopView loop_viewTime;



    protected void init() {
        interestList = new ArrayList<>();
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

        rlHeader=(RelativeLayout)findViewById(R.id.rlHeader);
        rlArrow=(RelativeLayout)findViewById(R.id.rlArrow);

        ivArrow=(ImageView) findViewById(R.id.ivArrow);
        ivSearch=(ImageView) findViewById(R.id.ivSearch);
        ivInterestArrow=(ImageView) findViewById(R.id.ivInterestArrow);
        ivDateIcon=(ImageView) findViewById(R.id.ivDateIcon);

        etSearchLocation = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.etSearchLocation);
        etActivityName=(EditText) findViewById(R.id.etActivityName);

        etActivityName=(EditText) findViewById(R.id.etActivityName);

        llSearch=(LinearLayout) findViewById(R.id.llSearch);
        llSearchIcon=(LinearLayout) findViewById(R.id.llSearchIcon);
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

        btnGo=(Button) findViewById(R.id.btnGo);

        loop_viewDate = (LoopView) findViewById(R.id.loop_viewDate);
        loop_viewTime = (LoopView) findViewById(R.id.loop_viewTime);
        loop_viewInterest = (LoopView) findViewById(R.id.loop_viewInterest);
    }

    public void setInterestList(ArrayList<String> st) {
        String i;
        ListIterator<String> li;
        li = st.listIterator();
        while (li.hasNext()) {
            i = li.next();
            interestList.add(i);
        }
    }
    /*
   Set array for interest picker view
    */
    protected ArrayList<String> getInterest() {
        ArrayList<String> list_interest = new ArrayList<>();
        /*
        list_interest.add("Music");
        list_interest.add("Food");
        list_interest.add("Sport");
        list_interest.add("Entertainment");
        list_interest.add("Photography");
        list_interest.add("Work");
        list_interest.add("Exhibition");
        list_interest.add("Art");
        list_interest.add("Literature");
        list_interest.add("Communication");*/
        return interestList;
    }

    /*
    Set array for date picker view
     */
    protected ArrayList<String> getDate() {
        ArrayList<String> list_date = new ArrayList<>();
        list_date.add("Thu July 25");
        list_date.add("Fri July 26");
        list_date.add("Sat July 27");
        list_date.add("Sun July 28");
        list_date.add("Mon July 29");
        list_date.add("Tue July 30");
        list_date.add("Wed Aug 01");
        list_date.add("Thu Aug 02");
        list_date.add("Fri Aug 03");
        list_date.add("Sat Aug 04");

        return list_date;
    }

    /*
    Set array for time picker view
     */
    protected ArrayList<String> getTime() {
        ArrayList<String> list_time = new ArrayList<>();
        list_time.add("11 54 AM");
        list_time.add("11 55 AM");
        list_time.add("11 56 AM");
        list_time.add("11 57 AM");
        list_time.add("11 58 AM");
        list_time.add("11 59 AM");
        list_time.add("12 00 AM");
        list_time.add("12 01 PM");
        list_time.add("12 02 PM");
        list_time.add("12 03 PM");
        return list_time;
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

        rlparams = new RelativeLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 5 / 100, DeviceResolution.getScreenHeight(this) * 5 / 100);
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
        ivSearch.setLayoutParams(llparams);

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

        llparams = new LinearLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 24 / 100, DeviceResolution.getScreenHeight(this) * 7 / 100);
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

    /**
     * Set font type for text in different views.
     *
     * @param activity
     */

    private void setFont(Activity activity) {

        Typeface faceBlack = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Black.ttf");
        tvHeader.setTypeface(faceBlack);
        btnGo.setTypeface(faceBlack);

        Typeface faceLight = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Light.ttf");
        //etSearchLocation.setTypeface(faceLight);
        etActivityName.setTypeface(faceLight);
        tvInterest.setTypeface(faceLight);
        tvDateTime.setTypeface(faceLight);

    }

}
