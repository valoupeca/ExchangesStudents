package com.example.lamur.exchangesstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Sign_in extends AppCompatActivity {


    String nom,role;

    EditText name;
    TextView user_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        nom = this.getIntent().getStringExtra("name");
        role = this.getIntent().getStringExtra("role");


        name = (EditText) findViewById(R.id.username);
        user_role = (TextView) findViewById(R.id.role_user);

        name.setText(nom);
        user_role.setText(role);
    }
}
