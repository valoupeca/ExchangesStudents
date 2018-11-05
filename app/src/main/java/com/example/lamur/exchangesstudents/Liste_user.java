package com.example.lamur.exchangesstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;

public class Liste_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_user);

        ListView liste = findViewById(R.id.list_user);

        String role = getIntent().getStringExtra("ROLE_ID");



            ArrayList<User> liste_fournisseur = new ArrayList<>();
            liste_fournisseur.addAll((Collection<? extends User>) getIntent().getSerializableExtra("DATA"));
            CustomAdapter myCustomAdapter = new CustomAdapter(Liste_user.this, liste_fournisseur);

            liste.setAdapter(myCustomAdapter);
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
