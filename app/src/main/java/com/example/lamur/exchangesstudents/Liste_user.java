package com.example.lamur.exchangesstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Liste_user extends AppCompatActivity {

    CustomAdapter  myCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_user);

        ListView liste = findViewById(R.id.liste_user);

        String role = getIntent().getStringExtra("ROLE_ID");


        Bundle objetbundle = this.getIntent().getExtras();



        ArrayList<User> l_user = (ArrayList<User>) this.getIntent().getSerializableExtra("LIST_OF_OBJECTS");
        myCustomAdapter = new CustomAdapter(Liste_user.this, l_user);



        liste.setAdapter(myCustomAdapter);

    }
}
