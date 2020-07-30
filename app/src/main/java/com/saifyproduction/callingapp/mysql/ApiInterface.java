package com.saifyproduction.callingapp.mysql;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("get_sos.php") // Select all data base
    Call<List<Donnees>> getSos();

    @FormUrlEncoded
    @POST("add_sos.php")
    Call<Donnees> insertSos(
            @Field("key") String key,
            @Field("nom_entreprise") String nom_entreprise, // Erreur
            @Field("responsable") String responsable,
            @Field("phone") String phone_number ,
            @Field("email") String mail_compte ,
            @Field("type") int type_entreprise ,//Index
            @Field("domicile") int domicile ,//Index
            @Field("latitude") float p_latitude ,
            @Field("logitude") float p_longitude  ,
            @Field("quartier") int quartier  , //index
            @Field("commune") int commune  , //index
            @Field("avenue") String avenue  ,
            @Field("numero_home") int numero_home  , //index Erreur
            @Field("date_save") String date_timer, // Erreur
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("update_sos.php")
    Call<Donnees> updateSos(
            @Field("key") String key,
            @Field("id") int id,
            @Field("nom_entreprise") String nom_entreprise,
            @Field("responsable") String responsable,
            @Field("phone") String phone_number ,
            @Field("email") String mail_compte ,
            @Field("type") int type_entreprise ,//Index
            @Field("domicile") int domicile ,//Index
            @Field("latitude") float p_latitude ,
            @Field("logitude") float p_longitude  ,
            @Field("quartier") int quartier  , //index
            @Field("commune") int commune  , //index
            @Field("avenue") String avenue  ,
            @Field("numero_home") int numero_home  , //index
            @Field("date_save") String date_timer,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("delete_sos.php")
    Call<Donnees> deleteSos(
            @Field("key") String key,
            @Field("id") int id,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("update_love.php")
    Call<Donnees> updateLove(
            @Field("key") String key,
            @Field("id") int id,
            @Field("love") boolean love);
}
