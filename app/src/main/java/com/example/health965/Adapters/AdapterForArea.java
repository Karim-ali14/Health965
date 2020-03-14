package com.example.health965.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.Models.Governorate.Row;
import com.example.health965.UI.AreaDetails.AreaDetailsActivity;
import com.example.health965.R;

import java.util.List;

public class AdapterForArea extends RecyclerView.Adapter<AdapterForArea.ViewHolderOfArea> {
    List<Row> list;
    Context context;
    int C_ID;
    public AdapterForArea(List<Row> list, Context context,int C_ID) {
        this.list = list;
        this.context = context;
        this.C_ID = C_ID;
    }

    @NonNull
    @Override
    public ViewHolderOfArea onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderOfArea(LayoutInflater.from(context).inflate(R.layout.model_area,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderOfArea holder, int position) {
        final Row row = list.get(position);
        holder.NameOFArea.setText(row.getName());
        holder.Line.setVisibility(View.VISIBLE);
        if (list.size()-1 == position)
            holder.Line.setVisibility(View.GONE);
        holder.Row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AreaDetailsActivity.class).putExtra("Row",row).putExtra("ID",C_ID));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderOfArea extends RecyclerView.ViewHolder {
        TextView NameOFArea;
        View Line;
        RelativeLayout Row;
        public ViewHolderOfArea(@NonNull View itemView) {
            super(itemView);
            NameOFArea = itemView.findViewById(R.id.NameOfArea);
            Line = itemView.findViewById(R.id.Line);
            Row = itemView.findViewById(R.id.Row);
        }
    }
}
