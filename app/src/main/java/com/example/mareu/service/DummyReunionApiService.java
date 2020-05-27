package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.List;


public class DummyReunionApiService implements  ReunionApiService {

    private List<Reunion> reunions = DummyReunionGenerator.generateReunions();

    public Reunion reunion;


    @Override
    public List<Reunion> getReunions() {
        return reunions;
    }

    @Override
    public List<Reunion> getfReunions(String date) {
        return reunions;
    }

    @Override
    public List<Reunion> getReunions(String lieu) {
        return reunions;
    }


    @Override
    public void deleteReunion(Reunion reunion) { reunions.remove(reunion); }


}
