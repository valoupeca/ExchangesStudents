package com.example.lamur.exchangesstudents;

import java.util.ArrayList;

public class Services {

    private String nom;
    private double taux_horraire;
    private ArrayList<Services> Categorie;


    public Services(String nom, float taux_horraire, ArrayList<Services> categorie) {
        this.nom = nom;
        this.taux_horraire = taux_horraire;
        Categorie = categorie;
    }

    public Services() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getTaux_horraire() {
        return taux_horraire;
    }

    public void setTaux_horraire(double taux_horraire) {
        this.taux_horraire = taux_horraire;
    }

    public ArrayList<Services> getCategorie() {
        return Categorie;
    }

    public void setCategorie(ArrayList<Services> categorie) {
        Categorie = categorie;
    }
}
