package com.saifyproduction.callingapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.saifyproduction.callingapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class AddEntreprise extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;
    Toolbar toolbar;
    private FloatingActionButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_entreprise);
        button = findViewById(R.id.fab_add);
        button.setOnClickListener(this);
        progressBar = findViewById(R.id.progres);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        toolbar = (Toolbar)findViewById(R.id.toolbar_tech);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add la position de l'entreprise");
        if (toolbar != null){
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setQueryHint("Search SOS...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                //adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //adapter.getFilter().filter(newText);
                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int index = item.getItemId();
        switch (index){
            case R.id.action_settings :
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_app :
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //getPets();
    }

    @Override
    public void onClick(View v)
    {
        int index = v.getId();
        switch (index)
        {
            case R.id.fab_add : startActivity(new Intent(this, Editor.class));
                break;
            default:
                break;
        }
    }
}