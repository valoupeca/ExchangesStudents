package com.example.lamur.exchangesstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Ajout_service extends AppCompatActivity {

    EditText nom;
    EditText taux_horaire;
    Spinner categorie;
    DBHelper dbhelper = DBHelper.getInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_service);
    }

    public void submit(View view) {
        nom = (EditText) findViewById(R.id.nom_service);
        taux_horaire = (EditText) findViewById(R.id.taux_horaire);
        categorie = (Spinner) findViewById(R.id.categorie);

        String _name = nom.getText().toString();

        double _th = Double.parseDouble(taux_horaire.getText().toString());
        dbhelper.addOrUpdateService(nom.toString(),_th, categorie.getSelectedItem().toString());
        Toast.makeText(this,"Service ajouté",Toast.LENGTH_LONG).show();
        finish();
    }
}
