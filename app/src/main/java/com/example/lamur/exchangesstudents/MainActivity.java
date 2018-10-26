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


    public void sign_up(View view)
    {
        Intent inscription_page = new Intent(this, Sign_up.class);

        startActivity(inscription_page);
    }
    public void sign_in(View view) {

        if(dbhelper.isReal(username.getText().toString(),password.getText().toString()))
        {
            if(dbhelper.isAdmin(username.getText().toString(),password.getText().toString()))
            {
                Intent admin_page_intent = new Intent(this, Admin_page.class);
                admin_page_intent.putExtra("name",username.getText().toString());
                admin_page_intent.putExtra("role",dbhelper.infoUser(username.getText().toString(),password.getText().toString()));
                startActivity(admin_page_intent);

            }
            else
            {
                Intent welcome_page = new Intent(this, Sign_in.class);
                welcome_page.putExtra("DbHelper",dbhelper);
                startActivity(welcome_page);
            }
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Utilisateur non trouvé", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
