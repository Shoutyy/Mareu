package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyReunionGenerator {

    public static List<Reunion> DUMMY_REUNIONS = Arrays.asList(
            new Reunion(1,"06/05/2020" , "16:00", "Salle A","Peach",new String[]{"amandine@lamzone.com","anthony@lamzone.com", "stephanie@lamzone.com"} ),
            new Reunion(2,"07/05/2020" , "14:00", "Salle B","Mario",new String[]{"corentin@lamzone.com","maxime@lamzone.com", "stephanie@lamzone.com"}),
            new Reunion(3,"10/05/2020" , "17:00", "Salle C","Luigi",new String[]{"maxime@lamzone.com","lara@lamzone.com", "anthony@lamzone.com"}),
            new Reunion(4,"12/05/2020" , "10:00", "Salle D","Bowser",new String[]{"maxime@lamzone.com","stephanie@lamzone.com", "lara@lamzone.com"}),
            new Reunion(5,"14/05/2020" , "10:00", "Salle E","Waluigi",new String[]{"amandine@lamzone.com","maxime@lamzone.com", "lara@lamzone.com"}),
            new Reunion(6,"16/05/2020" , "11:00", "Salle F","Wario",new String[]{"amandine@lamzone.com","anthony@lamzone.com", "maxime@lamzone.com"})
    );


    static List generateReunions() {
        return new ArrayList<>(DUMMY_REUNIONS);
    }

}

