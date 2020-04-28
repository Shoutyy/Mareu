package com.example.mareu.service;

import com.example.mareu.model.Reunion;
import com.example.mareu.model.Salle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyReunionGenerator {

    public static List<Reunion> DUMMY_REUNIONS = Arrays.asList(
            new Reunion(1,"06/05/2020" , "16H00", "Salle A","Peach","" ),
            new Reunion(2,"07/05/2020" , "14H00", "Salle B","Mario",""),
            new Reunion(3,"10/05/2020" , "17H00", "Salle C","Luigi",""),
            new Reunion(4,"12/05/2020" , "10H00", "Salle D","Bowser",""),
            new Reunion(5,"14/05/2020" , "10H00", "Salle E","Waluigi","" ),
            new Reunion(6,"16/05/2020" , "11H00", "Salle F","Wario","")
    );

    public static List<Salle> DUMMY_SALLES = Arrays.asList(
            new Salle(1,"Salle A"),
            new Salle(2,"Salle B"),
            new Salle(3,"Salle C"),
            new Salle(4,"Salle D"),
            new Salle(5,"Salle E"),
            new Salle(6,"Salle F")
    );

    static List generateReunions() {
        return new ArrayList<>(DUMMY_REUNIONS);
    }

    static List<Salle> generateSalles() {
        return new ArrayList<>(DUMMY_SALLES);}
}

