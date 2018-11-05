package com.example.lamur.exchangesstudents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ServiceCustomAdapter extends BaseAdapter {

    Context contex;

    ArrayList<Services> services = new ArrayList<>();


    public ServiceCustomAdapter(Context ctx, ArrayList<Services> liste)
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

        Services currentItem = (Services) getItem(position);

        // get the TextView for item name and item description
        TextView textViewIdservice = (TextView)
                convertView.findViewById(R.id.id_service);
        TextView textviewnomservice = (TextView)
                convertView.findViewById(R.id.nom_service);

        TextView textviewtauxhoraire = (TextView)
                convertView.findViewById(R.id.taux_horaire);

        //sets the text for item name and item description from the current item object
        textviewtauxhoraire.setText((int) currentItem.getTaux_horraire());
        textviewnomservice.setText(currentItem.getNom());

        // returns the view for the current row
        return convertView;

    }

}
