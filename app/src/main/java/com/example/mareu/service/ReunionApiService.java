package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.List;



public interface ReunionApiService {

    List<Reunion> getReunions(String date, String lieu);

    void injectReunion();

    void deleteReunion(Reunion reunion);

}
