package com.example.lamur.exchangesstudents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Ajout_disponibilite extends AppCompatActivity {

    ArrayList<String> List_dispo = new ArrayList<>();
    ArrayList<String> jours = new ArrayList<>();
    ArrayList<String> heures = new ArrayList<>();
    ServiceCustomAdapter  myCustomAdapter;
    ArrayAdapter<String> adapter;
    ListView list;
    Fournisseur user;
    EditText search;

    DBHelper dbhelper = DBHelper.getInstance(this);

    Services _services_selected = new Services();
    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_disponibilite);





        list = (ListView) findViewById(R.id.liste_service);

        user =  (Fournisseur)this.getIntent().getSerializableExtra("info_user");
        ArrayList<Services> ls = dbhelper.listService();
        myCustomAdapter = new ServiceCustomAdapter(Ajout_disponibilite.this, ls);

        list.setAdapter(myCustomAdapter);

        myCustomAdapter.updateReceiptsList(ls);

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
        heures.add("12am-1pm");
        heures.add("1pm-2pm");
        heures.add("2pm-3pm");
        heures.add("3pm-4pm");
        heures.add("4pm-5pm");
        heures.add("5pm-6pm");
        heures.add("6pm-7m");
        heures.add("7pm-8pm");



        for(int i=0; i<7;i++) {
            for(int j=0; j<12; j++) {
                List_dispo.add(jours.get(i) + "  " + heures.get(j));
            }
        }


        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) { //This is automatically generated by the OnItemClickListener, the parameters are shown as the default name says, so we can assign the "position" in the next progress.

                Services serv = (Services) myCustomAdapter.getItem(position);

                _services_selected = serv;

            }


        });


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,List_dispo );
        myListView = findViewById(R.id.agenda);

        myListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        search = (EditText) findViewById(R.id.search_id);
        myListView.setTextFilterEnabled(true);
        myListView.setAdapter(adapter);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Ajout_disponibilite.this.adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Services> ls = dbhelper.listService();
        myCustomAdapter.updateReceiptsList(ls);
    }

    public void Choose_Horaire(View view)
    {
        SparseBooleanArray checked = myListView.getCheckedItemPositions();
        if(checked.size() == 0 || _services_selected.getId() == -1)
            {
                Toast.makeText(this, "Veuillez choisir un horaire / Service", Toast.LENGTH_LONG).show();

            }        else {
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
                        dbhelper.addOrUpdateDisponibilite(_services_selected.getId(), _horaire, _jour, user.get_id(), -1,0,-1);

                    }
            }

            finish();
        }

    }
}
