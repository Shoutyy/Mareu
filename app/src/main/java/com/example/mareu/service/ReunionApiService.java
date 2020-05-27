package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.List;



public interface ReunionApiService {

    List<Reunion> getReunions();

    List<Reunion> getfReunions(String date);

    List<Reunion> getReunions(String lieu);


    void deleteReunion(Reunion reunion);

}
