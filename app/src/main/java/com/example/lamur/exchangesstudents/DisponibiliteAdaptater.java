package com.example.lamur.exchangesstudents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DisponibiliteAdaptater extends BaseAdapter {
    Context contex;

    ArrayList<Service_Disponibilite> services = new ArrayList<>();


    public DisponibiliteAdaptater(Context ctx, ArrayList<Service_Disponibilite> liste)
    {
        this.contex = ctx;
        this.services = liste;

    }
    @Override
    public int getCount() {
        return services.size();
    }

    @Override
    public Object getItem(int position) {
        return services.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(contex).
                    inflate(R.layout.listservice_layout, parent, false);
        }

        Service_Disponibilite currentItem = (Service_Disponibilite) getItem(position);

        // get the TextView for item name and item description
        TextView textViewIdservice = (TextView)
                convertView.findViewById(R.id.id_service);
        TextView textviewnomservice = (TextView)
                convertView.findViewById(R.id.date);

        TextView textviewtauxhoraire = (TextView)
                convertView.findViewById(R.id.taux_horaire);

        //sets the text for item name and item description from the current item object
        textViewIdservice.setText(String.valueOf(currentItem.get_id()));
        textviewtauxhoraire.setText(String.valueOf(currentItem.getNom_service()));
        textviewnomservice.setText(currentItem.getJour() + "  " + currentItem.getHeure());

        // returns the view for the current row
        return convertView;

    }

    public void updateReceiptsList(ArrayList<Service_Disponibilite> newlist) {
        services.clear();
        services.addAll(newlist);
        this.notifyDataSetChanged();
    }


}
