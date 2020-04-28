package com.example.mareu.service;

import com.example.mareu.model.Reunion;
import com.example.mareu.model.Salle;

import java.util.List;



public interface ReunionApiService {

    List<Reunion> getReunions();

    List<Salle> getSalles();

    void deleteReunion(Reunion reunion);

}
