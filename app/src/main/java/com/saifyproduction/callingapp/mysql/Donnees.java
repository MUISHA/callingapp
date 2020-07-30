package com.saifyproduction.callingapp.mysql;

import com.google.gson.annotations.SerializedName;

public class Donnees{
        @SerializedName("id")
        private int id;

        @SerializedName("nom_entreprise")
        private String nom_entreprise;

        @SerializedName("responsable")
        private String responsable;

        @SerializedName("phone")
        private String phone;

        @SerializedName("email")
        private String email;

        @SerializedName("type")
        private int type;

        @SerializedName("domicile")
        private int domicile;

        @SerializedName("latitude")
        private float p_latitude;

        @SerializedName("logitude")
        private float p_longitude;

        @SerializedName("quartier")
        private int quartier;

        @SerializedName("commune")
        private int commune;

        @SerializedName("avenue")
        private String avenue;

        @SerializedName("numero_home")
        private String numero_home;

        @SerializedName("date_save")
        private String date_save;

        @SerializedName("love")
        private Boolean love;

        @SerializedName("picture")
        private String picture;

        @SerializedName("value")
        private String value;

        @SerializedName("message")
        private String massage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_entreprise() {
        return nom_entreprise;
    }

    public void setNom_entreprise(String nom_entreprise) {
        this.nom_entreprise = nom_entreprise;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDomicile() {
        return domicile;
    }

    public void setDomicile(int domicile) {
        this.domicile = domicile;
    }

    public double getP_latitude() {
        return p_latitude;
    }

    public void setP_latitude(float p_latitude) {
        this.p_latitude = p_latitude;
    }

    public double getP_longitude() {
        return p_longitude;
    }

    public void setP_longitude(float p_longitude) {
        this.p_longitude = p_longitude;
    }

    public int getQuartier() {
        return quartier;
    }

    public void setQuartier(int quartier) {
        this.quartier = quartier;
    }

    public int getCommune() {
        return commune;
    }

    public void setCommune(int commune) {
        this.commune = commune;
    }

    public String getAvenue() {
        return avenue;
    }

    public void setAvenue(String avenue) {
        this.avenue = avenue;
    }

    public String getNumero_home() {
        return numero_home;
    }

    public void setNumero_home(String numero_home) {
        this.numero_home = numero_home;
    }

    public String getDate_save() {
        return date_save;
    }

    public void setDate_save(String date_save) {
        this.date_save = date_save;
    }

    public Boolean getLove() {
        return love;
    }

    public void setLove(Boolean love) {
        this.love = love;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
