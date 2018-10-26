package com.example.lamur.exchangesstudents;


import java.util.ArrayList;

public final class admin extends User{

public void createService(String nom, float taux_horraire, ArrayList<services> categorie){
    services _services = new services(nom,taux_horraire,categorie);
}

}
