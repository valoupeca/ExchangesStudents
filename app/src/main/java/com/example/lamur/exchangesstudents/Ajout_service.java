package com.example.lamur.exchangesstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Ajout_service extends AppCompatActivity {

    EditText nom;
    EditText taux_horaire;
    Spinner categorie;
    DBHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_service);

        Spinner listview = (Spinner) findViewById(R.id.categorie);


        String[] values = new String[] { "Services" };


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);

        listview.setAdapter(adapter);
    }

    public void submit(View view) {
        nom = (EditText) findViewById(R.id.date);
        taux_horaire = (EditText) findViewById(R.id.taux_horaire);
        categorie = (Spinner) findViewById(R.id.categorie);
        dbhelper =  dbhelper = DBHelper.getInstance(this);
        ArrayList<String> cat = new ArrayList<>();

        cat.add(categorie.getSelectedItem().toString());





        if (nom.getText().length() == 0 || taux_horaire.getText().length() == 0) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
        } else {
            double _th = Double.parseDouble(taux_horaire.getText().toString());
            Services serv = new Services(nom.getText().toString(), _th, cat);
            dbhelper.addOrUpdateService(serv);
            Toast.makeText(this, "Service ajouté", Toast.LENGTH_LONG).show();
            finish();
        }


    }
}
