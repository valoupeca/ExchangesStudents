package com.example.lamur.exchangesstudents;

import java.io.Serializable;

public class Rendez_Vous implements Serializable {

    String NomFourniseur;
    String Jour;
    String Heure;
    Integer IdDispo;
    String NomService;
    Integer IdServic;
    Integer IdRendez_vous;
    String Commentaire;
    Double Note_user;


    public Rendez_Vous() {
    }

    public String getNomFourniseur() {
        return NomFourniseur;
    }

    public void setNomFourniseur(String nomFourniseur) {
        NomFourniseur = nomFourniseur;
    }

    public String getJour() {
        return Jour;
    }

    public void setJour(String jour) {
        Jour = jour;
    }

    public String getHeure() {
        return Heure;
    }

    public void setHeure(String heure) {
        Heure = heure;
    }

    public Integer getIdDispo() {
        return IdDispo;
    }

    public void setIdDispo(Integer idDispo) {
        IdDispo = idDispo;
    }

    public String getNomService() {
        return NomService;
    }

    public void setNomService(String nomService) {
        NomService = nomService;
    }

    public Integer getIdServic() {
        return IdServic;
    }

    public void setIdServic(Integer idServic) {
        IdServic = idServic;
    }

    public Integer getIdRendez_vous() {
        return IdRendez_vous;
    }

    public void setIdRendez_vous(Integer idRendez_vous) {
        IdRendez_vous = idRendez_vous;
    }

    public String getCommentaire() {
        return Commentaire;
    }

    public void setCommentaire(String commentaire) {
        Commentaire = commentaire;
    }

    public Double getNote_user() {
        return Note_user;
    }

    public void setNote_user(Double note_user) {
        Note_user = note_user;
    }

    @Override
    public String toString() {
        return "Nom du fournissuer='" + NomFourniseur + '\'' +
                "Nom du service='" + NomService + '\'' +
                ", jour='" + Jour + '\'' +
                "heure='" + Heure + '\'' +
                "Votre Notre ='" + Note_user + '\'';
    }
}
