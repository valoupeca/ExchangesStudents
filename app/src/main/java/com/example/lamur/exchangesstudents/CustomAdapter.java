package com.example.lamur.exchangesstudents;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context contex;

    ArrayList<User> personnes = new ArrayList<>();


    public CustomAdapter(Context ctx, ArrayList<User> liste)
    {
        this.contex = ctx;
        this.personnes = liste;

    }
    @Override
    public int getCount() {
        return personnes.size();
    }

    @Override
    public Object getItem(int position) {
        return personnes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(contex).
                    inflate(R.layout.listuser_layout, parent, false);
        }

          User currentItem = (User) getItem(position);

        // get the TextView for item name and item description
        TextView textViewIduser = (TextView)
                convertView.findViewById(R.id.id);
        TextView textviewnomuser = (TextView)
                convertView.findViewById(R.id.nom);

        //sets the text for item name and item description from the current item object
        textViewIduser.setText(String.valueOf(currentItem.get_id()));
        textviewnomuser.setText(currentItem.get_username());

        // returns the view for the current row
        return convertView;

    }
}
