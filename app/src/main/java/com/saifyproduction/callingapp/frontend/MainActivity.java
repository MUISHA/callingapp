package com.saifyproduction.callingapp.frontend;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.Address;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.saifyproduction.callingapp.R;
import com.saifyproduction.callingapp.backend.BaseActivity;
import com.saifyproduction.callingapp.backend.SinchService;
import com.saifyproduction.callingapp.mapbox.PlaceFragment;
import com.sinch.android.rtc.SinchError;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends BaseActivity implements SinchService.StartFailedListener, LocationListener {

    private EditText phonetxt;
    private TextView mTextViewLocation;
    private FloatingActionButton loginbtn;
    String number,number1;
    private LocationManager lm;
    private static final int REQUEST_PERM_CODE = 12345;
    //private String StockLocation = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        phonetxt = (EditText) findViewById(R.id.phonetxt);
        mTextViewLocation = (TextView) findViewById(R.id.phonetxt);

        loginbtn = (FloatingActionButton) findViewById(R.id.loginbtn);

        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.READ_PHONE_STATE}, 100);
            }

        }


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (phonetxt.getText().toString().isEmpty() && mTextViewLocation.getText().toString().isEmpty()) {

                    Toast.makeText(MainActivity.this, "Empty Phone Number not allowed",
                            Toast.LENGTH_LONG).show();

                } else {
                    number = phonetxt.getText().toString().trim();

                    if (!getSinchServiceInterface().isStarted()) {

                        getSinchServiceInterface().startClient(number);
                        Toast.makeText(MainActivity.this, "Server Start Working ..... ", Toast.LENGTH_SHORT).show();



                        Intent i = new Intent(MainActivity.this, AudioCall.class);
                        i.putExtra("number", number);
                        startActivity(i);

                    }

                }

            }
        });

    }

    @Override
    public void onStartFailed(SinchError error) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERM_CODE) {
            Chekpermission();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (lm != null) {
            lm.removeUpdates(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getSinchServiceInterface() != null) {
            getSinchServiceInterface().stopClient();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Chekpermission();
    }

    private void Chekpermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
        }
        if (lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 10000, 0, this);
        }
        if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, this);
        }
        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onLocationChanged(Location location) {
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> address = null;

        try{
            address = geocoder.getFromLocation(latitude, longitude,1);
            String listAdress = address.get(0).getAddressLine(0);

            StockLocation = String.valueOf(listAdress);

        }catch (IOException exp){
            exp.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
