package com.example.health965.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.Common.Common;
import com.example.health965.Models.Category.Icon;
import com.example.health965.Models.Category.Row;
import com.example.health965.Models.Model;
import com.example.health965.R;
import com.example.health965.UI.Clinics.Clinics_Activity;
import com.example.health965.ViewHolders.ViewHolderFor;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.List;

public class AdapterFor extends RecyclerView.Adapter<ViewHolderFor> {
    Context context;
    List<Row> list;
    BottomNavigationView bottomNavigationView;

    public AdapterFor(Context context, List<Row> list) {
        this.context = context;
        this.list = list;
        list.add(list.size(),new Row(0,"عروض مميزة","افضل عروض عيادات الكويت",new Icon(R.drawable.discount,null,
                null,null,null)));
    }

    @NonNull
    @Override
    public ViewHolderFor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.model,parent,false);
        return new ViewHolderFor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFor holder, int position) {
        final Row model = list.get(position);
        if (model.getId() == 0){
            holder.Name.setText("عروض مميزة");
            holder.Dec.setText("افضل عروض عيادات الكويت");
            Picasso.with(context).load(R.drawable.discount).into(holder.Image);
            holder.Card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //context.startActivity(new Intent(context, Clinics_Activity.class));
                }
            });
        }else {
            holder.Name.setText(model.getName());
            holder.Dec.setText(model.getDescription());
            String path = Common.BaseURL + "images/" + model.getIcon().getFor() + "/" + Uri.encode(model.getIcon().getName());
            Log.i("TTT", path);
            Picasso.with(context).load(path).into(holder.Image);
            holder.Card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, Clinics_Activity.class).putExtra("ID",model.getId()));
                    Log.i("TTTT",model.getId()+"");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}