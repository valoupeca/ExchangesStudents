package com.example.lamur.exchangesstudents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Admin_page extends AppCompatActivity {

    ListView list;
    DBHelper dbhelper = DBHelper.getInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        list = (ListView) findViewById(R.id.list_user);


        ArrayList<Services> ls = dbhelper.listService();



        ServiceCustomAdapter myCustomAdapter = new ServiceCustomAdapter(Admin_page.this, ls);

        list.setAdapter(myCustomAdapter);




    }

    public void listProprio(View view)
    {
        ArrayList<User> _lprio = dbhelper.listProprio();
        Intent intent = new Intent(this, Liste_user.class);
        intent.putExtra("DATA", _lprio);
        intent.putExtra("ROLE_ID", "Proprietaire") ;
        startActivity(intent);
    }

    public void listFournisseur(View view)
    {
        ArrayList<User> _lfour = dbhelper.listFournisseur();

        Intent intent = new Intent(this, Liste_user.class);
        String listSerializedToJson = new Gson().toJson(_lfour);
        intent.putExtra("LIST_OF_OBJECTS", listSerializedToJson);

        intent.putExtra("DATA", _lfour);
        intent.putExtra("ROLE_ID", "Fournisseur") ;
        startActivity(intent);
    }
}
