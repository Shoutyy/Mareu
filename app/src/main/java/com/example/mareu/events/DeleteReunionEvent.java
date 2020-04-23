package com.example.mareu.events;

import com.example.mareu.model.Reunion;


public class DeleteReunionEvent {


    public Reunion reunion;


    public DeleteReunionEvent(Reunion reunion) {
        this.reunion = reunion;
    }
}