package com.example.lamur.exchangesstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Complete_Profil extends AppCompatActivity {


    EditText adresse;
    EditText ville;
    EditText phone;
    EditText cp;
    EditText company_name;
    EditText description;
    RadioButton yes;
    RadioButton non;
    DBHelper dbhelper = DBHelper.getInstance(this);
    Fournisseur user;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete__profil);
        user =  (Fournisseur)this.getIntent().getSerializableExtra("info_user");
        role  = this.getIntent().getStringExtra("role");
        adresse = (EditText) findViewById(R.id.adresse_id);
        cp = (EditText) findViewById(R.id.cp_id);
        ville = (EditText) findViewById(R.id.ville_id);
        phone = (EditText) findViewById(R.id.phone_id);
        description = (EditText) findViewById(R.id.description);
        company_name = (EditText) findViewById(R.id.company_name);
        yes = (RadioButton) findViewById(R.id.oui_check);
        non = (RadioButton) findViewById(R.id.non_check);


        if(user.getAdresse() != null && !user.getAdresse().isEmpty())
        {
            adresse.setText(user.getAdresse().toString());
        }
        if(user.getVille() != null && !user.getVille().isEmpty())
        {
            ville.setText(user.getVille().toString());
        }
        if(user.getPhone() != null &&!user.getPhone().isEmpty())
        {
            phone.setText(user.getPhone().toString());
        }
        if(user.getDescription() != null &&!user.getDescription().isEmpty())
        {
            description.setText(user.getDescription().toString());
        }
        if(user.getCompany() != null &&!user.getCompany().isEmpty())
        {
            company_name.setText(user.getCompany().toString());
        }
        if(user.getCode_postal()  != null &&!user.getCode_postal().isEmpty())
        {
            cp.setText((user.getCode_postal()));
        }
    }

    public void submit(View view) {

        if (TextUtils.isEmpty(adresse.getText()) || TextUtils.isEmpty(phone.getText()) || TextUtils.isEmpty(company_name.getText())) {

            if(TextUtils.isEmpty(adresse.getText()))
                adresse.setError("Data is required!");
            else if(TextUtils.isEmpty(phone.getText()))
                phone.setError("Data is required!");
            else if(TextUtils.isEmpty(company_name.getText()))
                company_name.setError("Data is required!");
            Toast.makeText(this, "Veuillez remplir tout les champs", Toast.LENGTH_LONG).show();


        }
        else
        {
            if(role.equals("Fournisseur"))
            {
                    user.setAdresse(adresse.getText().toString());
                    user.setPhone(phone.getText().toString());
                    user.setDescription(description.getText().toString());
                    user.setCompany(company_name.getText().toString());
                    user.setVille(ville.getText().toString());
                    user.setCode_postal(cp.getText().toString());
                    if(yes.isChecked())
                    {
                        user.setLicense(true);
                    }
                    else
                    {
                        user.setLicense(false);
                    }

                    dbhelper.addOrUpdateUser(user,role);
                Toast.makeText(this, "Profil Completé", Toast.LENGTH_LONG).show();
                finish();
            }
        }

    }
}
