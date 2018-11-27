package com.example.lamur.exchangesstudents;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class DisponibiliteAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<Service_Disponibilite> disponibilite;

    public DisponibiliteAdapter(List<Service_Disponibilite> list)
    {
        this.disponibilite = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.activity_search__disponibilite, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.display(disponibilite.get(i));

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

}
