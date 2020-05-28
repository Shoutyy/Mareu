package com.example.mareu.service;

import com.example.mareu.model.Reunion;
import com.example.mareu.reunion_list.ListReunionActivity;

import java.util.ArrayList;
import java.util.List;


public class DummyReunionApiService implements  ReunionApiService {

    private List<Reunion> reunions = DummyReunionGenerator.generateReunions();

    public Reunion reunion;

    ListReunionActivity activity = (ListReunionActivity) ListReunionActivity.this;
    String fLieu = activity.fLieu;
    String fDate = activity.fDate;

    @Override
    public List<Reunion> getReunions(String date, String lieu) {
        if (!fDate.equals("") & fLieu.equals("")) {
            List<Reunion> fReunions = new ArrayList<>();
            for (Reunion reunion : reunions) {
                if (reunion.getDate().equals(fDate)) {
                    fReunions.add(reunion);
                }
            }
            return fReunions;
        } else if (fDate.equals("") & !fLieu.equals("")) {
            List<Reunion> fReunions = new ArrayList<>();
            for (Reunion reunion : reunions) {
                if (reunion.getLieu().equals(fLieu)) {
                    fReunions.add(reunion);
                }
            }
            return fReunions;

        } else if (!fDate.equals("") & !fLieu.equals("")) {
            List<Reunion> fReunions = new ArrayList<>();
            for (Reunion reunion : reunions) {
                if (reunion.getLieu().equals(fLieu) & reunion.getDate().equals(fDate)) {
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
