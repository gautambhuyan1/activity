package com.spotizy.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;


public class EditProfileActivity extends EditProfileActivityView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        init();
    }

    @Override
    protected void setListenerToViews() {

    }
}
