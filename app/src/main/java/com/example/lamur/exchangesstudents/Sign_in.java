package com.example.lamur.exchangesstudents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Sign_in extends AppCompatActivity {


    String nom,role;

    TextView name;
    TextView user_role;

    Fournisseur four;
    DBHelper dbhelper = DBHelper.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        role = extras.getString("role");
        if(role.equals("Fournisseur")) {
            four = (Fournisseur) this.getIntent().getSerializableExtra("info_user");
        }

        name = (TextView) findViewById(R.id.nom_user);
        user_role = (TextView) findViewById(R.id.role_user);

        name.setText(four.get_username());
        user_role.setText(role);





    }

    public void complete_profil(View view) {

        if (role.equals("Fournisseur"))
        {
            Fournisseur new_four =  new Fournisseur();
            Intent add_info = new Intent(this, Complete_Profil.class);
            Bundle extras = new Bundle();
            new_four = dbhelper.getFournisseur(four.get_username(),four.getMdp());
            extras.putSerializable("info_user",new_four);
            add_info.putExtra("role",role);
            add_info.putExtras(extras);
            startActivity(add_info);
        }

    }
    public void ajout_service(View view) {

        if (role.equals("Fournisseur"))
        {
            Fournisseur new_four =  new Fournisseur();
            Intent add_service = new Intent(this, Ajout_disponibilite.class);
            Bundle extras = new Bundle();
            new_four = dbhelper.getFournisseur(four.get_username(),four.getMdp());
            extras.putSerializable("info_user",new_four);
            add_service.putExtras(extras);
            startActivity(add_service);
        }

    }

}
