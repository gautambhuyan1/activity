package com.spotizy.myapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
//import com.map.activity.R;
import com.spotizy.myapp.DeviceResolution;

public abstract class ActivityDetailsView extends AppCompatActivity {

    protected GoogleMap mMap;
    protected RelativeLayout rlHeader, rlArrow, rlDetails, rlNameTimeDate, rlShares, rlLikes;
    protected ImageView ivArrow, ivShare, ivLike, ivSeparatorOne, ivSeparatorTwo, ivSend;
    protected TextView tvHeader, tvShareCount, tvLikeCount, tvCityName, tvDetails, tvName, tvDate, tvTime, tvCommentCount;
    protected ListView lvComments;
    protected EditText etComment;

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
        rlDetails = (RelativeLayout) findViewById(R.id.rlDetails);
        rlNameTimeDate = (RelativeLayout) findViewById(R.id.rlNameTimeDate);
        rlShares = (RelativeLayout) findViewById(R.id.rlShares);
        rlLikes = (RelativeLayout) findViewById(R.id.rlLikes);

        ivArrow = (ImageView) findViewById(R.id.ivArrow);
        ivShare = (ImageView) findViewById(R.id.ivShare);
        ivLike = (ImageView) findViewById(R.id.ivLike);
        ivSeparatorOne = (ImageView) findViewById(R.id.ivSeparatorOne);
        ivSeparatorTwo = (ImageView) findViewById(R.id.ivSeparatorTwo);
        ivSend = (ImageView) findViewById(R.id.ivSend);

        tvHeader = (TextView) findViewById(R.id.tvHeader);
        tvShareCount = (TextView) findViewById(R.id.tvShareCount);
        tvLikeCount = (TextView) findViewById(R.id.tvLikeCount);
        tvCityName = (TextView) findViewById(R.id.tvCityName);
        tvDetails = (TextView) findViewById(R.id.tvDetails);
        tvName = (TextView) findViewById(R.id.tvName);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvCommentCount = (TextView) findViewById(R.id.tvCommentCount);

        lvComments = (ListView) findViewById(R.id.lvComments);

        etComment = (EditText) findViewById(R.id.etComment);
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

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins(0, (int) (DeviceResolution.getScreenHeight(this) * 0.020), 0, (int) (DeviceResolution.getScreenHeight(this) * 0.008));
        rlDetails.setLayoutParams(llparams);

        rlparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.030), 0, (int) (DeviceResolution.getScreenWidth(this) * 0.010), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        tvCityName.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.005), 0, (int) (DeviceResolution.getScreenWidth(this) * 0.010), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.LEFT_OF, R.id.rlLikes);
        rlShares.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 5 / 100, DeviceResolution.getScreenWidth(this) * 5 / 100);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.005), 0, (int) (DeviceResolution.getScreenWidth(this) * 0.005), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        ivShare.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.005), 0, (int) (DeviceResolution.getScreenWidth(this) * 0.010), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.RIGHT_OF, R.id.ivShare);
        tvShareCount.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.005), 0, (int) (DeviceResolution.getScreenWidth(this) * 0.030), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rlLikes.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 5 / 100, DeviceResolution.getScreenWidth(this) * 5 / 100);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.015), 0, (int) (DeviceResolution.getScreenWidth(this) * 0.005), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        ivLike.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.005), 0, (int) (DeviceResolution.getScreenWidth(this) * 0.030), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.RIGHT_OF, R.id.ivLike);
        tvLikeCount.setLayoutParams(rlparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.030), 0, (int) (DeviceResolution.getScreenWidth(this) * 0.010), 0);
        tvDetails.setLayoutParams(llparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins(0, (int) (DeviceResolution.getScreenHeight(this) * 0.010), 0, (int) (DeviceResolution.getScreenHeight(this) * 0.020));
        rlNameTimeDate.setLayoutParams(llparams);

        rlparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.030), 0, 0, 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        tvName.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams((int) (DeviceResolution.getScreenWidth(this) * 0.04), (int) (DeviceResolution.getScreenWidth(this) * 0.04));
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.002), 0, (int) (DeviceResolution.getScreenWidth(this) * 0.002), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.RIGHT_OF, R.id.tvName);
        ivSeparatorOne.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlparams.setMargins(0, 0, (int) (DeviceResolution.getScreenWidth(this) * 0.010), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.RIGHT_OF, R.id.ivSeparatorOne);
        tvDate.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams((int) (DeviceResolution.getScreenWidth(this) * 0.04), (int) (DeviceResolution.getScreenWidth(this) * 0.04));
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.002), 0, (int) (DeviceResolution.getScreenWidth(this) * 0.002), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.RIGHT_OF, R.id.tvDate);
        ivSeparatorTwo.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlparams.setMargins(0, 0, (int) (DeviceResolution.getScreenWidth(this) * 0.010), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.RIGHT_OF, R.id.ivSeparatorTwo);
        tvTime.setLayoutParams(rlparams);

        llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.030), 0, (int) (DeviceResolution.getScreenWidth(this) * 0.010), (int) (DeviceResolution.getScreenHeight(this) * 0.010));
        tvCommentCount.setLayoutParams(llparams);

        rlparams = new RelativeLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 75 / 100, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.030), 0, (int) (DeviceResolution.getScreenWidth(this) * 0.010), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        etComment.setLayoutParams(rlparams);

        rlparams = new RelativeLayout.LayoutParams(DeviceResolution.getScreenWidth(this) * 10 / 100, DeviceResolution.getScreenWidth(this) * 10 / 100);
        rlparams.setMargins((int) (DeviceResolution.getScreenWidth(this) * 0.015), 0, (int) (DeviceResolution.getScreenWidth(this) * 0.030), 0);
        rlparams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlparams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        ivSend.setLayoutParams(rlparams);

    }

    /**
     * Set text size for text view, edit text, buttons etc.
     */
    private void setTextSize() {
        tvHeader.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.5));
        tvShareCount.setTextSize((float) (DeviceResolution.getScreenInches(this) * 2.5));
        tvLikeCount.setTextSize((float) (DeviceResolution.getScreenInches(this) * 2.5));
        tvCityName.setTextSize((float) (DeviceResolution.getScreenInches(this) * 2.5));
        tvDetails.setTextSize((float) (DeviceResolution.getScreenInches(this) * 2.5));
        tvName.setTextSize((float) (DeviceResolution.getScreenInches(this) * 2.5));
        tvDate.setTextSize((float) (DeviceResolution.getScreenInches(this) * 2.5));
        tvTime.setTextSize((float) (DeviceResolution.getScreenInches(this) * 2.5));
        tvCommentCount.setTextSize((float) (DeviceResolution.getScreenInches(this) * 2.8));
        etComment.setTextSize((float) (DeviceResolution.getScreenInches(this) * 3.2));
    }

    /**
     * Set font type for text in different views.
     *
     * @param activity
     */

    private void setFont(Activity activity) {

        Typeface faceBlack = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Black.ttf");
        tvHeader.setTypeface(faceBlack);

        Typeface faceBold = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Bold.ttf");
        tvCityName.setTypeface(faceBold);

        Typeface faceLight = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Light.ttf");
        tvShareCount.setTypeface(faceLight);
        tvLikeCount.setTypeface(faceLight);
        tvDetails.setTypeface(faceLight);
        tvName.setTypeface(faceLight);
        tvDate.setTypeface(faceLight);
        tvTime.setTypeface(faceLight);
        tvCommentCount.setTypeface(faceLight);
        etComment.setTypeface(faceLight);
    }

}
