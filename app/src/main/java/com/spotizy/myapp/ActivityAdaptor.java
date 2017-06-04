package com.spotizy.myapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gautam_Bhuyan on 1/22/2016.
 */
public class ActivityAdaptor extends BaseAdapter implements View.OnClickListener {
    private Activity groupActivity;
    private ArrayList<ActivityData> activityList;
    private LayoutInflater layoutIflater;



    public ActivityAdaptor(Activity a, LayoutInflater lytIflator, ArrayList<ActivityData> aL) {
        this.groupActivity = a;
        this.activityList = aL;
        this.layoutIflater = lytIflator;
    }

    private class ActivityHolder {
        public String interestId;
        public String activityId;
        public double latitude;
        public double longitude;
        public String activityName;
        public String place;
        public String time;
        public String date;
        //public Button selectedActivity;
        protected TextView tvDateTime, tvDate, tvTime, tvActivityName, tvEventName;
        protected ImageView ivEvent;
        protected LinearLayout llMain;
        protected View vwDivider, vwSeperator;
    }

    public int getCount() {
        return this.activityList.size();
    }

    public boolean areAllItemsEnabled() {
        return true;
    }

    public Object getItem(int arg) {
        return null;
    }

    public long getItemId(int pos) {
        return pos;
    }

    public View getView(int pos, View convert, ViewGroup parent) {
        ActivityHolder aHolder;
        if (convert == null) {
            convert = layoutIflater.inflate(R.layout.activity_listview08, parent, false);
            aHolder = new ActivityHolder();
            //aHolder.selectedActivity = (Button)convert.findViewById(R.id.selected_activity);

            //gHolder.selectedGroup.setOnClickListener(this);
            convert.setTag(aHolder);
            aHolder.tvDate = (TextView) convert.findViewById(R.id.tvDate);
            aHolder.tvTime = (TextView) convert.findViewById(R.id.tvTime);
            aHolder.vwSeperator = (View) convert.findViewById(R.id.vwSeperator);
            aHolder.tvActivityName = (TextView) convert.findViewById(R.id.tvActivityName);
            aHolder.tvEventName = (TextView) convert.findViewById(R.id.tvEventName);
            aHolder.ivEvent = (ImageView) convert.findViewById(R.id.ivEvent);
            aHolder.llMain = (LinearLayout) convert.findViewById(R.id.llMain);
            aHolder.vwDivider = (View) convert.findViewById(R.id.vwDivider);
            aHolder.llMain.setTag(aHolder);
        }
        else {
            aHolder = (ActivityHolder)convert.getTag();
        }


        convert.setOnClickListener(this);
        aHolder.llMain.setOnClickListener(this);

        ActivityData a = activityList.get(pos);
        aHolder.interestId = a.getInterestId();
        aHolder.activityId = a.getActivityId();
        aHolder.activityName = a.getActivityName();
        aHolder.latitude = a.getLatitude();
        aHolder.longitude = a.getLongitude();
        aHolder.place = a.getPlace();
        aHolder.time = a.getTime();
        aHolder.date = a.getDate();
        aHolder.tvActivityName.setText(a.getInterestId());
        aHolder.tvEventName.setText(a.getActivityName());

        System.out.println("##### Holder = "+pos+" "+a.getActivityName()+" "+aHolder.toString());

        setSize(aHolder);
        setTextSize(aHolder);
        setFont(aHolder);

        return convert;

    }

    public void onClick(View v) {
        //int groupId = ((gHolder.selectedGroup)v.getTag()).groupId;
        ActivityHolder a = (ActivityHolder)v.getTag();
        System.out.println("#####    Clicked on button"+a.toString()+" "+a.activityId);
        //Intent intent = new Intent(Intent.ACTION_VIEW);
        Intent intent = new Intent(groupActivity.getApplicationContext(), MessageActivity.class);
        //intent.setClass("com.spotizy.chat", "com.spotizy.chat.MessageActivity");
        intent.putExtra("interest", a.interestId);
        intent.putExtra("activityid", a.activityId);
        intent.putExtra("activityname", a.activityName);
        intent.putExtra("latitude", a.latitude);
        intent.putExtra("longitude", a.longitude);
        intent.putExtra("place", a.place);
        intent.putExtra("time", a.time);
        intent.putExtra("date", a.date);
        this.groupActivity.startActivity(intent);
    }


    protected void setSize(ActivityHolder a) {

        LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(groupActivity) * 0.040), (int) (DeviceResolution.getScreenHeight(groupActivity) * 0.020), (int) (DeviceResolution.getScreenWidth(groupActivity) * 0.040), 0);
        a.llMain.setLayoutParams(llparams);
        llparams = new LinearLayout.LayoutParams((int) (DeviceResolution.getScreenWidth(groupActivity) * 0.045), (int) (DeviceResolution.getScreenWidth(groupActivity) * 0.045));
        llparams.setMargins(0, (int) (DeviceResolution.getScreenHeight(groupActivity) * 0.010), 0, 0);
        a.ivEvent.setLayoutParams(llparams);
        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (DeviceResolution.getScreenHeight(groupActivity) * 0.001));
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(groupActivity) * 0.040), (int) (DeviceResolution.getScreenHeight(groupActivity) * 0.020), (int) (DeviceResolution.getScreenWidth(groupActivity) * 0.040), 0);
        a.vwDivider.setLayoutParams(llparams);
        llparams = new LinearLayout.LayoutParams((int) (DeviceResolution.getScreenWidth(groupActivity) * 0.004), (int) (DeviceResolution.getScreenHeight(groupActivity) * 0.023));
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(groupActivity) * 0.020), 0, (int) (DeviceResolution.getScreenWidth(groupActivity) * 0.020), 0);
        a.vwSeperator.setLayoutParams(llparams);
    }

    protected void setTextSize(ActivityHolder a) {
        a.tvDate.setTextSize((float) (DeviceResolution.getScreenInches(groupActivity) * 3.3));
        a.tvActivityName.setTextSize((float) (DeviceResolution.getScreenInches(groupActivity) * 3.3));
        a.tvEventName.setTextSize((float) (DeviceResolution.getScreenInches(groupActivity) * 3.3));
        a.tvTime.setTextSize((float) (DeviceResolution.getScreenInches(groupActivity) * 3.3));

    }

    private void setFont(ActivityHolder a) {
        Typeface faceLight = Typeface.createFromAsset(groupActivity.getAssets(), "fonts/Roboto-Light.ttf");
        a.tvEventName.setTypeface(faceLight);
        a.tvActivityName.setTypeface(faceLight);
        a.tvTime.setTypeface(faceLight);
        a.tvDate.setTypeface(faceLight);

    }
}
