package com.example.health965.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.Common.Common;
import com.example.health965.Models.Offers.Row;
import com.example.health965.R;
import com.example.health965.UI.Clinics_Details_activity;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterForSlider extends RecyclerView.Adapter<AdapterForSlider.ViewHolderForSlider> {
    List<Row> list;
    Context context;

    public AdapterForSlider(List<Row> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderForSlider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slider_image,parent,false);
        return new ViewHolderForSlider(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderForSlider holder, int position) {
        final Row row = list.get(position);
        Picasso.with(context).load(Common.BaseURL+"images/"+row.getBackground().getFor()+"/"+ Uri.encode(row.getBackground().getName())).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Clinics_Details_activity.class).putExtra("Clinic_Id",row.getClinicId()));
            }
        });

//        Picasso.with(context).load(Common.BaseURL+"images/"+row.getIcon().getFor()+"/"+ Uri.encode(row.getIcon().getName())).into(holder.Icon);
//        holder.Title.setText(row.getTitle());
//        holder.Description.setText(row.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderForSlider extends RecyclerView.ViewHolder {
        RoundedImageView imageView;
//        ImageView Icon;
//        TextView Title,Description;
        public ViewHolderForSlider(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ImageSilder);
//            Icon = itemView.findViewById(R.id.Icon);
//            Title = itemView.findViewById(R.id.Title);
//            Description = itemView.findViewById(R.id.description);
        }
    }
}
