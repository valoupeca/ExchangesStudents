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
    static Integer cle[] = {1,2,3,5,4,7};
    static String alphabet1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWzXYZ1234567890&é'-è_çà)(=/*-+²";


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

    public static String cryptage(String mdp){
        int a = mdp.length();
        int b = alphabet1.length();
        char[] tab = new char[a];
        char[] alpha = new char[b];
        for(int i = 0; i < b;i++){
            alpha[i] = alphabet1.charAt(i);
        }
        for(int i = 0; i < a;i++){
            tab[i] = mdp.charAt(i);
        }

        int k=0;
        int j=0;
        for(int i=0;i<mdp.length(); i++){

            while(!(tab[i]==alpha[j])){
                j++;
            }
            if ((j+cle[k])>=alphabet1.length()){
                tab[i]=alpha[j+cle[k]-alphabet1.length()];
            }
            else{
                tab[i]=alpha[j+cle[k]];
            }
            j=0;
            k=+1;
            if(k>cle.length) {
                k=0;
            }
        }
        String test = new String(tab);
        return test;
    }


    public static String decryptage(String mdp){
        int a = mdp.length();
        int b = alphabet1.length();
        char[] tab = new char[a];
        char[] alpha = new char[b];
        for(int i = 0; i < b;i++){
            alpha[i] = alphabet1.charAt(i);
        }
        for(int i = 0; i < a;i++){
            tab[i] = mdp.charAt(i);
        }

        int k=0;
        for(int i=0;i<mdp.length(); i++){
            int j=0;
            while(!(tab[i]==alpha[j])){
                j++;
            }
            if (j-cle[k]<0){
                tab[i]=alpha[j-cle[k]+alphabet1.length()];
            }
            else{
                tab[i]=alpha[j-cle[k]];
            }

            k=+1;
            if(k>cle.length) {
                k=0;
            }
        }
        String test = new String(tab);
        return test;
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
