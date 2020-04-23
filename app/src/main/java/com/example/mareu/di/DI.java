package com.example.mareu.di;

import com.example.mareu.service.DummyReunionApiService;
import com.example.mareu.service.ReunionApiService;


public class DI {

    private static ReunionApiService service = new DummyReunionApiService();


    public static ReunionApiService getReunionApiService() {
        return service;
    }


    public static ReunionApiService getNewInstanceApiService() {
        return new DummyReunionApiService();
    }
}
