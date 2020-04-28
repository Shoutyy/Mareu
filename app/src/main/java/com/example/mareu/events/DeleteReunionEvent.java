package com.example.mareu.events;

import com.example.mareu.model.Reunion;


public class DeleteReunionEvent {


    public Reunion<String> reunion;


    public DeleteReunionEvent(Reunion<String> reunion) {
        this.reunion = reunion;
    }
}