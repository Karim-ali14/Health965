package com.example.health965.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.Models.ModelsForCilinics;
import com.example.health965.R;
import com.example.health965.UI.Clinics_Details_activity;

import java.util.List;

public class AdapterForClinics extends RecyclerView.Adapter<AdapterForClinics.ViewHolderForClicnics> {
    List<ModelsForCilinics> list;
    Context context;

    public AdapterForClinics(List<ModelsForCilinics> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderForClicnics onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderForClicnics(LayoutInflater.from(context).inflate(R.layout.model_clinics,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderForClicnics holder, final int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,Clinics_Details_activity.class).putExtra("Title",list.get(position).getName()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderForClicnics extends RecyclerView.ViewHolder{
        ImageView image;
        TextView Address,TypeWork;
        CardView cardView;
        public ViewHolderForClicnics(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ImageForClinic);
            Address = itemView.findViewById(R.id.Address);
            TypeWork = itemView.findViewById(R.id.TypeOfWork);
            cardView = itemView.findViewById(R.id.Card);
        }
    }
}
