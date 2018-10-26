package com.example.lamur.exchangesstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Sign_up extends AppCompatActivity {

    DBHelper dbhelper;
    Spinner role;
    EditText username;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        Spinner listview = (Spinner) findViewById(R.id.role);
        String[] values = new String[] { "Fournisseur","Propriétaire" };


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);


        // Assign adapter to ListView
        listview.setAdapter(adapter);
    }

     public void submit(View view){
         username = (EditText) findViewById(R.id.username);
         password = (EditText) findViewById(R.id.mdp);
         role = (Spinner) findViewById(R.id.role);


         if(role.getSelectedItem().toString().equals("Propriétaire"))
         {
             Proprietaire user = new Proprietaire(username.getText().toString(),password.getText().toString());
             Toast.makeText(this,"Proprietaire ajouté",Toast.LENGTH_LONG).show();
             BackgroundTask bg = new BackgroundTask(this);
             bg.execute("addUser",username.getText().toString(),password.getText().toString(),role.getSelectedItem().toString());
             finish();
         }
         else{
             Fournisseur user = new Fournisseur(username.getText().toString(),password.getText().toString());
             BackgroundTask bg = new BackgroundTask(this);
             Toast.makeText(this,"fournisseur ajouté",Toast.LENGTH_LONG).show();
             bg.execute("addUser",username.getText().toString(),password.getText().toString(),role.getSelectedItem().toString());
             finish();
         }



    }


}
