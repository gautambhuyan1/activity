package com.spotizy.myapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gautam_Bhuyan on 5/29/2017.
 */
public class InterestList extends BaseAdapter implements View.OnClickListener {
    private Activity groupActivity;
    private ArrayList<String> interestList;
    private LayoutInflater layoutIflater;



    public InterestList(Activity a, LayoutInflater lytIflator, ArrayList<String> aL) {
        this.groupActivity = a;
        this.interestList = aL;
        this.layoutIflater = lytIflator;
    }

    private class InterestHolder {
        protected String interestId;
        protected ImageView ivArrow;
        protected LinearLayout llItems;
        protected TextView tvItemName;
        protected View vwDivider;
    }

    public int getCount() {
        return this.interestList.size();
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
        InterestHolder iHolder;
        System.out.println("###### Alive till here.");
        if (convert == null) {
            convert = layoutIflater.inflate(R.layout.activity_listview10, parent, false);
            iHolder = new InterestHolder();
            //aHolder.selectedActivity = (Button)convert.findViewById(R.id.selected_activity);

            //gHolder.selectedGroup.setOnClickListener(this);
            convert.setTag(iHolder);
            //iHolder.ivArrow = (ImageView) convert.findViewById(R.id.ivArrow);
            iHolder.tvItemName = (TextView) convert.findViewById(R.id.tvItemName);
            iHolder.llItems = (LinearLayout) convert.findViewById(R.id.llItems);
            //iHolder.vwDivider = (View) convert.findViewById(R.id.vwDivider);
            iHolder.llItems.setTag(iHolder);
        }
        else {
            iHolder = (InterestHolder) convert.getTag();
        }


        convert.setOnClickListener(this);
        iHolder.llItems.setOnClickListener(this);

        String i = interestList.get(pos);
        iHolder.interestId = i;
        iHolder.tvItemName.setText(i);

        System.out.println("##### Holder = "+pos+" "+i+" "+iHolder.toString());
        //System.out.println("##### Holder ="+i+" "+iHolder.toString());

        setSize(iHolder);
        setTextSize(iHolder);
        setFont(iHolder);

        return convert;

    }

    public void onClick(View v) {
        //int groupId = ((gHolder.selectedGroup)v.getTag()).groupId;
        InterestHolder a = (InterestHolder) v.getTag();
        String interestId = a.interestId;
        //String jsonInterest = "[\""+interestId+"\"]";

        System.out.println("##### INTERESTID "+ interestId);
        ((CreateActivity)groupActivity).setInterestItem(interestId);
    }

    protected void setSize(InterestHolder a) {

        LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (DeviceResolution.getScreenHeight(groupActivity) * 0.070));
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(groupActivity) * 0.040),(int) (DeviceResolution.getScreenHeight(groupActivity) * 0.010), (int) (DeviceResolution.getScreenWidth(groupActivity) * 0.040), (int) (DeviceResolution.getScreenHeight(groupActivity) * 0.010));
        a.llItems.setLayoutParams(llparams);
        llparams = new LinearLayout.LayoutParams((int) (DeviceResolution.getScreenWidth(groupActivity) * 0.050), (int) (DeviceResolution.getScreenWidth(groupActivity) * 0.050));
        //a.ivArrow.setLayoutParams(llparams);
        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (DeviceResolution.getScreenHeight(groupActivity) * 0.001));
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(groupActivity) * 0.040), 0, (int) (DeviceResolution.getScreenWidth(groupActivity) * 0.040), 0);
        //a.vwDivider.setLayoutParams(llparams);
    }

    protected void setTextSize(InterestHolder a) {
        a.tvItemName.setTextSize((float) (DeviceResolution.getScreenInches(groupActivity) * 3.8));

    }


    private void setFont(InterestHolder a) {
        Typeface faceLight = Typeface.createFromAsset(groupActivity.getAssets(), "fonts/Roboto-Light.ttf");
        a.tvItemName.setTypeface(faceLight);

    }
}
