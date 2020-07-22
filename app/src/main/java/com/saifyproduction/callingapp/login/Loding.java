package com.saifyproduction.callingapp.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.saifyproduction.callingapp.R;
import com.saifyproduction.callingapp.frontend.MainActivity;

public class Loding extends AppCompatActivity implements View.OnClickListener {
    Button button,button1,button2;
    RelativeLayout rellay1,rellay2;
    Handler handler = new Handler();
    private  ProgressBar progressBar;
    ProgressDialog dialog;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loding);
        rellay1 = findViewById(R.id.rellay1);
        rellay2 = findViewById(R.id.rellay2);
        handler.postDelayed(runnable,5000);


        button = findViewById(R.id.login_lodin);
        button1 = findViewById(R.id.registre_login);
        button2 = findViewById(R.id.forger_login);
        progressBar=findViewById(R.id.progress);
        button2.setOnClickListener(this);
        button1.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int index = v.getId();
        switch (index){
            case R.id.login_lodin :
                dialog = new ProgressDialog(this);
                dialog.setIndeterminate(true);
                dialog.setTitle("Pleaser some one munites");
                dialog.setMessage("Ouverture dans quelque mulites");
                //dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.registre_login : startActivity(new Intent(this, Registre.class));
                finish();
                break;
            case R.id.forger_login :
                Toast.makeText(this, "Can you helper you???", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
