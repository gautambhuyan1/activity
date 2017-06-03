package com.spotizy.myapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public abstract class MyInterestView extends AppCompatActivity {


    protected RelativeLayout rlHeader, rlArrow;
    protected ImageView ivArrow;
    protected TextView tvHeader;

    protected void init() {
        initView();
        setSize();
        setTextSize();
        setFont(this);
        setListenerToViews();
    }

    protected abstract void setListenerToViews();

    /**
     * Binding layout view with the view variables.
     */
    private void initView() {

        rlHeader = (RelativeLayout) findViewById(R.id.rlHeader);
        rlArrow = (RelativeLayout) findViewById(R.id.rlArrow);
        ivArrow = (ImageView) findViewById(R.id.ivArrow);
        tvHeader = (TextView) findViewById(R.id.tvHeader);

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
    }

    /**
     * Set text size for text view, edit text, buttons etc.
     */
    private void setTextSize() {
        tvHeader.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.5));
    }

    /**
     * Set font type for text in different views.
     *
     * @param activity
     */

    private void setFont(Activity activity) {

        Typeface faceBlack = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Black.ttf");
        tvHeader.setTypeface(faceBlack);

    }
}


