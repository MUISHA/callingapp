package com.saifyproduction.callingapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mapbox.mapboxsdk.Mapbox;

public class MapBoxNagigator extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, String.valueOf(R.string.access_token));
        setContentView(R.layout.map_box_nagigator);
    }
}