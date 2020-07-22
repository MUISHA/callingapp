package com.saifyproduction.callingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    FloatingActionMenu floatingActionButtonMenu;
    FloatingActionButton _entreprise_surcarton,_entreprise_sos,_place_sos,_calling_sos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        floatingActionButtonMenu = (FloatingActionMenu)findViewById(R.id.floatingActionButtonMenu);

        _entreprise_surcarton = (FloatingActionButton)findViewById(R.id.flaotActionItem_entreprise_surcarton);
        _entreprise_surcarton.setOnClickListener(this);
        _entreprise_sos = (FloatingActionButton)findViewById(R.id.flaotActionItem_entreprise_sos);
        _entreprise_sos.setOnClickListener(this);
        _place_sos = (FloatingActionButton)findViewById(R.id.flaotActionItem_place_sos);
        _place_sos.setOnClickListener(this);
        _calling_sos = (FloatingActionButton)findViewById(R.id.flaotActionItem_calling_sos);
        _calling_sos.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
