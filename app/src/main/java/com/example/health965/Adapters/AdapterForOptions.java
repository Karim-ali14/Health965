package com.example.health965.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.Common.Common;
import com.example.health965.Models.Options.Row;
import com.example.health965.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterForOptions extends RecyclerView.Adapter<AdapterForOptions.ViewHolderForOptions> {
    Context context;
    List<Row> rows;

    public AdapterForOptions(Context context, List<Row> rows) {
        this.context = context;
        this.rows = rows;
    }

    @NonNull
    @Override
    public ViewHolderForOptions onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderForOptions(LayoutInflater.from(context).inflate(R.layout.image_option,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderForOptions holder, int position) {
        Picasso.with(context).load(Common.BaseURLForImage+"images/"+
                rows.get(position).getImage().getFor()+"/"+
                Uri.encode(rows.get(position).getImage().getName()))
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    class ViewHolderForOptions extends RecyclerView.ViewHolder{
        ImageView image;
        public ViewHolderForOptions(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_Option);
        }
    }
}
