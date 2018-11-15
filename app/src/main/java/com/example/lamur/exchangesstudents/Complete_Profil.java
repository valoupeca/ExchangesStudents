package com.example.lamur.exchangesstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Complete_Profil extends AppCompatActivity {


    EditText adresse;
    EditText phone;
    EditText company_name;
    EditText description;
    CheckBox yes;
    CheckBox non;
    DBHelper dbhelper = DBHelper.getInstance(this);
    Fournisseur user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete__profil);
        user =  (Fournisseur)this.getIntent().getSerializableExtra("info_user");
    }

    public void submit(View view) {
        adresse = (EditText) findViewById(R.id.adresse_id);
        phone = (EditText) findViewById(R.id.phone_id);
        description = (EditText) findViewById(R.id.description);
        company_name = (EditText) findViewById(R.id.company_name);
        yes = (CheckBox) findViewById(R.id.yes_check);
        non = (CheckBox) findViewById(R.id.non_check);

        if (TextUtils.isEmpty(adresse.getText()) || TextUtils.isEmpty(phone.getText()) || TextUtils.isEmpty(company_name.getText())) {

            adresse.setError("Data is required!");
            Toast.makeText(this, "Veuillez remplir tout les champs", Toast.LENGTH_LONG).show();


        }
        else
        {

        }

    }
}
