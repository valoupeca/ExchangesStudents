package com.example.lamur.exchangesstudents;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MyViewHolder extends RecyclerView.ViewHolder {

    private TextView note,nom_service,horaire;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        note = (TextView) itemView.findViewById(R.id.note);
        nom_service = (TextView) itemView.findViewById(R.id.nom_service);
        horaire = (TextView) itemView.findViewById(R.id.horaire);
    }

    void display(Service_Disponibilite service){
        nom_service.setText(service.getNom_service());
        horaire.setText((service.getJour()+ "  "+ service.getHeure()));
        note.setText(String.valueOf(service.getMoyenne()));
    }


}
