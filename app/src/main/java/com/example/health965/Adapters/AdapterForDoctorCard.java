package com.example.health965.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.health965.Common.Common;
import com.example.health965.Models.DoctorsWithClinics.Row;
import com.example.health965.Models.MakeReservation.RequestOfReservation;
import com.example.health965.Models.Reservation.Reservation;
import com.example.health965.R;
import com.example.health965.UI.Login_In.Login_Activity;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterForDoctorCard extends RecyclerView.Adapter<AdapterForDoctorCard.ViewHolderForDoctorCard> {
    List<Row> list;
    Context context;
    ProgressDialog dialog;
    public AdapterForDoctorCard(List<Row> list, Context context) {
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
        final Row model = list.get(position);
        //holder.DoctorIamge.setImageResource(model.getIamgeOfDoctoer());
        //holder.ClinicImage.setImageResource(model.getImageOfClinics());
        Picasso.with(context).load(Common.BaseURL+"images/"+model.getImage().getFor()
                +"/"+ Uri.encode(model.getImage().getName())).into(holder.DoctorIamge);
        Picasso.with(context).load(Common.BaseURL+"images/"+model.getClinic().getImage().getFor()
                +"/"+ Uri.encode(model.getClinic().getImage().getName())).into(holder.ClinicImage);
        //holder.Address.setText(model.get);
        holder.DoctorName.setText(model.getName());
        holder.NameOfClinic.setText(model.getClinic().getName());
        holder.DoctorDec.setText(model.getSpecialty());
        //holder.TypeOfWork.setText(model.);

        holder.Reservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(context);
                dialog.show();
                if (Common.CurrentUser != null) {
                    SharedPreferences preferences = context.getSharedPreferences(Common.FileName,context.MODE_PRIVATE);
                    Common.getAPIRequest().makeReservation(preferences.getString(Common.Token, ""), "application/json", new RequestOfReservation(model.getClinicId(),
                            model.getId())).enqueue(new Callback<Reservation>() {
                        @Override
                        public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                            dialog.dismiss();
                            if (response.code() == 201) {
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            } else {
                                try {
                                    Toast.makeText(context, new JSONObject(response.errorBody().string()).getString("message"),
                                            Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Reservation> call, Throwable t) {
                            dialog.dismiss();
                        }
                    });
                }else {
                    dialog.dismiss();
                    context.startActivity(new Intent(context, Login_Activity.class).putExtra("type","getPass"));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderForDoctorCard extends RecyclerView.ViewHolder {
        ImageView DoctorIamge,ClinicImage;
        TextView DoctorName,DoctorDec,NameOfClinic,Address,TypeOfWork;
        RelativeLayout Reservations;
        public ViewHolderForDoctorCard(@NonNull View itemView) {
            super(itemView);
            DoctorIamge = itemView.findViewById(R.id.ImageDoctor);
            ClinicImage = itemView.findViewById(R.id.ImageForClinic);
            DoctorName = itemView.findViewById(R.id.NameOfDoctor);
            DoctorDec = itemView.findViewById(R.id.Dec);
            NameOfClinic = itemView.findViewById(R.id.NameOfClinics);
            Address = itemView.findViewById(R.id.Address);
            TypeOfWork = itemView.findViewById(R.id.TypeOfWork);
            Reservations = itemView.findViewById(R.id.Reservations);
        }
    }
}
