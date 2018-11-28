package com.example.lamur.exchangesstudents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Sign_in_utilisateur extends AppCompatActivity {


    String nom,role;
    TextView name;
    Proprietaire user;
    ListView list;
    ArrayAdapter<String> adapter;
    DBHelper dbhelper = DBHelper.getInstance(this);
    ArrayList<Rendez_Vous> listOfValues = new ArrayList<>();
    RdvCustomAdapter myCustomAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page_utilisateur);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        role = extras.getString("role");
        if(role.equals("Propriétaire")) {
            user = (Proprietaire) this.getIntent().getSerializableExtra("info_user");
        }
        name = (TextView) findViewById(R.id.nom_user);
        name.setText(user.get_username());

        list = (ListView) findViewById(R.id.Liste_activite);



        HashMap<Integer,Rendez_Vous> Listhash = new HashMap<>();
        Listhash = dbhelper.ServicesByUser(user.get_id());

        if(Listhash.isEmpty())
        {
            listOfValues = new ArrayList<>();


            myCustomAdapter = new RdvCustomAdapter(Sign_in_utilisateur.this, listOfValues);

            list.setAdapter(myCustomAdapter);

        }
        else {

            Collection<Rendez_Vous> values = Listhash.values();

            listOfValues = new ArrayList<>(values);

            list = (ListView) findViewById(R.id.liste_service);

                       myCustomAdapter = new RdvCustomAdapter(Sign_in_utilisateur.this, listOfValues);

            list.setAdapter(myCustomAdapter);

        }


    }

    public void ajouter_Service(View view){


        Intent add_service = new Intent(this, Search_Disponibilite.class);
        Bundle extras = new Bundle();

        extras.putSerializable("info_user",user);
        add_service.putExtras(extras);
        startActivity(add_service);

    }
}
