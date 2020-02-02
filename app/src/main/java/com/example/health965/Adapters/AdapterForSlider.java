package com.example.health965.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class AdapterForSlider extends RecyclerView.Adapter<AdapterForSlider.ViewHolderForSlider> {
    List<Integer> list;
    Context context;

    public AdapterForSlider(List<Integer> list, Context context) {
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
        holder.imageView.setImageResource(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderForSlider extends RecyclerView.ViewHolder {
        RoundedImageView imageView;
        public ViewHolderForSlider(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ImageSilder);
        }
    }
}
