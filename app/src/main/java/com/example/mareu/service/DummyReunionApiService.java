package com.example.mareu.service;

import com.example.mareu.model.Reunion;
import com.example.mareu.model.Salle;

import java.util.List;


public class DummyReunionApiService implements  ReunionApiService {

    private List<Reunion> reunions = DummyReunionGenerator.generateReunions();

    private List<Salle> salles = DummyReunionGenerator.generateSalles();

    public Reunion reunion;


    @Override
    public List<Reunion> getReunions() {
        return reunions;
    }

    @Override
    public List<Salle> getSalles() {return salles;}

    @Override
    public void deleteReunion(Reunion reunion) { reunions.remove(reunion); }


}
