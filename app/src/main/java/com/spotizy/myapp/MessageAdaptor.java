package com.spotizy.myapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.spotizy.myapp.DeviceResolution;

import java.util.ArrayList;

/**
 * Created by Gautam_Bhuyan on 1/10/2016.
 */
public class MessageAdaptor extends BaseAdapter implements View.OnClickListener {
    private Activity msgActivity;
    private ArrayList<MessageData> messages;
    private LayoutInflater layoutIflater;


    public MessageAdaptor(Activity m, LayoutInflater lytIflator, ArrayList<MessageData> msg) {
        this.msgActivity = m;
        this.messages = msg;
        this.layoutIflater = lytIflator;
    }

    private class MessageHolder {
        protected String userId;
        protected String message;
        protected TextView userArea;
        protected TextView messageArea;
        protected LinearLayout llItems;
        protected RelativeLayout rlNameDate;
        protected TextView tvName,tvDate,tvDetails;
        protected ImageView ivRoundSeparator;
    }

    public int getCount() {
        return this.messages.size();
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
        MessageHolder mHolder;
        if (convert == null) {
            convert = layoutIflater.inflate(R.layout.message_detail, parent, false);
            mHolder = new MessageHolder();
            mHolder.userArea = (TextView) convert.findViewById(R.id.tvName);
            mHolder.messageArea = (TextView) convert.findViewById(R.id.tvDetails);
            mHolder.userArea.setTag(mHolder);
            mHolder.messageArea.setTag(mHolder);
            mHolder.llItems = (LinearLayout) convert.findViewById(R.id.llItems);
            mHolder.rlNameDate = (RelativeLayout) convert.findViewById(R.id.rlNameDate);

            mHolder.ivRoundSeparator = (ImageView) convert.findViewById(R.id.ivRoundSeparator);

            //tvDetails = (TextView) convert.findViewById(R.id.tvDetails);
            //tvDetails = (TextView) convert.findViewById(R.id.tvDetails);
            //tvName = (TextView) convert.findViewById(R.id.tvName);
            mHolder.tvDate = (TextView) convert.findViewById(R.id.tvDate);
            //gHolder.selectedGroup.setOnClickListener(this);
            convert.setTag(mHolder);
        }
        else {
            mHolder = (MessageHolder)convert.getTag();
        }


        //convert.setOnClickListener(this);
        //gHolder.selectedGroup.setOnClickListener(this);

        MessageData  this_message = messages.get(pos);
        mHolder.userId = this_message.getUserName();
        mHolder.message = new String(this_message.getMessage());
        mHolder.userArea.setText(mHolder.userId);
        mHolder.messageArea.setText(mHolder.message);

        System.out.println("##### Message ="+mHolder.userId+" "+mHolder.message);

        setSize(mHolder);
        setTextSize(mHolder);

        return convert;

    }

    public void onClick(View v) {
        //int groupId = ((gHolder.selectedGroup)v.getTag()).groupId;
        MessageHolder g = (MessageHolder)v.getTag();
        System.out.println("#####    Clicked on button"+g.userId+" "+g.message);

    }


    /**
     * Set responsive dimens to the views from layout.
     */
    private void setSize(MessageHolder m) {

        LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        m.llItems.setLayoutParams(llparams);

        RelativeLayout.LayoutParams rlparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(msgActivity) * 0.030), (int) (DeviceResolution.getScreenHeight(msgActivity) * 0.010), (int) (DeviceResolution.getScreenWidth(msgActivity) * 0.010), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        m.userArea.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(DeviceResolution.getScreenWidth(msgActivity) * 1 / 100, DeviceResolution.getScreenWidth(msgActivity) * 1 / 100);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(msgActivity) * 0.005), (int) (DeviceResolution.getScreenHeight(msgActivity) * 0.020), (int) (DeviceResolution.getScreenWidth(msgActivity) * 0.010), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.RIGHT_OF,R.id.tvName);
        m.ivRoundSeparator.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(msgActivity) * 0.005), (int) (DeviceResolution.getScreenHeight(msgActivity) * 0.020), (int) (DeviceResolution.getScreenWidth(msgActivity) * 0.010), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.RIGHT_OF,R.id.ivRoundSeparator);
        m.tvDate.setLayoutParams(rlparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(msgActivity) * 0.030), 0, (int) (DeviceResolution.getScreenWidth(msgActivity) * 0.030), (int) (DeviceResolution.getScreenHeight(msgActivity) * 0.012));
        m.messageArea.setLayoutParams(llparams);

    }

    /**
     * Set text size for text view, edit text, buttons etc.
     */
    private void setTextSize(MessageHolder m) {

        m.userArea.setTextSize((float) (DeviceResolution.getScreenInches(msgActivity) * 2.8));
        m.tvDate.setTextSize((float) (DeviceResolution.getScreenInches(msgActivity) * 2.2));
        m.messageArea.setTextSize((float) (DeviceResolution.getScreenInches(msgActivity) * 2.5));

    }
}
