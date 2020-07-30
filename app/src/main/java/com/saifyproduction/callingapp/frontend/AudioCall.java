package com.saifyproduction.callingapp.frontend;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.saifyproduction.callingapp.R;
import com.saifyproduction.callingapp.backend.BaseActivity;
import com.saifyproduction.callingapp.backend.SinchService;
import com.saifyproduction.callingapp.choise_sos.ShoisTypeSosFragment;
import com.sinch.android.rtc.SinchError;
import com.sinch.android.rtc.calling.Call;

public class AudioCall extends BaseActivity implements SinchService.StartFailedListener, ShoisTypeSosFragment.SingleAppelsListSOS {
    String result;
    EditText call_no;
    FloatingActionButton call_btn;
    String number;
    String self, location;
    ProgressDialog progress;

    TextView ur_number1,ur_number2;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    TextView ur_sos1;
    LocationManager lm;
    MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_call);


        DialogFragment sigleChoiceDialog = new ShoisTypeSosFragment();
        sigleChoiceDialog.setCancelable(false);
        sigleChoiceDialog.show(getSupportFragmentManager(), "TYPE D'APPEL SOS");
        builder = new AlertDialog.Builder(this);

        ur_sos1 = (TextView) findViewById(R.id.ur_sos);
        ur_number1 = (TextView) findViewById(R.id.ur_num);
        call_no = (EditText) findViewById(R.id.call_number);
        ur_number2 = (TextView) findViewById(R.id.text_location);

        progress = new ProgressDialog(this);

        call_btn = (FloatingActionButton) findViewById(R.id.callbtn);

        progress.setMessage("Please wait .....");
        progress.setCancelable(false);
        progress.show();

    //////////////////////////////////////////////////////
        if (getIntent().hasExtra("number") && getIntent().hasExtra("location")) {
            /**
             * Here Number colling
             */
            self = getIntent().getStringExtra("number");
            /**
             * Here location
             */
            location = getIntent().getStringExtra("location");

            ur_number1.setText("Login as : " + self);
            ur_number2.setText("The incoming call is located from  : " + location);

        } else {
            self = "0";
            location = "0";
        }

        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (call_no.getText().toString().isEmpty()) {

                    Toast.makeText(AudioCall.this, "Empty Phone Number not allowed",
                            Toast.LENGTH_LONG).show();

                }

                else if( self.equalsIgnoreCase( call_no.getText().toString().trim() )  ){

                    Toast.makeText(AudioCall.this, "You can't call yourself",
                            Toast.LENGTH_LONG).show();



                }

                else {
                    /*
                     Call call = getSinchServiceInterface().callUser(userName, headers);
                    String callId = call.getCallId();
                     */


                    //Location Listener
                    number = call_no.getText().toString().trim();
                    Call call = getSinchServiceInterface().callUseraudio(number);
                    final String callId = call.getCallId();

                    Intent callScreen = new Intent(AudioCall.this, AudioCallScreen.class);
                    callScreen.putExtra(SinchService.CALL_ID, callId);
                    callScreen.putExtra("self", self);
                    callScreen.putExtra("number", number);


                    startActivity(callScreen);
                }
            }
        });
    }

    @Override
    protected void onServiceConnected() {
        // mLoginButton.setEnabled(true);
        getSinchServiceInterface().setStartListener(this);

        Toast.makeText(AudioCall.this, "Server connected", Toast.LENGTH_LONG).show();
        progress.dismiss();
    }
    @Override
    public void onStartFailed(SinchError error) {

    }

    @Override
    public void onStarted() {
    }


    @Override
    public void onPositionButtonCliked(String[] list, int position) {
        ur_sos1.setText("" + list [position]);
    }

    @Override
    public void onPositionNegatifClicked() {
        ur_sos1.setText("ANNULER L'OPERATION");
    }
    /**
     * Cliquer sur le buton de localisation
     *
     * }else if( location.equalsIgnoreCase( call_no.getText().toString().trim() )  ){
     *
     *                     Toast.makeText(AudioCall.this, "You can't call yourself",
     *                             Toast.LENGTH_LONG).show();
     */
}
