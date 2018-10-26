package com.example.lamur.exchangesstudents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    DBHelper dbhelper;
    Boolean isAdmin;


    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        dbhelper = new DBHelper(this);
    }


    public void sign_in(View view) {

        if(dbhelper.isReal(username.getText().toString(),password.getText().toString()))
        {
            if(true)
            {
                Intent admin_page_intent = new Intent(this, Admin_page.class);
                admin_page_intent.putExtra("DbHelper",dbhelper);
                startActivityForResult(admin_page_intent,0);

            }
            else
            {
                Intent aboutIntent = new Intent(this, Sign_in.class);
                startActivity(aboutIntent);
            }
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Utilisateur non trouvé", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
