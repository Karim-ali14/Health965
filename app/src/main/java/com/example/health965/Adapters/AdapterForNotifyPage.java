package com.example.health965.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.Models.Notification.Data;
import com.example.health965.R;

import java.util.List;

public class AdapterForNotifyPage extends RecyclerView.Adapter<AdapterForNotifyPage.ViewHolderForNotify> {
    List<Data> list;
    Context context;

    public AdapterForNotifyPage(List<Data> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderForNotify onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderForNotify(LayoutInflater.from(context)
                .inflate(R.layout.notify_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderForNotify holder, int position) {
        holder.Massage.setText(list.get(position).getBody());
        //holder.Time.setText("منذ "+list.get(position).);
        if (list.size()-1 == position)
            holder.line.setVisibility(View.GONE);
        else
            holder.line.setVisibility(View.VISIBLE);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderForNotify extends RecyclerView.ViewHolder {
        TextView Massage,Time;
        View line;
        public ViewHolderForNotify(@NonNull View itemView) {
            super(itemView);
            Massage = itemView.findViewById(R.id.massage);
            Time = itemView.findViewById(R.id.Time);
            line = itemView.findViewById(R.id.Line);
        }
    }
}
