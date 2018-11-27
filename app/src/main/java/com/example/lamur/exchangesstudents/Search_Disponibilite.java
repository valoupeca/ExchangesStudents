package com.example.lamur.exchangesstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Search_Disponibilite extends AppCompatActivity {

    private RecyclerView recycler_liste;
    private List<Service_Disponibilite> lesDispo;
    private DisponibiliteAdapter lAdapter;

    EditText searchS;
    EditText searchD;
    EditText searchR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__disponibilite);

        recycler_liste = (RecyclerView)findViewById(R.id.liste_dispo);

        lesDispo = new ArrayList<>();


        lAdapter = new DisponibiliteAdapter(lesDispo);

        recycler_liste.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL ,false));

        recycler_liste.setAdapter(lAdapter);

        searchS = (EditText) findViewById(R.id.searchByS);

        searchD = (EditText) findViewById(R.id.searchByD);

        searchR = (EditText) findViewById(R.id.searchByR);


        searchS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Service_Disponibilite> newList = new ArrayList<>();

                for(Service_Disponibilite service : lesDispo)
                {
                    if(service.getNom_service().contains(s))
                    {
                        newList.add(service);
                    }
                }

                lAdapter.updateList(newList);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        searchD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                List<Service_Disponibilite> newList = new ArrayList<>();

                for(Service_Disponibilite service : lesDispo)
                {
                    if(service.getHeure().contains(s) || service.getJour().contains(s))
                    {
                        newList.add(service);
                    }
                }

                lAdapter.updateList(newList);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        searchR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Service_Disponibilite> newList = new ArrayList<>();

                for(Service_Disponibilite service : lesDispo)
                {
                    if(service.getMoyenne() == Double.parseDouble(s.toString()))
                    {
                        newList.add(service);
                    }
                }

                lAdapter.updateList(newList);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}
