package com.example.lamur.exchangesstudents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Fournisseur extends User implements Serializable {

    private Date disponibilité;
    private ArrayList<Services> _services;


    public Fournisseur()
    {

    }


    public Fournisseur(String _username, String mdp) {
        super(_username, mdp);
    }

    public Fournisseur(Date disponibilité, ArrayList<Services> _services) {
        this.disponibilité = disponibilité;
        this._services = _services;
    }


    public void setDisponibilité(Date disponibilité) {
        this.disponibilité = disponibilité;
    }

    public void set_services(ArrayList<Services> _services) {
        this._services = _services;
    }

    public Date getDisponibilité() {
        return disponibilité;
    }

    public ArrayList<Services> get_services() {
        return _services;
    }
}
