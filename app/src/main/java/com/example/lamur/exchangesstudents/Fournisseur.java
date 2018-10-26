package com.example.lamur.exchangesstudents;

import java.util.ArrayList;

public class Fournisseur extends User {

    private ArrayList<profile> Profils;

public void createProfil(profile _profile){

    Profils.add(_profile);

}

public void delete(profile _profile){

    Profils.remove(_profile);
}

}
