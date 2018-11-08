package com.example.lamur.exchangesstudents;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Modifier_service extends AppCompatActivity {

    EditText nom;
    EditText taux_horaire;
    Spinner categorie;
    DBHelper dbhelper = DBHelper.getInstance(this);

    String nom_service_select;
    ArrayList<String> categorie_service_select;
    double taux_horaire_service_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_service);

        nom_service_select = getIntent().getExtras().getString("NOM");
        categorie_service_select= getIntent().getExtras().getStringArrayList("CATEGORIE");
        taux_horaire_service_select = getIntent().getExtras().getDouble("TAUX_HORAIRE");
    }

    public void modifierService (View view){
        nom = (EditText) findViewById(R.id.nom_service);
        taux_horaire = (EditText) findViewById(R.id.taux_horaire);
        categorie = (Spinner) findViewById(R.id.categorie);

        ArrayList<String> cat = new ArrayList<>();

        cat.add(categorie.getSelectedItem().toString());

        double _th = Double.parseDouble(taux_horaire.getText().toString());




        if (nom.getText().length() == 0 || taux_horaire.getText().length() == 0) {
            Toast.makeText(this, "Veuillez remplir tout les champs", Toast.LENGTH_LONG).show();
        } else {
            Services serv = new Services(nom.toString(), _th, cat);
            dbhelper.addOrUpdateService(serv);
            Toast.makeText(this, "Service modifié", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void supprimerService (View view){

        dbhelper.deleteService(nom_service_select);
    }

}

