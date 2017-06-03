package com.spotizy.myapp;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

public class MessageActivity extends ActivityDetailsView implements OnMapReadyCallback {

    private ArrayList<MessageData> messages;
    //private ListView msgList;
    private LayoutInflater layoutIflator;
    //private TextView activityName;
    //private TextView activityDate;
    private String activityNameStr;
    //private EditText message_content;
    //private Button sendBtn;
    //private Button createActivity;
    private String activityId;
    private String interestId;
    private double latitude;
    private double longitude;
    private int likes;
    private int shares;
    private Intent intent;

    private String date;
    private String username;
    private String userid;
    //private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("#####  Before Message Main");
        super.onCreate(savedInstanceState);

        getUserContext();
        setContentView(R.layout.activity_details);
        init();
        Intent triggerData = this.getIntent();
        if (triggerData != null) {
            this.activityId = triggerData.getStringExtra("activityid");
            this.interestId = triggerData.getStringExtra("interestid");
        }
        else {
            System.out.println("##### No intent data");
        }
        /*
        //createActivity = (Button)this.findViewById(R.id.create_activity);
        //sendBtn = (Button)this.findViewById(R.id.send_message);
        message_content = (EditText)this.findViewById(R.id.message_content);
        activityName = (TextView)this.findViewById(R.id.activity_name);
        activityDate = (TextView)this.findViewById(R.id.activity_date);
        */
        //msgList = (ListView)this.findViewById(R.id.message_list);
        this.layoutIflator = LayoutInflater.from(this);
        intent = getIntent();
        interestId = intent.getStringExtra("interest");
        activityId = intent.getStringExtra("activityid");
        activityNameStr = intent.getStringExtra("activityname");
        latitude = intent.getDoubleExtra("latitude", 100);
        longitude = intent.getDoubleExtra("longitude", 100);
        likes = intent.getIntExtra("likes", 0);
        shares = intent.getIntExtra("shares", 0);
        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0)
            {
                System.out.println(addresses.get(0).getLocality());
                tvCityName.setText(addresses.get(0).getLocality());
            }
            else
            {
                // do your staff
            }
        }
        catch (IOException e) {

        }

        date = intent.getStringExtra("date");
        System.out.println("#### INSIDE Activity latlong "+latitude+" "+longitude);
        tvCityName.setText(interestId);
        tvName.setText("userid");
        tvDetails.setText(activityNameStr);
        tvLikeCount.setText(likes+" likes");
        tvShareCount.setText(shares+" shares");


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

        //webView = (WebView)this.findViewById(R.id.json_display);
        System.out.println("#####  Main");

        WebApiDataTask webDataFetcher = new WebApiDataTask(MessageActivity.this);
        try {
            //String url = "http://hospitopedia.com/message/get?activityid="+activityId+"&phone=%221234%22";
            //System.out.println("#### Messages: Onclick url = "+url);
            LinkedHashMap<String,String> getParams=new LinkedHashMap<>();
            getParams.put("activityid", activityId);
            String getURL = ServerDataRetriever.createGetURL(getParams);
            webDataFetcher.execute("GET", "messages", getURL);
        } catch (Exception e) {
            webDataFetcher.cancel(true);
        }

        ivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity(0);
                finish();
            }
        });

        ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LinkedHashMap<String, String> postParams = new LinkedHashMap<>();
                JSONObject postParams = new JSONObject();
                try {
                    postParams.put("userid", userid);
                    postParams.put("username", username);
                    postParams.put("activityid", activityId);

                } catch (JSONException e) {
                    System.out.println("Error in JSON");
                }

                WebApiDataTask webDataFetcher = new WebApiDataTask(MessageActivity.this);
                try {

                    //check whether the msg empty or not

                    String postURL = postParams.toString();//ServerDataRetriever.createPostURL(postParams);
                    webDataFetcher.execute("POST", "likeactivity", postURL);
                } catch (Exception e) {
                    webDataFetcher.cancel(true);
                }
            }
        });

        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LinkedHashMap<String, String> postParams = new LinkedHashMap<>();
                JSONObject postParams = new JSONObject();
                try {
                    postParams.put("userid", userid);
                    postParams.put("username", username);
                    postParams.put("activityid", activityId);

                } catch (JSONException e) {
                    System.out.println("Error in JSON");
                }

                WebApiDataTask webDataFetcher = new WebApiDataTask(MessageActivity.this);
                try {

                    //check whether the msg empty or not

                    String postURL = postParams.toString();//ServerDataRetriever.createPostURL(postParams);
                    webDataFetcher.execute("POST", "likeactivity", postURL);
                } catch (Exception e) {
                    webDataFetcher.cancel(true);
                }
            }
        });

        ivSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = etComment.getText().toString();
                WebApiDataTask webDataFetcher = new WebApiDataTask(MessageActivity.this);
                JSONObject postParams = new JSONObject();

                try {
                    System.out.println("#####  URL : "+msg);
                    String urlTmp = URLEncoder.encode("\"" + msg + "\"", "utf-8");
                    System.out.println("#####  URL 1: "+urlTmp);
                    //LinkedHashMap<String,String> postParams=new LinkedHashMap<>();
                    postParams.put("activityid", activityId);
                    postParams.put("username", username);
                    postParams.put("userid", userid);
                    postParams.put("message", msg);
                    String postURL = postParams.toString();//ServerDataRetriever.createPostURL(postParams);

                    //System.out.println("#### Messages: Onclick url = "+url);
                    webDataFetcher.execute("POST", "createnewmessage", postURL);
                } catch (Exception e) {
                    webDataFetcher.cancel(true);
                }
                WebApiDataTask messageDataFetcher = new WebApiDataTask(MessageActivity.this);
                try {
                    //String url = "http://hospitopedia.com/message/get?activityid="+activityId+"&phone=%221234%22";
                    //System.out.println("#### Messages: Onclick url = "+url);
                    LinkedHashMap<String,String> getParams=new LinkedHashMap<>();
                    getParams.put("activityid", activityId);
                    String getURL = ServerDataRetriever.createGetURL(getParams);
                    messageDataFetcher.execute("GET", "messages", getURL);
                } catch (Exception e) {
                    webDataFetcher.cancel(true);
                }
                etComment.setText("");
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

            }
        });
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    protected void setListenerToViews() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        System.out.println("##### Map is ready now");
        // Add a marker to the activity location
        //latitude = 10;
        //longitude = 100;
        System.out.println("#### INSIDE onMapReady "+latitude+" "+longitude);
        LatLng activityLocation = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(activityLocation).title("Marker in location"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(activityLocation));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(activityLocation, 12));
    }

    public void setMessages(int count, ArrayList<MessageData> msgs) {
        this.messages = msgs;
        System.out.println("#####  setData::Messages");
        for (int i=0; i<msgs.size();i++) {
            System.out.println(" Inside setMessages "+msgs.get(i).getUserName()+" "+msgs.get(i).getMessage());
        }
        this.tvCommentCount.setText("Comments("+count+")");
        this.lvComments.setAdapter(new MessageAdaptor(this, this.layoutIflator, this.messages));
    }

    private void getUserContext() {
        username = UserCredentials.getUserName();
        userid = UserCredentials.getUserId();
    }
}
