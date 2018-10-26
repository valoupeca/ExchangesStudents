package com.example.lamur.exchangesstudents;

import java.util.ArrayList;

public class services {

private String nom;
private float taux_horraire;
private ArrayList<services> Categorie;


    public services(String nom, float taux_horraire, ArrayList<services> categorie) {
        this.nom = nom;
        this.taux_horraire = taux_horraire;
        Categorie = categorie;
    }



}
