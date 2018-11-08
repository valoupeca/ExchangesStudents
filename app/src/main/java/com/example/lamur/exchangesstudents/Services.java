package com.example.lamur.exchangesstudents;

import java.io.Serializable;
import java.util.ArrayList;

public class Services  implements Serializable {

    int id;
    private String nom;
    private double taux_horraire;
    private ArrayList<String> Categorie;



    public Services(int id, String nom, double taux_horraire, ArrayList<String> categorie) {

        this.id = id;
        this.nom = nom;
        this.taux_horraire = taux_horraire;

        Categorie = categorie;
    }

    public Services(String nom, double taux_horraire, ArrayList<String> categorie) {
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

    public ArrayList<String> getCategorie() {
        return Categorie;
    }

    public void setCategorie(ArrayList<String> categorie) {
        Categorie = categorie;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
