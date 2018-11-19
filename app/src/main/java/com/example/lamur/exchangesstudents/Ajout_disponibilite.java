package com.example.lamur.exchangesstudents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Ajout_disponibilite extends AppCompatActivity {

    ArrayList<String> List_dispo;
    ArrayList<String> jours;
    ArrayList<String> heures;
    CustomAdapter  myCustomAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_modif_services);




        jours.add("Monday");
        jours.add("Tuesday");
        jours.add("Wednesday");
        jours.add("Thursday");
        jours.add("Friday ");
        jours.add("Saturday");
        jours.add("Sunday");

        heures.add("8am-9am");
        heures.add("9am-10am");
        heures.add("10am-11am");
        heures.add("11am-12am");
        heures.add("12am-13am");
        heures.add("13am-14am");
        heures.add("14am-15am");
        heures.add("15am-16am");
        heures.add("16am-17am");
        heures.add("17am-18am");
        heures.add("18am-19am");
        heures.add("19am-20am");


        for(int i=0; i<7;i++) {
            for(int j=0; j<12; j++) {
                List_dispo.add(jours.get(i) + "  " + heures.get(j));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,List_dispo );
        ListView myListView =(ListView)findViewById(R.id.agenda);
        myListView.setAdapter(adapter);


    }
}
