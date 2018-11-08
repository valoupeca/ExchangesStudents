package com.example.lamur.exchangesstudents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Sign_in extends AppCompatActivity {


    String nom,role;

    TextView name;
    TextView user_role;
    DBHelper dbhelper = DBHelper.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        nom = extras.getString("name");
        role = extras.getString("role");


        name = (TextView) findViewById(R.id.nom_user);
        user_role = (TextView) findViewById(R.id.role_user);

        name.setText(nom);
        user_role.setText(role);
    }
}
