package com.spotizy.myapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

//import android.support.v7.app.AppCompactActivity;


public class CreateActivity extends CreateActivityView implements View.OnClickListener, OnMapReadyCallback, PlaceSelectionListener {

    private Context context;
    private String interestId;
    private double latitude;
    private double longitude;
    private GoogleMap mMap;
    //private Spinner interestList;
    private String interestSelected;
    private ArrayList<String> interests;
    //private PlaceAutocompleteFragment autocompleteFragment;
    private static String date = "1 Aug";
    private String time = "12:30am";
    private String userid;
    private String username;
    private LayoutInflater layoutIflator;

    private Runnable r = null;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("#####  In Create Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        getUserContext();
        init();

        interestList = getIntent().getStringArrayListExtra("interests");

        setYearMonthPicker();
        r = new Runnable() {

            @Override
            public void run() {
                try {
                    //Log.e("EventsActivity", "run: loopView: " + loop_viewDate.getSelectedItem());
                    //Log.e("EventsActivity", "run: loopView2: " + loop_viewTime.getSelectedItem());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        context = this.getApplicationContext();
        this.layoutIflator = LayoutInflater.from(this);
        ArrayList<String> interests = new ArrayList<String>();
        interests.add("hiking");
        interests.add("cricket");
        interests.add("soccer");
        interests.add("tennis");

        setInterests(interests);
        Intent triggerData = this.getIntent();
        interestId = interestSelected = "hiking";
        if (triggerData != null) {
            this.interests = triggerData.getStringArrayListExtra("interests");
            System.out.println("##### Interest ID is" + this.interestId);
        } else {
            System.out.println("##### No intent data");
        }
        //createActivity = (Button) this.findViewById(R.id.create_activity);
        //interestList = (Spinner) this.findViewById(R.id.interest_selection);
        //activityName = (EditText) this.findViewById(R.id.activity_name);


        // Register a listener to receive callbacks when a place has been selected or an error has
        // occurred.
        etSearchLocation.setOnPlaceSelectedListener(this);

        ArrayAdapter<String> adaptor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, this.interests);
        //interestList.setAdapter(adaptor);

        latitude = longitude = -1;
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
        //webView = (WebView)this.findViewById(R.id.json_display);
        /*
        Get current location.
         */
        System.out.println("#####  Done with page");
        GPSTracker tracker = new GPSTracker(this);
        if (!tracker.canGetLocation()) {
            System.out.println("#####   Did not find tracker");
            tracker.showSettingsAlert();
        } else {
            latitude = tracker.getLatitude();
            longitude = tracker.getLongitude();
            System.out.println("#### Found location latitude = " + latitude + " longitude = " + longitude);
        }



        /*
        interestList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

                interestSelected = interestList.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                //interestSelected = interestList.
                // Do nothing

            }

        });
*/
        ivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etActivityName.getText().toString();
                String dateAndTime = date;//activityDateTime.getText().toString();
                String interest = interestSelected;
                String place = "";

                List<Address> addresses;
                Geocoder gcd = new Geocoder(context, Locale.getDefault());
                try {
                    addresses = gcd.getFromLocation(latitude, longitude, 1);
                    if (addresses.size() > 0)
                    {
                        System.out.println(addresses.get(0).getLocality());
                        place = addresses.get(0).getLocality();
                    }
                    else
                    {
                        // do your staff
                    }
                }
                catch (IOException e) {

                }

                interest = interestList.get(loop_viewInterest.getSelectedItem());


                //LinkedHashMap<String, String> postParams = new LinkedHashMap<>();
                JSONObject postParams = new JSONObject();
                try {
                    postParams.put("userid", userid);
                    postParams.put("username", username);
                    postParams.put("interest", interest);
                    postParams.put("activity", name);
                    postParams.put("lat", Double.toString(latitude));
                    postParams.put("lng", Double.toString(longitude));
                    postParams.put("place", place);
                    postParams.put("time", time);
                    postParams.put("date", date);

                } catch (JSONException e) {
                    System.out.println("Error in JSON");
                }

                WebApiDataTask webDataFetcher = new WebApiDataTask(CreateActivity.this);
                try {

                    //check whether the msg empty or not

                    String postURL = postParams.toString();//ServerDataRetriever.createPostURL(postParams);
                    webDataFetcher.execute("POST", "createnewactivity", postURL);
                } catch (Exception e) {
                    webDataFetcher.cancel(true);
                }
                finish();
            }
        });
    }
    private void setYearMonthPicker() {

        // Interest Picker
        loop_viewInterest.setInitPosition(5);
        loop_viewInterest.setCanLoop(true);


        loop_viewInterest.setLoopListener(new LoopScrollListener() {
            @Override
            public void onItemSelect(int item) {
            }

            @Override
            public void onUserSelectionInProgress(int msgCode) {
                Log.e("TAG", "onItemSelect " + msgCode + ": new interest selected");
                try {
                    handler.removeCallbacks(r);
                    handler.postDelayed(r, 500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        loop_viewInterest.setTextSize(18);
        loop_viewInterest.setDataList(getInterest());

        // Date Picker
        loop_viewDate.setInitPosition(5);
        loop_viewDate.setCanLoop(true);
        loop_viewDate.setLoopListener(new LoopScrollListener() {
            @Override
            public void onItemSelect(int item) {
            }

            @Override
            public void onUserSelectionInProgress(int msgCode) {
                Log.e("TAG", "onItemSelect " + msgCode + ": new date selected");
                try {
                    handler.removeCallbacks(r);
                    handler.postDelayed(r, 500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });

        loop_viewDate.setTextSize(18);//must be called before setDateList
        loop_viewDate.setDataList(getDate());

        // Time Picker
        loop_viewTime.setInitPosition(5);
        loop_viewTime.setCanLoop(true);


        loop_viewTime.setLoopListener(new LoopScrollListener() {
            @Override
            public void onItemSelect(int item) {
            }

            @Override
            public void onUserSelectionInProgress(int msgCode) {
                Log.e("TAG", "onItemSelect " + msgCode + ": new time selected");
                try {
                    handler.removeCallbacks(r);
                    handler.postDelayed(r, 500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        loop_viewTime.setTextSize(18);
        loop_viewTime.setDataList(getTime());
    }

    @Override
    protected void setListenerToViews() {

        llInterestArrow.setOnClickListener(this);
        llDateIcon.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.llInterestArrow:
                if(llInterestSelection.getVisibility() == View.GONE)
                {

                    llInterestSelection.setVisibility(View.VISIBLE);
                    viewInterestSelection.setVisibility(View.VISIBLE);

                }else{

                    llInterestSelection.setVisibility(View.GONE);
                    viewInterestSelection.setVisibility(View.GONE);
                }

                break;
            case R.id.llDateIcon:
                if(llDateTimeSelection.getVisibility() == View.GONE)
                {

                    llDateTimeSelection.setVisibility(View.VISIBLE);
                    viewDateTimeSelection.setVisibility(View.VISIBLE);

                }else{

                    llDateTimeSelection.setVisibility(View.GONE);
                    viewDateTimeSelection.setVisibility(View.GONE);
                }

                break;
        }



    }


    @Override
    public void onPlaceSelected(Place place) {
        LatLng location = place.getLatLng();

        latitude = location.latitude;
        longitude = location.longitude;

        mMap.clear();

        mMap.addMarker(new MarkerOptions().position(location).title("Marker in location"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 12));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        interestId = data.getStringExtra("interest");

        System.out.println("#### onActivityResult::InterestId = " + interestId);

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        System.out.println("##### Map is ready now");
        LatLng activityLocation = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(activityLocation).title("Marker in location"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(activityLocation));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(activityLocation, 12));
        if ((ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            mMap.setMyLocationEnabled(true);
        }
    }

    public void creationCompleted(boolean status) {
        System.out.println("####   Completed creation of task");
        finishActivity(0);
    }

    @Override
    public void onError(Status status) {
        System.out.println("Error hit");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), R.style.dialogTheme, this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            date = month+":"+day+":"+year;
        }

    }

    public void showDatePickerDialog(View v) {

        FragmentManager fragmentManager = getFragmentManager();

        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(fragmentManager, "datePicker");
    }

    private void getUserContext() {
        username = UserCredentials.getUserName();
        userid = UserCredentials.getUserId();
    }

    public void setInterestItem(String interest) {
        interestSelected = interest;
    }

    public void setInterests(ArrayList<String> interestArray) {
        this.interests = interestArray;
        System.out.println("#####  setData::Interests");

        InterestList iAdaptor = new InterestList(this, this.layoutIflator, interestArray);
        //this.ll.setAdapter(new InterestAdaptor(this, this.layoutIflator, interestArray));
        //iAdaptor.

        //this.viewInterestSelection.setAdapter(iAdaptor);
        //this.scrollItem.setAdapter(iAdaptor);
    }
}
