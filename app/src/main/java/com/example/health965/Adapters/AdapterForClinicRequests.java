package com.example.health965.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.health965.Models.Reservation.Row;
import com.example.health965.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterForClinicRequests extends RecyclerView.Adapter<AdapterForClinicRequests.ViewHolderForClinicRequests> {
    List<Row> list;
    Context context;

    public AdapterForClinicRequests(List<Row> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderForClinicRequests onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderForClinicRequests(LayoutInflater.from(context).inflate(R.layout.model_requests,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderForClinicRequests holder, int position) {
        Row model = list.get(position);
        holder.Name.setText(model.getClient().getFullName());
        holder.Email.setText(model.getClient().getEmail());
        holder.Phone.setText(model.getClient().getMobilePhone());
        if (model.getDoctor() != null)
            holder.Doctor.setText(model.getDoctor().getName());
        else
            holder.Doctor.setText("-------");
        try {
            holder.ReservationDate.setText(new SimpleDateFormat("hh:mm a").format(
                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(model.getCreatedAt())));

            holder.ReservationTime.setText(new SimpleDateFormat("dd/MM/yyyy").format(
                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(model.getCreatedAt())));
        }  catch (ParseException e) {
            e.printStackTrace();
        }
        holder.StatusRequest.setText(model.getStatus());
        holder.Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog dialog = new BottomSheetDialog(context,R.style.BottomSheetDialogTheme);
                View bottomSheet = LayoutInflater.from(context).inflate(R.layout.model_bottom_sheet
                        ,(RelativeLayout)holder.view.findViewById(R.id.container));
                dialog.setContentView(bottomSheet);
                dialog.show();
                bottomSheet.findViewById(R.id.LayoutCanceled).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                bottomSheet.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderForClinicRequests extends RecyclerView.ViewHolder {
        TextView Name,Email,Phone,Doctor,ReservationDate,ReservationTime,StatusRequest;
        RelativeLayout Menu;
        View view;
        public ViewHolderForClinicRequests(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Name);
            Email = itemView.findViewById(R.id.Email);
            Phone = itemView.findViewById(R.id.Phone);
            Doctor = itemView.findViewById(R.id.Doctor);
            ReservationDate = itemView.findViewById(R.id.Date);
            ReservationTime = itemView.findViewById(R.id.Time);
            StatusRequest = itemView.findViewById(R.id.Status);
            Menu = itemView.findViewById(R.id.ListOfChose);
            view = itemView;
        }
    }
}
