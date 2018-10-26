package com.example.lamur.exchangesstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

public class Sign_up extends AppCompatActivity {

    DBHelper dbhelper;
    ListView role;
    EditText username;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        dbhelper = (DBHelper)this.getIntent().getSerializableExtra("DbHelper");

        ListView listview = (ListView) findViewById(R.id.role);
        String[] values = new String[] { "Fournisseur","Propriétaire" };

    }

     protected void submit(){
         username = (EditText) findViewById(R.id.username);
         password = (EditText) findViewById(R.id.mdp);
         role = (ListView) findViewById(R.id.role);


        /*  "role.setList"*/
         Proprietaire user = new Proprietaire(username.getText().toString(),password.getText().toString());
     }


}
