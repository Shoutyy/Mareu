package com.example.mareu.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;


public class Reunion implements Parcelable {

    private Integer id;

    private String heure;

    private String lieu;

    private String sujet;

    private String participants;

    public Reunion(Integer id, String heure, String lieu, String sujet, String participants) {
        this.id = id;
        this.heure = heure;
        this.lieu = lieu;
        this.sujet = sujet;
        this.participants = participants;
    }

    protected Reunion(Parcel in) {

        id = in.readInt();
        heure = in.readString();
        lieu = in.readString();
        sujet = in.readString();
        participants = in.readString();
    }

    public static final Creator<Reunion> CREATOR = new Creator<Reunion>() {

        @Override
        public Reunion createFromParcel(Parcel in) {
            return new Reunion(in);
        }

        @Override
        public Reunion[] newArray(int size) {
            return new Reunion[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reunion reunion = (Reunion) o;
        return Objects.equals(id, reunion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(heure);
        parcel.writeString(lieu);
        parcel.writeString(sujet);
        parcel.writeString(participants);
    }

}

