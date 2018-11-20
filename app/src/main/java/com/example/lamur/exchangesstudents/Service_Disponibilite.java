package com.example.lamur.exchangesstudents;

import java.io.Serializable;

public class Service_Disponibilite implements Serializable {
    private int _id;
    private String heure;
    private String jour;
    private int id_service;
    private int id_fournisseur;
    private  String nom_service;

    public Service_Disponibilite(int _id, String heure, String jour, int id_service, int id_fournisseur, String nom_service) {
        this._id = _id;
        this.heure = heure;
        this.jour = jour;
        this.id_service = id_service;
        this.id_fournisseur = id_fournisseur;
        this.nom_service = nom_service;
    }

    public Service_Disponibilite(int _id) {
        this._id = _id;
    }

    public Service_Disponibilite() {
    }

    public String getNom_service() {
        return nom_service;
    }

    public void setNom_service(String nom_service) {
        this.nom_service = nom_service;
    }

    public Service_Disponibilite(int _id, String heure, String jour, int id_service, int id_fournisseur) {
        this._id = _id;
        this.heure = heure;
        this.jour = jour;
        this.id_service = id_service;
        this.id_fournisseur = id_fournisseur;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public int getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    @Override
    public String toString() {
        return "Nom du service='" + nom_service + '\'' +
                ", jour='" + jour + '\'' +
                "heure='" + heure + '\'' ;
    }
}
