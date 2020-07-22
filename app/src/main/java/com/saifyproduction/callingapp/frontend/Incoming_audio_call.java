package com.saifyproduction.callingapp.frontend;

import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.location.LocationListener;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.saifyproduction.callingapp.R;
import com.saifyproduction.callingapp.backend.AudioPlayer;
import com.saifyproduction.callingapp.backend.BaseActivity;
import com.saifyproduction.callingapp.backend.SinchService;
import com.saifyproduction.callingapp.mapbox.PlaceFragment;
import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallEndCause;
import com.sinch.android.rtc.calling.CallListener;
import com.sinch.android.rtc.video.VideoCallListener;

import java.util.List;

public class Incoming_audio_call extends BaseActivity {

    static final String TAG = Incoming_audio_call.class.getSimpleName();
    private String mCallId;
    private AudioPlayer mAudioPlayer;

    ImageView callattend,callreject, callmsg ;
    TextView remote_LntLong1;
    TextView ur_number ;

    PlaceFragment placeFragment;
    LatLng point;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_audio_call);

        placeFragment = new PlaceFragment();


        callattend = (ImageView) findViewById(R.id.attend_call);
        callreject = (ImageView) findViewById(R.id.reject_call);
        callmsg = (ImageView) findViewById(R.id.call_msg);
        //Location number
        remote_LntLong1 = ( TextView ) findViewById( R.id.remote_LntLong);

        ur_number = ( TextView ) findViewById( R.id.otheruser);

        callmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                int frgm = v.getId();
                switch (frgm){
                    case R.id.call_msg : fragment = new PlaceFragment();
                    break;
                }
            }
        });
        callattend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerClicked();
            }
        });
        callreject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                declineClicked();
            }
        });
        mAudioPlayer = new AudioPlayer(this);
        mAudioPlayer.playRingtone();
        mCallId = getIntent().getStringExtra(SinchService.CALL_ID);
        /*
        placeFragment.onMapClick(mCallId = getIntent().getStringExtra(SinchService.CALL_ID));

        Point destinationPoint = Point.fromLngLat(point.getLongitude(), point.getLatitude());
        Point originPoint = Point.fromLngLat(locationComponent.getLastKnownLocation().getLongitude(),
                locationComponent.getLastKnownLocation().getLatitude());
         */
        //mCallId = getIntent().getStringExtra(SinchService.CALL_ID + remote_LntLong1.setText(String.valueOf(point.getLatitude()+point.getLongitude())));

    }

    @Override
    protected void onServiceConnected() {
        Call call = getSinchServiceInterface().getCall(mCallId);
        if (call != null) {
            call.addCallListener(new SinchCallListener());
            ur_number.setText( getSinchServiceInterface().getCall(mCallId).getRemoteUserId() );


        } else {
            Log.e(TAG, "Started with invalid callId, aborting");
            finish();
        }
    }



    private void answerClicked() {

        mAudioPlayer.stopRingtone();
        Call call = getSinchServiceInterface().getCall(mCallId);
        if (call != null) {
            call.answer();
            finish();
            Intent intent = new Intent(this, AudioCallScreen.class);
            intent.putExtra(SinchService.CALL_ID, mCallId);
            startActivity(intent);
        } else {
            finish();
        }
    }

    private void declineClicked() {


        mAudioPlayer.stopRingtone();
        Call call = getSinchServiceInterface().getCall(mCallId);
        if (call != null) {
            call.hangup();
        }
        finish();
    }

    private class SinchCallListener implements CallListener {

        @Override
        public void onCallEnded(Call call) {
            CallEndCause cause = call.getDetails().getEndCause();
            Log.d(TAG, "Call ended, cause: " + cause.toString());
            mAudioPlayer.stopRingtone();
            finish();
        }

        @Override
        public void onCallEstablished(Call call) {
            Log.d(TAG, "Call established");
        }

        @Override
        public void onCallProgressing(Call call) {
            Log.d(TAG, "Call progressing");

            mAudioPlayer.playRingtone();
        }

        @Override
        public void onShouldSendPushNotification(Call call, List<PushPair> pushPairs) {
            // Send a push through your push provider here, e.g. GCM
        }
    }
}
