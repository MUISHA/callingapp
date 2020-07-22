package com.saifyproduction.callingapp.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.saifyproduction.callingapp.R;

public class Registre extends AppCompatActivity implements View.OnClickListener {
    EditText e1,e2,e3,e4,e5;
    Button _registre_btn,_login_regir, _forget_pass_regi;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registre);

        e1 = (EditText)findViewById(R.id.regi_username);
        e2 = (EditText)findViewById(R.id.regi_email);
        e3 = (EditText)findViewById(R.id.regi_numbphon);
        e4 = (EditText)findViewById(R.id.regi_password);
        e5 = (EditText)findViewById(R.id.regi_cpassword);
        _login_regir = (Button)findViewById(R.id.registre_login);
        _login_regir.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int index = v.getId();
        switch (index){
            case R.id.registre_login :startActivity(new Intent(Registre.this, Loding.class)); finish();
                break;
        }
    }
}
