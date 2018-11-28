package com.example.lamur.exchangesstudents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RdvCustomAdapter extends BaseAdapter {

    Context contex;

    ArrayList<Rendez_Vous> rdv = new ArrayList<>();


    public RdvCustomAdapter(Context ctx, ArrayList<Rendez_Vous> liste)
    {
        this.contex = ctx;
        this.rdv = liste;

    }
    @Override
    public int getCount() {
        return rdv.size();
    }

    @Override
    public Object getItem(int position) {
        return rdv.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(contex).
                    inflate(R.layout.list_rendez_vous, parent, false);
        }

        Rendez_Vous currentItem = (Rendez_Vous) getItem(position);

        // get the TextView for item name and item description
        TextView textViewIdservice = (TextView)
                convertView.findViewById(R.id.id_rdv);
        TextView nom_four = (TextView)
                convertView.findViewById(R.id.nom_four);
        // get the TextView for item name and item description
        TextView nom_service = (TextView)
                convertView.findViewById(R.id.nom_serv);
        TextView date = (TextView)
                convertView.findViewById(R.id.date);
        // get the TextView for item name and item description
        TextView commente = (TextView)
                convertView.findViewById(R.id.commente);
        //sets the text for item name and item description from the current item object
        textViewIdservice.setText(String.valueOf(currentItem.getIdRendez_vous()));
        nom_four.setText(currentItem.getNomFourniseur());
        nom_service.setText(currentItem.getNomService());
        date.setText(currentItem.getJour()+ "  " + currentItem.getHeure());
        commente.setText(currentItem.getIdDispo());


        // returns the view for the current row
        return convertView;

    }
}
