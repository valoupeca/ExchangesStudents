package com.example.lamur.exchangesstudents;

public class Proprietaire extends User{


    public Proprietaire(int _id, String _username, String mdp) {
        super(_id, _username, mdp);
    }

    public Proprietaire(String _username, String mdp) {
        super(_username, mdp);
    }
}
