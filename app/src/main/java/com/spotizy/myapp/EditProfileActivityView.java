package com.spotizy.myapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public abstract class EditProfileActivityView extends AppCompatActivity {

    protected RelativeLayout rlHeader, rlArrow;
    protected LinearLayout llInfo, llName;
    protected ImageView ivArrow, ivImage;
    protected TextView tvHeader;
    protected EditText etName;
    protected Button btnSave;

    protected void init() {
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

        rlHeader = (RelativeLayout) findViewById(R.id.rlHeader);
        rlArrow = (RelativeLayout) findViewById(R.id.rlArrow);

        llInfo = (LinearLayout) findViewById(R.id.llInfo);
        llName = (LinearLayout) findViewById(R.id.llName);

        ivArrow = (ImageView) findViewById(R.id.ivArrow);
        ivImage = (ImageView) findViewById(R.id.ivImage);

        tvHeader = (TextView) findViewById(R.id.tvHeader);
        etName = (EditText) findViewById(R.id.etName);
        btnSave = (Button) findViewById(R.id.btnSave);

    }

    /**
     * Set responsive dimens to the views from layout.
     */
    private void setSize() {

        LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DeviceResolution.getScreenHeight(this) * 8 / 100);
        rlHeader.setLayoutParams(llparams);

        RelativeLayout.LayoutParams rlparams = new RelativeLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 12 / 100, DeviceResolution.getScreenHeight(this) * 12 / 100);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.035), 0, 0, 0);
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

        llparams = new LinearLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 55 / 100, DeviceResolution.getScreenWidth(this) * 55 / 100);
        llparams.setMargins(0, (int) (DeviceResolution.getScreenHeight(this) * 0.055), 0, (int) (DeviceResolution.getScreenHeight(this) * 0.010));
        ivImage.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.050), (int) (DeviceResolution.getScreenHeight(this) * 0.035), (int) (DeviceResolution.getScreenWidth(this) * 0.050), (int) (DeviceResolution.getScreenHeight(this) * 0.020));
        etName.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 52 / 100, DeviceResolution.getScreenHeight(this) * 8 / 100);
        llparams.setMargins(0, (int) (DeviceResolution.getScreenHeight(this) * 0.060), 0, (int) (DeviceResolution.getScreenHeight(this) * 0.020));
        btnSave.setLayoutParams(llparams);

    }

    /**
     * Set text size for text view, edit text, buttons etc.
     */
    private void setTextSize() {
        tvHeader.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.5));
        etName.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.5));
        btnSave.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.5));

    }

    /**
     * Set font type for text in different views.
     *
     * @param activity
     */

    private void setFont(Activity activity) {
        Typeface faceBlack = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Black.ttf");
        tvHeader.setTypeface(faceBlack);
        btnSave.setTypeface(faceBlack);

        Typeface faceLight = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Light.ttf");
        etName.setTypeface(faceLight);

    }
}
