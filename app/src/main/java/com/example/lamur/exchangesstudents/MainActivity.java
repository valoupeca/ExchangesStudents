package com.example.lamur.exchangesstudents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Boolean isAdmin;

    DBHelper dbhelper;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        dbhelper = DBHelper.getInstance(this);



    }


    public void sign_up(View view)
    {
        Intent inscription_page = new Intent(this, Sign_up.class);

        startActivity(inscription_page);
    }


    public void sign_in(View view) {

            if (dbhelper.isReal(username.getText().toString(), password.getText().toString())) {
                if (dbhelper.isAdmin(username.getText().toString(), password.getText().toString())) {
                    Intent admin_page_intent = new Intent(this, Admin_page.class);
                    startActivity(admin_page_intent);
                    finish();
                } else {

                    String role = dbhelper.infoRole(username.getText().toString(), password.getText().toString());
                    if(role.equals("Fournisseur"))
                    {
                        Intent welcome_page = new Intent(this, Sign_in_fournisseur.class);
                        Bundle extras = new Bundle();
                        Fournisseur fournis = dbhelper.getFournisseur(username.getText().toString(), password.getText().toString());
                        extras.putSerializable("info_user",fournis );
                        extras.putString("role", role);
                        welcome_page.putExtras(extras);
                        startActivity(welcome_page);
                    }
                    else
                    {
                        Intent welcome_page = new Intent(this, Sign_in_utilisateur.class);
                        Bundle extras = new Bundle();
                        Proprietaire proprietaire = dbhelper.getProprietaire(username.getText().toString(), password.getText().toString());
                        extras.putSerializable("info_user",proprietaire );
                        extras.putString("role", role);
                        welcome_page.putExtras(extras);
                        startActivity(welcome_page);
                        finish();
                    }


                }
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Utilisateur non trouvé", Toast.LENGTH_SHORT);
                toast.show();
            }

        }

}
