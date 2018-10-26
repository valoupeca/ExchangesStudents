package com.example.lamur.exchangesstudents;

import java.util.ArrayList;

public class Services {

private String nom;
private float taux_horraire;
private ArrayList<Services> Categorie;


    public Services(String nom, float taux_horraire, ArrayList<Services> categorie) {
        this.nom = nom;
        this.taux_horraire = taux_horraire;
        Categorie = categorie;
    }



}
