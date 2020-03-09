package com.example.health965.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.Common.Common;
import com.example.health965.Models.Reservation.Row;
import com.example.health965.Models.Reservation.UpDate.ModelOfUpDate;
import com.example.health965.Models.Reservation.UpDate.UpdateStatusOfReservation;
import com.example.health965.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        final Row model = list.get(position);
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
                TextView Text = bottomSheet.findViewById(R.id.Text);
                if (model.getStatus().equals("waiting"))
                    Text.setText("تم الإتصال بالمريض");
                else if (model.getStatus().equals("confirmed")){
                    Text.setText("أنهاء");
                }
                bottomSheet.findViewById(R.id.LayoutCanceled).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateStatus(model,"cancelled");
                        dialog.dismiss();
                    }
                });
                bottomSheet.findViewById(R.id.LayoutContacted).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateStatus(model,"confirmed");
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
    private void updateStatus(Row model,String status){
        ProgressDialog dialog1 = new ProgressDialog(context);
        dialog1.show();
        Common.getAPIRequest().onUpDateReservation(
                Common.CurrentClinic.getData().getToken().getAccessToken(),
                Common.CurrentClinic.getData().getClinic().getId()+"",
                model.getId()+"",new ModelOfUpDate("confirmed")).enqueue(new Callback<UpdateStatusOfReservation>() {
            @Override
            public void onResponse(Call<UpdateStatusOfReservation> call, Response<UpdateStatusOfReservation> response) {
                if (response.code() == 200){

                }
            }

            @Override
            public void onFailure(Call<UpdateStatusOfReservation> call, Throwable t) {

            }
        });
    }
}
