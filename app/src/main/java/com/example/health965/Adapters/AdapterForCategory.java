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
import com.example.health965.Models.Category.Row;
import com.example.health965.R;
import com.example.health965.UI.Clinics.Clinics_Activity;
import com.example.health965.ViewHolders.ViewHolderFor;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterForCategory extends RecyclerView.Adapter<ViewHolderFor> {
    Context context;
    List<Row> list;

    public AdapterForCategory(Context context, List<Row> list) {
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
        final Row model = list.get(position);
        holder.Name.setText(model.getName());
        holder.Dec.setText(model.getDescription());
        String path = Common.BaseURLForImage + "images/" + model.getIcon().getFor() + "/" + Uri.encode(model.getIcon().getName());
        Picasso.with(context).load(path).into(holder.Image);
        holder.Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Clinics_Activity.class).putExtra("ID",model.getId()));
                Log.i("TTTT",model.getId()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}