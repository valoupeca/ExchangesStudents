package com.example.lamur.exchangesstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
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

        if(rdv.getNote_user() != null && rdv.getNote_user() != -1.0)
        {
            note.setText(rdv.getNote_user().toString());
        }
        if(rdv.getCommentaire() != null && !rdv.getCommentaire().isEmpty())
        {
            commentaire.setText(rdv.getCommentaire().toString());
        }

        note.setInputType(InputType.TYPE_CLASS_NUMBER );
        note.setKeyListener(DigitsKeyListener.getInstance("12345"));
        note.setSingleLine(true);


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
            int nb_vote = dbhelper.nombre_vote_disponibilite(rdv.getIdDispo());

            double moyenne = (dbhelper.moyenne_dispo(rdv.getIdDispo()))*nb_vote;
            moyenne = moyenne + Double.parseDouble(note.getText().toString());
            nb_vote = nb_vote + 1;
            moyenne = (moyenne/nb_vote);
            dbhelper.addOrUpdateDisponibilite(rdv.getServ().get_id(), rdv.getServ().getHeure(), rdv.getServ().getJour(), rdv.getServ().getId_fournisseur(), rdv.getIdDispo(),nb_vote, moyenne);
            dbhelper.ajout_RDV(user.get_id(),rdv.getIdDispo(),rdv.getIdRendez_vous(),commentaire.getText().toString(),Double.parseDouble(note.getText().toString()));
            finish();

        }
    }
}
