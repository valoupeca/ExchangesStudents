package com.example.lamur.exchangesstudents;

import java.io.Serializable;

public class Proprietaire extends User implements Serializable {


    public Proprietaire()
    {

    }
    public Proprietaire(int _id, String _username, String mdp) {
        super(_id, _username, mdp);
    }

    public Proprietaire(String _username, String mdp) {
        super(_username, mdp);
    }
}
