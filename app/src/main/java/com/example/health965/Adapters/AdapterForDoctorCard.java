package com.example.health965.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.Models.ModelOfCardDoctor;
import com.example.health965.R;

import java.util.List;

public class AdapterForDoctorCard extends RecyclerView.Adapter<AdapterForDoctorCard.ViewHolderForDoctorCard> {
    List<ModelOfCardDoctor> list;
    Context context;

    public AdapterForDoctorCard(List<ModelOfCardDoctor> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderForDoctorCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderForDoctorCard(LayoutInflater.from(context).inflate(R.layout.model_doctor_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderForDoctorCard holder, int position) {
        ModelOfCardDoctor model = list.get(position);
        holder.DoctorIamge.setImageResource(model.getIamgeOfDoctoer());
        holder.ClinicImage.setImageResource(model.getImageOfClinics());
        holder.Address.setText(model.getAddress());
        holder.DoctorName.setText(model.getNameOfDoctor());
        holder.NameOfClinic.setText(model.getNameOfClinic());
        holder.DoctorDec.setText(model.getDec());
        holder.TypeOfWork.setText(model.getTypeOfWork());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderForDoctorCard extends RecyclerView.ViewHolder {
        ImageView DoctorIamge,ClinicImage;
        TextView DoctorName,DoctorDec,NameOfClinic,Address,TypeOfWork;
        public ViewHolderForDoctorCard(@NonNull View itemView) {
            super(itemView);
            DoctorIamge = itemView.findViewById(R.id.ImageDoctor);
            ClinicImage = itemView.findViewById(R.id.ImageForClinic);
            DoctorName = itemView.findViewById(R.id.NameOfDoctor);
            DoctorDec = itemView.findViewById(R.id.Dec);
            NameOfClinic = itemView.findViewById(R.id.NameOfClinics);
            Address = itemView.findViewById(R.id.Address);
            TypeOfWork = itemView.findViewById(R.id.TypeOfWork);
        }
    }
}
