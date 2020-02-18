package com.example.health965.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.Models.ModelOfCertificates;
import com.example.health965.R;

import java.util.List;

public class AdapterForCertificates extends RecyclerView.Adapter<AdapterForCertificates.ViewHolderForCertificates> {
    List<ModelOfCertificates> list;
    Context context;
    public AdapterForCertificates(List<ModelOfCertificates> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolderForCertificates onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.certificates_model,parent,false);
        return new ViewHolderForCertificates(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderForCertificates holder, int position) {
        ModelOfCertificates model = list.get(position);
        holder.Line.setVisibility(View.VISIBLE);
        holder.NameCertificates.setText(model.getNameCertificates());
        holder.Date.setText(model.getDate());
        if (list.size()-1 == position)
            holder.Line.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderForCertificates extends RecyclerView.ViewHolder {
        TextView NameCertificates,Date;
        View Line;
        public ViewHolderForCertificates(@NonNull View itemView) {
            super(itemView);
            NameCertificates =itemView.findViewById(R.id.NameOfCertificate);
            Date =itemView.findViewById(R.id.Date);
            Line =itemView.findViewById(R.id.LineC);
        }
    }
}
