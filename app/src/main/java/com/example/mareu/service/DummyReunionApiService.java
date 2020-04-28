package com.example.mareu.service;

import com.example.mareu.model.Reunion;
import com.example.mareu.model.Salle;

import java.util.List;


public class DummyReunionApiService implements  ReunionApiService {

    private List<Reunion<String>> reunions = DummyReunionGenerator.generateReunions();

    private List<Salle> salles = DummyReunionGenerator.generateSalles();

    public Reunion<String> reunion;


    @Override
    public List<Reunion<String>> getReunions() {
        return reunions;
    }

    @Override
    public List<Salle> getSalles() {return salles;}

    @Override
    public void deleteReunion(Reunion<String> reunion) { reunions.remove(reunion); }


}
