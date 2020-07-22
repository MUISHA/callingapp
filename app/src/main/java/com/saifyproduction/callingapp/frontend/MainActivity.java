package com.saifyproduction.callingapp.frontend;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.saifyproduction.callingapp.R;
import com.saifyproduction.callingapp.backend.BaseActivity;
import com.saifyproduction.callingapp.backend.SinchService;
import com.saifyproduction.callingapp.mapbox.PlaceFragment;
import com.sinch.android.rtc.SinchError;


public class MainActivity extends BaseActivity implements SinchService.StartFailedListener {

    EditText phonetxt;
    FloatingActionButton loginbtn;
    String number ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        phonetxt = ( EditText ) findViewById ( R.id.phonetxt );
        loginbtn = ( FloatingActionButton )   findViewById ( R.id.loginbtn );

        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED  || ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.READ_PHONE_STATE},100);
            }

        }


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( phonetxt.getText().toString().isEmpty() ) {

                    Toast.makeText(  MainActivity.this  , "Empty Phone Number not allowed",
                            Toast.LENGTH_LONG).show();

                }
                else{
                    number = phonetxt.getText().toString().trim();
                    if ( ! getSinchServiceInterface().isStarted() ) {

                        getSinchServiceInterface().startClient( number );
                        Toast.makeText(MainActivity.this,"Server Start Working ..... ",Toast.LENGTH_SHORT).show();

                        Intent i = new Intent( MainActivity.this , AudioCall.class );
                        i.putExtra("number", number);
                        startActivity( i );

                    }

                }

            }
        });

    }
    @Override
    public void onStartFailed(SinchError error) {

    }

    @Override
    public void onStarted() {

    }
}
