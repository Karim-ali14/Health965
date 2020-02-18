package com.example.health965.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.Models.Model;
import com.example.health965.R;
import com.example.health965.UI.Clinics.Clinics_Activity;
import com.example.health965.ViewHolders.ViewHolderFor;

import java.util.List;

public class AdapterFor extends RecyclerView.Adapter<ViewHolderFor> {
    Context context;
    List<Model> list;

    public AdapterFor(Context context, List<Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderFor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.model,parent,false);
        return new ViewHolderFor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFor holder, int position) {
        Model model = list.get(position);
        holder.Name.setText(model.getName());
        holder.Dec.setText(model.getDec());
        holder.Image.setImageResource(model.getImage());
        holder.Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Clinics_Activity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
