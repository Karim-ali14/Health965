package com.example.health965.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.Common.Common;
import com.example.health965.Models.OfferForClinic.Row;
import com.example.health965.R;
import com.example.health965.UI.Clinics_Details.Clinics_Details_activity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterForOfferPage extends RecyclerView.Adapter<AdapterForOfferPage.ViewHolderForOfferPage>{
    List<Row> list;
    Context context;
    boolean insideClinic;
    public AdapterForOfferPage(List<Row> list, Context context,boolean insideClinic) {
        this.list = list;
        this.context = context;
        this.insideClinic = insideClinic;
    }

    @NonNull
    @Override
    public ViewHolderForOfferPage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderForOfferPage(LayoutInflater.from(context)
                .inflate(R.layout.offer_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderForOfferPage holder, int position) {
        final Row row = list.get(position);
        Picasso.with(context).load(Common.BaseURL+"images/"+
                row.getBackground().getFor()+"/"+
                Uri.encode(row.getBackground().getName())).into(holder.imageView);
        holder.CardOfOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!insideClinic)
                    context.startActivity(new Intent(context, Clinics_Details_activity.class).putExtra("Clinic_Id",row.getClinicId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderForOfferPage extends RecyclerView.ViewHolder{
        ImageView imageView;
        Button ConnectWithUs;
        CardView CardOfOffer;
        public ViewHolderForOfferPage(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.Image);
            ConnectWithUs = itemView.findViewById(R.id.ConnectWithUs);
            CardOfOffer = itemView.findViewById(R.id.CardOfOffer);
        }
    }
}