package com.example.lamur.exchangesstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Liste_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_user);

        ListView liste = findViewById(R.id.list_user);

        String role = getIntent().getStringExtra("ROLE_ID");


        Bundle objetbundle = this.getIntent().getExtras();
        User[] liste_user;

        String listSerializedToJson = getIntent().getExtras().getString("LIST_OF_OBJECTS");
        liste_user = new Gson().fromJson(listSerializedToJson, User[].class);

        ArrayList<User> l_user = (ArrayList<User>) Arrays.asList(liste_user);

           // liste_user.addAll((Collection<? extends User>) getIntent().getSerializableExtra("DATA"));
            CustomAdapter myCustomAdapter = new CustomAdapter(Liste_user.this, l_user);

            liste.setAdapter(myCustomAdapter);
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
