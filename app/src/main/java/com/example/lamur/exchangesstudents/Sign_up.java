package com.example.lamur.exchangesstudents;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Sign_up extends AppCompatActivity {

    Spinner role;
    EditText username;
    EditText password;
    DBHelper dbhelper = DBHelper.getInstance(this);
    static Integer cle[] = {1,2,3,5,4,7};
    static String alphabet1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWzXYZ1234567890&é'-è_çà)(=/*-+²";


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
             if(username.getText().length()==0 || password.getText().length()==0) {
                 Toast.makeText(this, "Veuillez remplir tout les champs", Toast.LENGTH_LONG).show();
             }
             else {
                 Proprietaire user = new Proprietaire(username.getText().toString(), password.getText().toString());
                 dbhelper.addOrUpdateUser(user,role.getSelectedItem().toString());
                 Toast.makeText(this, "Propriétaire ajouté", Toast.LENGTH_LONG).show();
                 finish();
             }
         }
         else{
             if(username.getText().length()==0 || password.getText().length()==0) {
                 Toast.makeText(this, "Veuillez remplir tout les champs", Toast.LENGTH_LONG).show();
             }
             else {

                 Fournisseur user = new Fournisseur(username.getText().toString(), password.getText().toString());
                 String test1 = role.getSelectedItem().toString();
                 dbhelper.addOrUpdateUser(user,role.getSelectedItem().toString());
                 Toast.makeText(this, "Fournisseur ajouté", Toast.LENGTH_LONG).show();
                 finish();
             }
         }






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

}
