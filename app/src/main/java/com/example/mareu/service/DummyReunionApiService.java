package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.List;


public class DummyReunionApiService implements  ReunionApiService {

    private List<Reunion> reunions = DummyReunionGenerator.generateReunions();

    public Reunion reunion;

    @Override
    public List<Reunion> getReunions(String date, String lieu) {
        if (!date.equals("") & lieu.equals("")) {
            List<Reunion> fReunions = new ArrayList<>();
            for (Reunion reunion : reunions) {
                if (reunion.getDate().equals(date)) {
                    fReunions.add(reunion);
                }
            }
            return fReunions;
        } else if (date.equals("") & !lieu.equals("")) {
            List<Reunion> fReunions = new ArrayList<>();
            for (Reunion reunion : reunions) {
                if (reunion.getLieu().equals(lieu)) {
                    fReunions.add(reunion);
                }
            }
            return fReunions;

        } else if (!date.equals("") & !lieu.equals("")) {
            List<Reunion> fReunions = new ArrayList<>();
            for (Reunion reunion : reunions) {
                if (reunion.getLieu().equals(lieu) & reunion.getDate().equals(date)) {
                    fReunions.add(reunion);
                }
            }
            return fReunions;
        } else {
            return reunions;
        }
    }

  /*  @Override
    public List<Reunion> getfReunions(String date) {
        return reunions;
    }

    @Override
    public List<Reunion> getReunions(String lieu) {
        return reunions;
    } */

    @Override
    public void deleteReunion(Reunion reunion) { reunions.remove(reunion); }


}
