package com.example.lamur.exchangesstudents;

import java.util.ArrayList;
import java.util.Date;

public class profile {

    private  String nom;
    private Date disponibilité;
    private ArrayList<services> _services;

    public profile(String nom, Date disponibilité, ArrayList<services> _services) {
        this.nom = nom;
        this.disponibilité = disponibilité;
        this._services = _services;
    }
}
