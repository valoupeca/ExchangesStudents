package com.example.lamur.exchangesstudents;

import java.io.Serializable;

public class Rendez_Vous implements Serializable {


    Integer IdDispo;
    Integer IdRendez_vous;
    String Commentaire;
    Double Note_user;
    Service_Disponibilite serv;
    Integer id_user;




    public Rendez_Vous() {
    }

    public Service_Disponibilite getServ() {
        return serv;
    }

    public void setServ(Service_Disponibilite serv) {
        this.serv = serv;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getIdDispo() {
        return IdDispo;
    }

    public void setIdDispo(Integer idDispo) {
        IdDispo = idDispo;
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
        return "Nom du fournissuer='" + serv.getId_fournisseur() + '\'' +
                "Nom du service='" + serv.getNom_service() + '\'' +
                ", jour='" + serv.getJour() + '\'' +
                "heure='" + serv.getHeure() + '\'' +
                "Votre Notre ='" + Note_user + '\'';
    }
}
