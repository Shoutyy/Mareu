package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.Date;
import java.util.List;



public interface ReunionApiService {

    List<Reunion> getReunions();

    List<Reunion> getReunions(Date date);

    List<Reunion> getReunions(String lieu);


    void deleteReunion(Reunion reunion);

}
