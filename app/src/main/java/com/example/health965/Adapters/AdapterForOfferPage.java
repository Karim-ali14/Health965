package com.example.health965.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.R;

import java.util.List;

public class AdapterForOfferPage extends RecyclerView.Adapter<AdapterForOfferPage.ViewHolderForOfferPage>{
    List<Integer> list;
    Context context;

    public AdapterForOfferPage(List<Integer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderForOfferPage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderForOfferPage(LayoutInflater.from(context)
                .inflate(R.layout.offer_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderForOfferPage holder, int position) {
        holder.imageView.setImageResource(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderForOfferPage extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolderForOfferPage(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.Image);
        }
    }
}
