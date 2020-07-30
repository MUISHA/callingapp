package com.saifyproduction.callingapp.entreprise;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.saifyproduction.callingapp.R;
import com.saifyproduction.callingapp.mysql.ApiClient;
import com.saifyproduction.callingapp.mysql.ApiInterface;
import com.saifyproduction.callingapp.mysql.Donnees;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class Editor extends AppCompatActivity implements View.OnClickListener{

    private Spinner spinner_domicile,spinner_quartie,spinner_commune,spinner_type;
    private CircleImageView mPicture;
    private FloatingActionButton mFabChoosePic;
    Calendar myCalendar = Calendar.getInstance();
    /**
     * Declaration des variale pour domicile de l'entreprise
     */
    private int mGender_domilcile = 0;
    public static final int GENDER_LIEU = 0;
    public static final int GENDER_DOMICILE = 1;
    public static final int GENDER_BUREAU = 2;
    /**
     * Fin Declaration des variale pour domicile de l'entreprise
     */
    /**
     * Declaration des variale pour Commune de l'entreprise
     */
    private int mGender_commune = 0;
    public static final int GENDER_COMM = 0;
    public static final int GENDER_GOMA = 1;
    public static final int GENDER_KARISIMBI = 2;
    /**
     * Fin Declaration des variale pour commune de l'entreprise
     */
    /**
     * Declaration des variale pour Quartier de l'entreprise
     */
    //Debut Goma
    private int mGender_quartier = 0;
    public static final int GENDER_QUART = 0;
    public static final int GENDER_LESVOLCAN = 1;
    public static final int GENDER_HIMBIE = 2;
    public static final int GENDER_KYESHERO = 3;
    public static final int GENDER_LACVERT = 4;
    public static final int GENDER_MIKENO = 5;
    //Fin Goma
    //Debut Karisimbi
    public static final int GENDER_KAHEMBE = 6;
    public static final int GENDER_MURARA = 7;
    public static final int GENDER_VIRUNGA = 8;
    public static final int GENDER_MAJENGO = 9;
    public static final int GENDER_MABANG_S = 10;
    public static final int GENDER_MABANG_N = 11;
    public static final int GENDER_NDO = 12;
    public static final int GENDER_MUNGUNG = 13;
    public static final int GENDER_KATINDO_a = 14;
    public static final int GENDER_KATINDO_b = 15;
    //Fin Karisimbi
    /**
     * Fin Declaration des variale pour commune de l'entreprise
     */
    /**
     * Déclaration type d'entreprise
     */
    //Début type d'entreprise
    private int mGender_type = 0;
    public static final int GENDER_TYPE = 0;
    public static final int GENDER_SANITAIRE = 1;
    public static final int GENDER_PARAETA = 2;
    public static final int GENDER_ETATIQUE = 3;
    public static final int GENDER_PRIVEE = 4;
    public static final int GENDER_LOCAL = 5;
    public static final int GENDER_INTERNATIONAL = 6;
    public static final int GENDER_UNIVERSITAIRE = 7;
    public static final int GENDER_INSTITUT_SUPERIEUR = 8;
    public static final int GENDER_INSTITUT_SECONDAIRE = 9;
    public static final int GENDER_INSTITUT_PRIMAIRE_MATERNEL = 10;
    public static final int GENDER_INSTITUT_PRIMAIRE = 11;
    // Fin type d'entreprise




    private ApiInterface apiInterface;

    private EditText mNentre,mRespo,mPhone,mMail,mLat, mLongi,mNum_parcel,mAvenu, mDate; //Saving EditFragment

    private String entreprise,responsable,phone,mail,nume_home,avenue, date_dat,picture;

    private int id,type,domicile,quartier,commune,num_parcel;

    private float latitudes,longitudes;

    private Menu action;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor);

        mNentre = findViewById(R.id.txt_entreprise);
        mRespo = findViewById(R.id.txt_respon);
        mPhone = findViewById(R.id.txt_phone);
        mMail = findViewById(R.id.txt_email);

        spinner_type = (Spinner) findViewById(R.id.txt_type); // Index
        spinner_domicile = findViewById(R.id.txt_domicile); // Index

        mLat = findViewById(R.id.txt_lati);
        mLongi = findViewById(R.id.txt_long);

        spinner_quartie = findViewById(R.id.txt_quartier);
        spinner_commune = (Spinner) findViewById(R.id.txt_commune);

        mAvenu = findViewById(R.id.txt_avenue);
        mNum_parcel = findViewById(R.id.txt_num_home);
        mDate = findViewById(R.id.txt_date);
        mDate.setFocusableInTouchMode(false);
        mDate.setFocusable(false);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Editor.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mPicture = findViewById(R.id.picture);
        mFabChoosePic= findViewById(R.id.fabChoosePic);
        mFabChoosePic.setOnClickListener(this);


        setupSpinner_Domicile();
        setupSpinner_Commune();
        setupSpinner_Quartier();
        setupSpinner_type();

        setBirth();
        setDataFromIntentExtra();

        //Base de données
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        entreprise = intent.getStringExtra("entreprise");
        responsable = intent.getStringExtra("responsable");
        phone = intent.getStringExtra("phone");
        mail = intent.getStringExtra("mail");
        type = intent.getIntExtra("type", 0); //Index
        domicile = intent.getIntExtra("domicile", 0); //Index

        latitudes = Float.parseFloat(String.valueOf(intent.getIntExtra("latitude", 0)));
        longitudes = Float.parseFloat(String.valueOf(intent.getIntExtra("longitude",  0)));



        quartier = intent.getIntExtra("quartier", 0); //Index
        commune = intent.getIntExtra("commune", 0); //Index

        avenue = intent.getStringExtra("avenue");
        nume_home = intent.getStringExtra("nume_home");
        date_dat = intent.getStringExtra("date_dat");
        picture = intent.getStringExtra("picture");

        //Fin Base de données
    }

    /**
     * Set table
     */
    private void setDataFromIntentExtra() {

        if (id != 0) {
            readMode();
            getSupportActionBar().setTitle("Edit " + entreprise.toString());

            mNentre.setText("entreprise");
            mRespo.setText("responsable");
            mPhone.setText("phone");
            mMail.setText("mail");
            mLat.setText("latitude");
            mLongi.setText("longitude");
            mAvenu.setText("avenue");
            mNum_parcel.setText("nume_home");
            mDate.setText("date_dat");

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.ic_likeon);
            requestOptions.error(R.drawable.ic_likeon);

            Glide.with(Editor.this)
                    .load(picture)
                    .apply(requestOptions)
                    .into(mPicture);

            //Index type
            switch (type) {
                case GENDER_SANITAIRE :
                    spinner_type.setSelection(1);
                    break;
                case GENDER_PARAETA :
                    spinner_type.setSelection(2);
                    break;
                case GENDER_ETATIQUE :
                    spinner_type.setSelection(3);
                    break;
                case GENDER_PRIVEE :
                    spinner_type.setSelection(4);
                    break;
                case GENDER_LOCAL :
                    spinner_type.setSelection(5);
                    break;
                case GENDER_INTERNATIONAL :
                    spinner_type.setSelection(6);
                    break;
                case GENDER_UNIVERSITAIRE :
                    spinner_type.setSelection(7);
                    break;
                case GENDER_INSTITUT_SUPERIEUR :
                    spinner_type.setSelection(8);
                    break;
                case GENDER_INSTITUT_SECONDAIRE :
                    spinner_type.setSelection(9);
                    break;
                case GENDER_INSTITUT_PRIMAIRE_MATERNEL :
                    spinner_type.setSelection(10);
                    break;
                case GENDER_INSTITUT_PRIMAIRE :
                    spinner_type.setSelection(11);
                    break;
                default:
                    spinner_type.setSelection(0);
                    break;
            }
            //Index domicile
            switch (domicile) {
                case GENDER_DOMICILE:
                    spinner_domicile.setSelection(1);
                    break;
                case GENDER_BUREAU:
                    spinner_domicile.setSelection(2);
                    break;
                default:
                    spinner_domicile.setSelection(0);
                    break;
            }
            // Index Quartier
            switch (quartier) {
                case GENDER_LESVOLCAN :
                    spinner_quartie.setSelection(1);
                    break;
                case GENDER_HIMBIE :
                    spinner_quartie.setSelection(2);
                    break;
                case GENDER_KYESHERO :
                    spinner_quartie.setSelection(3);
                    break;
                case GENDER_LACVERT :
                    spinner_quartie.setSelection(4);
                    break;
                case GENDER_MIKENO :
                    spinner_quartie.setSelection(5);
                    break;
                //Fin Goma
                //Debut Karisimbi
                case GENDER_KAHEMBE :
                    spinner_quartie.setSelection(6);
                    break;
                case GENDER_MURARA :
                    spinner_quartie.setSelection(7);
                    break;
                case GENDER_VIRUNGA :
                    spinner_quartie.setSelection(8);
                    break;
                case GENDER_MAJENGO :
                    spinner_quartie.setSelection(9);
                    break;
                case GENDER_MABANG_S :
                    spinner_quartie.setSelection(10);
                    break;
                case GENDER_MABANG_N :
                    spinner_quartie.setSelection(11);
                    break;
                case GENDER_NDO :
                    spinner_quartie.setSelection(12);
                    break;
                case GENDER_MUNGUNG :
                    spinner_quartie.setSelection(13);
                    break;
                case GENDER_KATINDO_a :
                    spinner_quartie.setSelection(14);
                    break;
                case GENDER_KATINDO_b :
                    spinner_quartie.setSelection(15);
                    break;
                default:
                    spinner_quartie.setSelection(0);
                    break;
            }
            // Index Commune
            switch (commune) {
                case GENDER_GOMA:
                    spinner_commune.setSelection(1);
                    break;
                case GENDER_KARISIMBI:
                    spinner_commune.setSelection(2);
                    break;
                default:
                    spinner_commune.setSelection(0);
                    break;
            }
        } else {
            getSupportActionBar().setTitle("Ajouter l'entreprise");
        }
    }
    /**
     * Insert for the data base
     * @param key
     */
    private void postData(final String key) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Enregistre ...");
        progressDialog.show();

        readMode();

        String nomE = mNentre.getText().toString().trim();
        String respo = mRespo.getText().toString().trim();
        String phon = mPhone.getText().toString().trim();;
        String mails = mMail.getText().toString().trim();

        int types = mGender_type; //index
        int domiciles = mGender_domilcile; //index

        float latitud = latitudes;
        float longitud = longitudes;

        int quartiers = mGender_quartier; //index
        int communes = mGender_commune; //index

        String avenus = mAvenu.getText().toString().trim();
        int num_homs = num_parcel;

        String dates = mDate.getText().toString().trim();
        String pictures = null;

        if (bitmap == null) {
            picture = "";
        } else {
            picture = getStringImage(bitmap);
        }

    apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Donnees> call = apiInterface.insertSos(key,nomE,respo ,phon ,mails ,types , domiciles , latitud , longitud , quartiers ,communes , avenus , num_homs , dates , pictures);


        call.enqueue(new Callback<Donnees>() {
            @Override
            public void onResponse(@NotNull Call<Donnees> call, @NotNull Response<Donnees> response) {

                progressDialog.dismiss();

                Timber.tag(Editor.class.getSimpleName()).i(response.toString());

                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMassage();

                if (value.equals("1")){
                    finish();
                } else {
                    Toast.makeText(Editor.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NotNull Call<Donnees> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Editor.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Update for the database
     * @param key
     * @param id
     */
     private void updateData(final String key, final int id) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Modifier ...");
        progressDialog.show();

        readMode();

        String nomE = mNum_parcel.getText().toString().trim();
        String respo =mRespo.getText().toString().trim();
        String phon = mPhone.getText().toString().trim();;
        String mails = mMail.getText().toString().trim();

        int types = mGender_type; //index
        int domiciles = mGender_domilcile; //index

        float latitud = latitudes;
        float longitud = longitudes;

        int quartiers = mGender_quartier; //index
        int communes = mGender_commune; //index

        String avenus = mAvenu.getText().toString().trim();
        int num_homs = num_parcel;

        String dates = mDate.getText().toString().trim();
        String pictures = null;

        if (bitmap == null) {
            picture = "";
        } else {
            picture = getStringImage(bitmap);
        }

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Donnees> call = apiInterface.updateSos(key,id,nomE,respo ,phon ,mails ,types , domiciles , latitud , longitud , quartiers ,
                communes , avenus , num_homs , dates , pictures);


        call.enqueue(new Callback<Donnees>() {
            @Override
            public void onResponse(@NotNull Call<Donnees> call, @NotNull Response<Donnees> response) {

                progressDialog.dismiss();

                Timber.tag(Editor.class.getSimpleName()).i(response.toString());

                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMassage();

                if (value.equals("1")){
                    Toast.makeText(Editor.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Editor.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NotNull Call<Donnees> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Editor.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Delete table
     * @param key
     * @param id
     * @param pic
     */
    private void deleteData(final String key, final int id, final String pic) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Suprimmer ...");
        progressDialog.show();

        readMode();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Donnees> call = apiInterface.deleteSos(key, id, pic);

        call.enqueue(new Callback<Donnees>() {
            @Override
            public void onResponse(Call<Donnees> call, Response<Donnees> response) {

                progressDialog.dismiss();

                Log.i(Editor.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getMassage();

                if (value.equals("1")){
                    Toast.makeText(Editor.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Editor.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Donnees> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Editor.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @SuppressLint("RestrictedApi")
    void readMode(){
        mNentre.setFocusableInTouchMode(false);
        mRespo.setFocusableInTouchMode(false);
        mPhone.setFocusableInTouchMode(false);
        mMail.setFocusableInTouchMode(false);
        spinner_type.setEnabled(false);
        spinner_domicile.setEnabled(false);
        mLat.setFocusableInTouchMode(false);
        mLongi.setFocusableInTouchMode(false);
        spinner_quartie.setEnabled(false);
        spinner_commune.setEnabled(false);
        mAvenu.setFocusableInTouchMode(false);
        mNum_parcel.setFocusableInTouchMode(false);
        mDate.setFocusableInTouchMode(false);
        mFabChoosePic.setVisibility(View.INVISIBLE);

    }
    @SuppressLint("RestrictedApi")
    private void editMode(){
        mNentre.setFocusableInTouchMode(true);
        mRespo.setFocusableInTouchMode(true);
        mPhone.setFocusableInTouchMode(true);
        mMail.setFocusableInTouchMode(true);
        spinner_type.setEnabled(true);
        spinner_domicile.setEnabled(true);
        mLat.setFocusableInTouchMode(true);
        mLongi.setFocusableInTouchMode(true);
        spinner_quartie.setEnabled(true);
        spinner_commune.setEnabled(true);
        mAvenu.setFocusableInTouchMode(true);
        mNum_parcel.setFocusableInTouchMode(true);
        mDate.setFocusableInTouchMode(true);
        mFabChoosePic.setVisibility(View.INVISIBLE);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);
        if (id == 0){
            action.findItem(R.id.menu_edit).setVisible(false);
            action.findItem(R.id.menu_delete).setVisible(false);
            action.findItem(R.id.menu_save).setVisible(true);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.menu_edit:
                //Edit
                editMode();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mNentre, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;
            case R.id.menu_save:
                //Save

                if (id == 0) {
                    //Si ce n'est pas completer alerte total
                    if (TextUtils.isEmpty(mNentre.getText().toString()) ||
                            TextUtils.isEmpty(mRespo.getText().toString()) ||
                            TextUtils.isEmpty(mPhone.getText().toString()) ||
                            TextUtils.isEmpty(mMail.getText().toString()) ||

                            TextUtils.isEmpty(spinner_type.getSelectedItem().toString()) ||
                            TextUtils.isEmpty(spinner_domicile.getSelectedItem().toString()) ||

                            TextUtils.isEmpty(mLat.getText().toString()) ||
                            TextUtils.isEmpty(mLongi.getText().toString()) ||

                            TextUtils.isEmpty(spinner_quartie.getSelectedItem().toString()) ||
                            TextUtils.isEmpty(spinner_commune.getSelectedItem().toString()) ||

                            TextUtils.isEmpty(mAvenu.getText().toString()) ||
                            TextUtils.isEmpty(mNum_parcel.getText().toString())){
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                        alertDialog.setMessage("Please complete the field!");
                        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }
                    else {
                        postData("insert");
                        action.findItem(R.id.menu_edit).setVisible(true);
                        action.findItem(R.id.menu_save).setVisible(false);
                        action.findItem(R.id.menu_delete).setVisible(true);

                        readMode();

                    }

                } else {

                    updateData("update", id);
                    action.findItem(R.id.menu_edit).setVisible(true);
                    action.findItem(R.id.menu_save).setVisible(false);
                    action.findItem(R.id.menu_delete).setVisible(true);

                    readMode();
                }

                return true;
            case R.id.menu_delete:

                AlertDialog.Builder dialog = new AlertDialog.Builder(Editor.this);
                dialog.setMessage("Voullez-Vous supprimer cet entreprise");
                dialog.setPositiveButton("Yes" ,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteData("Supprimer", id, picture);
                    }
                });
                dialog.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                dialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setBirth();
        }

    };

    private void setBirth() {
        String myFormat = "dd MMMM yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        mDate.setText(sdf.format(myCalendar.getTime()));
    }
    @Override
    public void onClick(View v) {
        int index = v.getId();
        switch (index)
        {
            case R.id.fabChoosePic :chooseFile();
                break;

        }
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    private void chooseFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selectionner L'Image"), 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                mPicture.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void setupSpinner_Domicile(){
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_gender_lieu,
                android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_domicile.setAdapter(genderSpinnerAdapter);

        spinner_domicile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_domicile))) {
                        mGender_domilcile = GENDER_DOMICILE;
                    } else if (selection.equals(getString(R.string.gender_bureau))) {
                        mGender_domilcile = GENDER_BUREAU;
                    } else {
                        mGender_domilcile = GENDER_LIEU;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender_domilcile = 0;
            }
        });
    }
    private void setupSpinner_Commune(){
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_gender_commune,
                android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_commune.setAdapter(genderSpinnerAdapter);
        spinner_commune.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.comm_goma))) {
                        mGender_commune = GENDER_GOMA;
                    } else if (selection.equals(getString(R.string.comm_karis))) {
                        mGender_commune = GENDER_KARISIMBI;
                    } else {
                        mGender_commune = GENDER_COMM;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender_commune = 0;
            }
        });
    }
    private void setupSpinner_Quartier(){
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_gender_quartier,
                android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_quartie.setAdapter(genderSpinnerAdapter);

        spinner_quartie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    //Compter 0
                    //Début Goma
                    if (selection.equals(getString(R.string.quartie_lesvol))) {
                        mGender_quartier = GENDER_LESVOLCAN;
                    } else if (selection.equals(getString(R.string.quartie_mik))) {
                        mGender_quartier = GENDER_MIKENO;
                    } else if (selection.equals(getString(R.string.quartier_himb))) {
                        mGender_quartier = GENDER_HIMBIE;
                    } else if (selection.equals(getString(R.string.quartier_Kyesh))) {
                        mGender_quartier = GENDER_KYESHERO;
                    } else if (selection.equals(getString(R.string.quartier_lacv))) {
                        mGender_quartier = GENDER_LACVERT;
                        //Fin Goma
                    } else if (selection.equals(getString(R.string.quartier_kah))) {
                        mGender_quartier = GENDER_KAHEMBE;
                    } else if (selection.equals(getString(R.string.quartier_mur))) {
                        mGender_quartier = GENDER_MURARA;
                    } else if (selection.equals(getString(R.string.quartier_vir))) {
                        mGender_quartier = GENDER_VIRUNGA;
                    } else if (selection.equals(getString(R.string.quartier_maj))) {
                        mGender_quartier = GENDER_MAJENGO;
                    } else if (selection.equals(getString(R.string.quartier_maban_s))) {
                        mGender_quartier = GENDER_MABANG_S;
                    } else if (selection.equals(getString(R.string.quartier_maban_n))) {
                        mGender_quartier = GENDER_MABANG_N;
                    } else if (selection.equals(getString(R.string.quartier_ndos))) {
                        mGender_quartier = GENDER_NDO;
                    } else if (selection.equals(getString(R.string.quartier_mung))) {
                        mGender_quartier = GENDER_MUNGUNG;
                    } else if (selection.equals(getString(R.string.quartier_katin1))) {
                        mGender_quartier = GENDER_KATINDO_a;
                    } else if (selection.equals(getString(R.string.quartier_katin2))) {
                        mGender_quartier = GENDER_KATINDO_b;
                        //Fin Karisimbi 0
                        //Fin Compter 0
                    } else {
                        mGender_quartier = GENDER_QUART;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender_quartier = 0;
            }
        });
    }
    private void setupSpinner_type(){
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_gender_type,
                android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_type.setAdapter(genderSpinnerAdapter);

        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    //Compter 0
                    if (selection.equals(getString(R.string.type_sani))) {
                        mGender_type = GENDER_SANITAIRE;
                    } else if (selection.equals(getString(R.string.type_par))) {
                        mGender_type = GENDER_PARAETA;
                    } else if (selection.equals(getString(R.string.type_etat))) {
                        mGender_type = GENDER_ETATIQUE;
                    } else if (selection.equals(getString(R.string.type_priv))) {
                        mGender_type = GENDER_PRIVEE;
                    } else if (selection.equals(getString(R.string.type_loca))) {
                        mGender_type = GENDER_LOCAL;
                        //Fin Goma
                    } else if (selection.equals(getString(R.string.type_intern))) {
                        mGender_type = GENDER_INTERNATIONAL;
                    } else if (selection.equals(getString(R.string.type_univ))) {
                        mGender_type = GENDER_UNIVERSITAIRE;
                    } else if (selection.equals(getString(R.string.type_inst_s))) {
                        mGender_type = GENDER_INSTITUT_SUPERIEUR;
                    } else if (selection.equals(getString(R.string.type_inst_se))) {
                        mGender_type = GENDER_INSTITUT_SECONDAIRE;
                    } else if (selection.equals(getString(R.string.type_ecolep))) {
                        mGender_type = GENDER_INSTITUT_PRIMAIRE_MATERNEL;
                    } else if (selection.equals(getString(R.string.type_ecoleprim))) {
                        mGender_type = GENDER_INSTITUT_PRIMAIRE;
                        //Fin Compter 0
                    } else {
                        mGender_quartier = GENDER_QUART;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender_type = 0;
            }
        });
    }
}