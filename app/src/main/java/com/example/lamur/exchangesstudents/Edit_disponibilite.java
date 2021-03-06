    package com.example.lamur.exchangesstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import java.util.ArrayList;

    public class Edit_disponibilite extends AppCompatActivity {

    Service_Disponibilite _serv_passer;
        TextView Name;
        TextView Horaire;
        ArrayList<String> List_dispo = new ArrayList<>();
        ArrayList<String> jours = new ArrayList<>();
        ArrayList<String> heures = new ArrayList<>();
        ServiceCustomAdapter  myCustomAdapter;
        ArrayAdapter<String> adapter;
        ListView list;
        EditText search;
        ListView myListView;
        DBHelper dbhelper = DBHelper.getInstance(this);

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_disponibilite);

        _serv_passer = (Service_Disponibilite) getIntent().getSerializableExtra("Service");

        Name = (TextView) findViewById(R.id.name_service);
        Horaire = (TextView) findViewById(R.id.horaire_service);

        Name.setText(_serv_passer.getNom_service());
        Horaire.setText(_serv_passer.getJour()+"  "+_serv_passer.getHeure());


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

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,List_dispo );

        myListView = findViewById(R.id.agenda_modif);

        myListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        search = (EditText) findViewById(R.id.search_horaire);
        myListView.setTextFilterEnabled(true);
        myListView.setAdapter(adapter);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Edit_disponibilite.this.adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    public void Modifier_Horaire(View view)
    {
        SparseBooleanArray checked = myListView.getCheckedItemPositions();
        if(checked.size() == 0) {
            Toast.makeText(this, "Veuillez choisir un horaire", Toast.LENGTH_LONG).show();
        }
        else {
            int nb;
            String _jour, _horaire;

            ArrayList<String> selectedItems = new ArrayList<String>();
            for (int i = 0; i < checked.size(); i++) {
                // Item position in adapter
                int position = checked.keyAt(i);
                // Add sport if it is checked i.e.) == TRUE!
                if (checked.valueAt(i))
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        nb = Math.floorMod(position, 12);
                        int _pos_jour = position / 12;
                        _jour = jours.get(_pos_jour);
                        _horaire = heures.get(nb);
                        dbhelper.addOrUpdateDisponibilite(_serv_passer.getId_service(), _horaire, _jour, _serv_passer.getId_fournisseur(), _serv_passer.get_id(),(int)_serv_passer.getNb_vote(),(int)_serv_passer.getMoyenne());

                    }
            }
            Toast.makeText(this, "Update dispo", Toast.LENGTH_LONG).show();
            finish();
        }

    }


    public void Delete_Horaire(View view)
    {
        int id = _serv_passer.get_id();

        dbhelper.delete_disponibilite(id);
        Toast.makeText(this, "Delete dispo", Toast.LENGTH_LONG).show();
        finish();

    }

}
