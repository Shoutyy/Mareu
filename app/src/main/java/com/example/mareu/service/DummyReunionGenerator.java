package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyReunionGenerator {

    public static List<Reunion> DUMMY_REUNIONS = Arrays.asList(
            new Reunion(1, "16H00", "Réunion A","Peach", "participants" ),
            new Reunion(2, "14H00", "Réunion B","Mario", "participants" ),
            new Reunion(3, "17H00", "Réunion C","Luigi", "participants" ),
            new Reunion(4, "10H00", "Réunion D","Bowser", "participants" ),
            new Reunion(5, "10H00", "Réunion E","Waluigi", "participants" ),
            new Reunion(6, "11H00", "Réunion F","Wario", "participants" )
    );

    static List<Reunion> generateReunions() {
        return new ArrayList<>(DUMMY_REUNIONS);
    }
}

