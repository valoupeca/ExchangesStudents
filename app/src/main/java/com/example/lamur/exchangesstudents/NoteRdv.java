package com.example.lamur.exchangesstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NoteRdv extends AppCompatActivity {

    Rendez_Vous rdv;
    DBHelper dbhelper = DBHelper.getInstance(this);
    EditText note,commentaire;
    Proprietaire user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_rdv);

        rdv = (Rendez_Vous) this.getIntent().getSerializableExtra("info_rdv");
        user = (Proprietaire) this.getIntent().getSerializableExtra("info_user");
        note = (EditText) findViewById(R.id.note);
        commentaire = (EditText) findViewById(R.id.commentaire);



    }


    public void Ajout_Note(View view){
        if (TextUtils.isEmpty(note.getText()) || TextUtils.isEmpty(commentaire.getText())) {

            if(TextUtils.isEmpty(note.getText()) && TextUtils.isEmpty(commentaire.getText())) {
                note.setError("Data is required!");
                commentaire.setError("Data is required!");
            }
            else if((TextUtils.isEmpty(note.getText())) ) {
                note.setError("Data is required!");
            }
            else
                commentaire.setError("Data is required");

            Toast.makeText(this, "Veuillez remplir tout les champs", Toast.LENGTH_LONG).show();


        }
        else {

            dbhelper.addOrUpdateDisponibilite(rdv.getIdServic(), rdv.getHeure(), rdv.getJour(), user.get_id(), rdv.getIdDispo());

        }
    }
}
