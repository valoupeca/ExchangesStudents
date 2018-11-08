package com.example.lamur.exchangesstudents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Modifier_service extends AppCompatActivity {

    EditText nom;
    EditText taux_horaire;
    Spinner categorie;
    DBHelper dbhelper = DBHelper.getInstance(this);
    int _id;
    String nom_service_select;
    ArrayList<String> categorie_service_select;
    double taux_horaire_service_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_service);

        nom = (EditText) findViewById(R.id.nom_service_modif);
        taux_horaire = (EditText) findViewById(R.id.taux_horaire_modif);
        categorie = (Spinner) findViewById(R.id.categorie_modif);

        String[] values = new String[] { "Services" };


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);

        categorie.setAdapter(adapter);

        Services _serv_passer = (Services) getIntent().getSerializableExtra("Service");

        nom_service_select = _serv_passer.getNom();
        categorie_service_select=  _serv_passer.getCategorie();
        taux_horaire_service_select =  _serv_passer.getTaux_horraire();
        _id =  _serv_passer.getId();

        nom.setText(nom_service_select);
        taux_horaire.setText(String.valueOf(taux_horaire_service_select));


    }

    public void modifierService (View view){
        nom = (EditText) findViewById(R.id.nom_service_modif);
        taux_horaire = (EditText) findViewById(R.id.taux_horaire_modif);
        categorie = (Spinner) findViewById(R.id.categorie_modif);

        ArrayList<String> cat = new ArrayList<>();

        cat.add(categorie.getSelectedItem().toString());

        double _th = Double.parseDouble(taux_horaire.getText().toString());




        if (nom.getText().length() == 0 || taux_horaire.getText().length() == 0) {
            Toast.makeText(this, "Veuillez remplir tout les champs", Toast.LENGTH_LONG).show();
        } else {
            Services serv = new Services(_id,nom.getText().toString(), _th, cat);
            dbhelper.addOrUpdateService(serv);
            Toast.makeText(this, "Service modifié", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void supprimerService (View view){

        dbhelper.deleteService(_id);
        Toast.makeText(this, "Service supprimé", Toast.LENGTH_LONG).show();
        finish();

    }

}

