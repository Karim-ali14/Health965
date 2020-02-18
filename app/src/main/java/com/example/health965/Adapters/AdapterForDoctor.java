package com.example.health965.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.Models.ModelDoctor;
import com.example.health965.R;

import java.util.List;

public class AdapterForDoctor extends RecyclerView.Adapter<AdapterForDoctor.ViewHolderForDoctor> {
    List<ModelDoctor> list;
    Context context;
    int CurrentPosition;
    int OldPosition = -1;
    ViewHolderForDoctor OldCard = null;

    public AdapterForDoctor(List<ModelDoctor> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderForDoctor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderForDoctor(LayoutInflater.from(context).inflate(R.layout.model_doctors,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderForDoctor holder, final int position) {
        final ModelDoctor model = list.get(position);
        holder.DoctorImage.setImageResource(model.getImage());
        holder.Name.setText(model.getName());
        holder.Dec.setText(model.getDec());
        holder.Check.setVisibility(View.GONE);
        holder.CardDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentPosition = position;
                if (model.isSelected()){
                    holder.CardDoctor.setCardBackgroundColor(context.getResources().getColor(R.color.White));
                    holder.Name.setTextColor(context.getResources().getColor(R.color.Black));
                    holder.CardDoctor.setRadius(30);
                    holder.Check.setVisibility(View.GONE);
                    model.setSelected(false);
                }else if (!model.isSelected()){
                    holder.CardDoctor.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
                    holder.Name.setTextColor(context.getResources().getColor(R.color.White));
                    holder.CardDoctor.setRadius(30);
                    holder.Check.setVisibility(View.VISIBLE);
                    model.setSelected(true);
                    if (OldPosition != -1 && OldPosition != position){
                        list.get(OldPosition).setSelected(false);
                        OldCard.CardDoctor.setCardBackgroundColor(context.getResources().getColor(R.color.White));
                        OldCard.Name.setTextColor(context.getResources().getColor(R.color.Black));
                        OldCard.CardDoctor.setRadius(30);
                        OldCard.Check.setVisibility(View.GONE);
                        OldCard = holder;
                        OldPosition = position;
                    }else {
                        OldCard = holder;
                        OldPosition = position;
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderForDoctor extends RecyclerView.ViewHolder {
        ImageView Check,DoctorImage;
        TextView Name,Dec;
        CardView CardDoctor;
        public ViewHolderForDoctor(@NonNull View itemView) {
            super(itemView);
            Check = itemView.findViewById(R.id.CheckImage);
            DoctorImage = itemView.findViewById(R.id.ImageDoctor);
            Name = itemView.findViewById(R.id.NameOfDoctor);
            Dec = itemView.findViewById(R.id.Dec);
            CardDoctor = itemView.findViewById(R.id.CardDoctor);
        }
    }
}
