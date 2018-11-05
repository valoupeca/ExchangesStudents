package com.example.lamur.exchangesstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        List<User> list_user = new ArrayList<User>();



        ArrayList<Proprietaire> _lprio = dbhelper.listProprio();



    }
}
