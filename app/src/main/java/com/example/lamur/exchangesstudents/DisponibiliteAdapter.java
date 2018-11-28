package com.example.lamur.exchangesstudents;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DisponibiliteAdapter extends RecyclerView.Adapter<DisponibiliteAdapter.MyViewHolder> {

    List<Service_Disponibilite> disponibilite;
    private OnItemClickListener clickListener;
    private int selectedPos = RecyclerView.NO_POSITION;

    public DisponibiliteAdapter(List<Service_Disponibilite> list)
    {
        this.disponibilite = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.liste_disponibilite_view, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.display(disponibilite.get(position));
        myViewHolder.itemView.setBackgroundColor(selectedPos == position ? Color.GRAY : Color.TRANSPARENT);


    }

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
    @Override
    public int getItemCount() {
        return disponibilite.size();
    }


    public void updateList(List<Service_Disponibilite> newList)
    {
        disponibilite = new ArrayList<>();
        disponibilite.addAll(newList);
        notifyDataSetChanged();


    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView note,nom_service,horaire;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            note = (TextView) itemView.findViewById(R.id.note);
            nom_service = (TextView) itemView.findViewById(R.id.date);
            horaire = (TextView) itemView.findViewById(R.id.horaire);
            itemView.setOnClickListener(this); // bind the listener
        }

        void display(Service_Disponibilite service){
            if(service.equals(null)) {
                nom_service.setText("Not find");
                horaire.setText("Not Find");
                if (service.getMoyenne() == -1) {
                    note.setText("Nouvelle Disponibilité");
                } else {
                    note.setText("Not FInd");
                }
            }
            else
            {
                nom_service.setText(service.getNom_service());
                horaire.setText((service.getJour() + "  " + service.getHeure()));
                if (service.getMoyenne() == -1) {
                    note.setText("Nouvelle Disponibilité");
                } else {
                    note.setText(String.valueOf(service.getMoyenne()));
                }
            }
        }


        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onClick(view, getLayoutPosition());

            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;

            // Updating old as well as new positions
            notifyItemChanged(selectedPos);
            selectedPos = getLayoutPosition();
            notifyItemChanged(selectedPos);

        }
    }




}
