package com.saifyproduction.callingapp.entreprise;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import android.app.SearchManager;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.saifyproduction.callingapp.R;

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
import com.saifyproduction.callingapp.mysql.Adapter;
import com.saifyproduction.callingapp.mysql.ApiClient;
import com.saifyproduction.callingapp.mysql.ApiInterface;
import com.saifyproduction.callingapp.mysql.Donnees;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class AddEntreprise extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;
    private List<Donnees> petsList;
    ApiInterface apiInterface;
    Adapter.RecyclerViewClickListener listener;
    ProgressBar progressBar;
    Toolbar toolbar;
    FloatingActionButton fbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_entreprise);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        fbtn = findViewById(R.id.fab_add);
        fbtn.setOnClickListener(this);
        progressBar = findViewById(R.id.progres);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listener = new Adapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent intent = new Intent(AddEntreprise.this, Editor.class);

                intent.putExtra("id", petsList.get(position).getId());
                intent.putExtra("nom_entreprise", petsList.get(position).getNom_entreprise());
                intent.putExtra("responsable", petsList.get(position).getResponsable());
                intent.putExtra("phone", petsList.get(position).getPhone());
                intent.putExtra("email", petsList.get(position).getEmail());
                intent.putExtra("type", petsList.get(position).getType());
                intent.putExtra("domicile", petsList.get(position).getDomicile());
                intent.putExtra("latitude", petsList.get(position).getP_latitude());
                intent.putExtra("logitude", petsList.get(position).getP_longitude());
                intent.putExtra("quartier", petsList.get(position).getQuartier());
                intent.putExtra("commune", petsList.get(position).getCommune());
                intent.putExtra("avenue", petsList.get(position).getAvenue());
                intent.putExtra("numero_home", petsList.get(position).getNumero_home());
                intent.putExtra("date_save", petsList.get(position).getDate_save());
                intent.putExtra("picture", petsList.get(position).getPicture());
                startActivity(intent);

            }

            @Override
            public void onLoveClick(View view, int position) {

                final int id = petsList.get(position).getId();
                final Boolean love = petsList.get(position).getLove();
                final ImageView mLove = view.findViewById(R.id.love);

                if (love){
                    mLove.setImageResource(R.drawable.like_of);
                    petsList.get(position).setLove(false);
                    updateLove("update_love", id, false);
                    adapter.notifyDataSetChanged();
                } else {
                    mLove.setImageResource(R.drawable.ic_likeon);
                    petsList.get(position).setLove(true);
                    updateLove("update_love", id, true);
                    adapter.notifyDataSetChanged();
                }

            }
        };
        toolbar = (Toolbar)findViewById(R.id.toolbar_tech);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Position de l'entreprise");
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

                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
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
        getPets();
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
    public void getPets(){
        Call<List<Donnees>> call = apiInterface.getSos();
        call.enqueue(new Callback<List<Donnees>>() {
            @Override
            public void onResponse(Call<List<Donnees>> call, Response<List<Donnees>> response) {
                progressBar.setVisibility(View.GONE);
                petsList = response.body();
                Log.i(AddEntreprise.class.getSimpleName(), response.body().toString());
                adapter = new Adapter(petsList, AddEntreprise.this, listener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Donnees>> call, Throwable t) {
                Toast.makeText(AddEntreprise.this, "rp :"+t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateLove(final String key, final int id, final Boolean love){

        Call<Donnees> call = apiInterface.updateLove(key, id, love);

        call.enqueue(new Callback<Donnees>() {
            @Override
            public void onResponse(Call<Donnees> call, Response<Donnees> response) {
                Log.i(AddEntreprise.class.getSimpleName(), "Response "+response.toString());
                String value = response.body().getValue();
                String message = response.body().getMassage();
                if (value.equals("1")){
                    Toast.makeText(AddEntreprise.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddEntreprise.this, message, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Donnees> call, Throwable t) {
                Toast.makeText(AddEntreprise.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}