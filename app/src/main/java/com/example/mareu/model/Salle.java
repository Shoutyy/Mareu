package com.example.mareu.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;


public class Salle implements Parcelable {

    private Integer id;

    private String lieu;

    public Salle(Integer id, String lieu) {
        this.id = id;
        this.lieu = lieu;
    }

    protected Salle(Parcel in) {

        id = in.readInt();
        lieu = in.readString();
    }

    public static final Creator<Salle> CREATOR = new Creator<Salle>() {

        @Override
        public Salle createFromParcel(Parcel in) {
            return new Salle(in);
        }

        @Override
        public Salle[] newArray(int size) {
            return new Salle[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salle salle = (Salle) o;
        return Objects.equals(id, salle.id);
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
        parcel.writeString(lieu);
    }

}

