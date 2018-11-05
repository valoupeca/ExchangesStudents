package com.example.lamur.exchangesstudents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Admin_page extends AppCompatActivity {

    ListView list;
    DBHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        dbhelper = (DBHelper)this.getIntent().getSerializableExtra("DbHelper");

        list = (ListView) findViewById(R.id.list_user);

        List<Services> list_service = new ArrayList<Services>();

        list_service = dbhelper.listService();


    }

    public void listProprio(View view)
    {
        ArrayList<Proprietaire> _lprio = dbhelper.listProprio();
        Intent intent = new Intent(this, Liste_user.class);
        intent.putExtra("DATA", _lprio);
        intent.putExtra("ROLE_ID", "Proprietaire") ;
        startActivity(intent);
    }

    public void listFournisseur(View view)
    {
        ArrayList<Fournisseur> _lfour = dbhelper.listFournisseur();

        Intent intent = new Intent(this, Liste_user.class);
        intent.putExtra("DATA", _lfour);
        intent.putExtra("ROLE_ID", "Fournisseur") ;
        startActivity(intent);
    }
}
