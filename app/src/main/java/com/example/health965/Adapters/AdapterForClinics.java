package com.example.health965.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.Common.Common;
import com.example.health965.Models.Clinics.Row;
import com.example.health965.Models.MakeReservation.RequestOfReservation;
import com.example.health965.Models.Options.Option;
import com.example.health965.Models.Reservation.Reservation;
import com.example.health965.R;
import com.example.health965.UI.Clinics_Details.Clinics_Details_activity;
import com.example.health965.UI.Login_In.Login_Activity;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterForClinics extends RecyclerView.Adapter<AdapterForClinics.ViewHolderForClinics> {
    List<Row> list;
    Context context;
    ProgressDialog dialog;
    public static Row CLINIC = null;
    Option option;
    SharedPreferences preferences;
    public AdapterForClinics(List<Row> list, Context context, Option option) {
        this.list = list;
        this.context = context;
        this.option = option;
    }

    @NonNull
    @Override
    public ViewHolderForClinics onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderForClinics(LayoutInflater.from(context).inflate(R.layout.model_clinics,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderForClinics holder, final int position) {
        getOptions(holder,position);
        final Row row = list.get(position);
        holder.NameOfClinics.setText(row.getName());
        holder.Address.setText(row.getAddress());
        holder.TypeWork.setText(row.getDescription());
        Picasso.with(context).load(Common.BaseURLForImage+"images/"+ row.getImage().getFor()+"/"+Uri.encode(row.getImage().getName())).into(holder.image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CLINIC  = row;
                context.startActivity(new Intent(context, Clinics_Details_activity.class).putExtra("Clinic_Id",row.getId().toString()));

            }
        });
        holder.Reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(context);
                dialog.show();
                if (Common.CurrentUser != null) {
                    preferences = context.getSharedPreferences(Common.FileName,context.MODE_PRIVATE);
                    Common.getAPIRequest().makeReservation(preferences.getString(Common.Token, ""), "application/json"
                            , new RequestOfReservation(row.getId()))
                            .enqueue(new Callback<Reservation>() {
                                @Override
                                public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                                    Log.i("TTTTTT", response.code() + "");
                                    dialog.dismiss();
                                    if (response.code() == 201) {
                                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                    } else {
                                        try {
                                            Toast.makeText(context, new JSONObject(response.errorBody().string()).getString("message"),
                                                    Toast.LENGTH_LONG).show();
                                        } catch (JSONException e) {
                                            Log.i("TTTTTT", e.getMessage());
                                        } catch (IOException e) {
                                            Log.i("TTTTTT", e.getMessage());
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
                    context.startActivity(new Intent(context, Login_Activity.class).putExtra("type","Login"));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderForClinics extends RecyclerView.ViewHolder{
        ImageView image;
        TextView Address,TypeWork,NameOfClinics;
        CardView cardView;
        RelativeLayout Reservation;
        RecyclerView RecyclerOfOption;
        public ViewHolderForClinics(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ImageForClinic);
            Address = itemView.findViewById(R.id.Address);
            TypeWork = itemView.findViewById(R.id.TypeOfWork);
            NameOfClinics = itemView.findViewById(R.id.NameOfClinics);
            cardView = itemView.findViewById(R.id.Card);
            Reservation = itemView.findViewById(R.id.Reservation);
            RecyclerOfOption = itemView.findViewById(R.id.RecyclerOfOption);
            RecyclerOfOption.setHasFixedSize(true);
            RecyclerOfOption.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        }
    }

    private void getOptions(ViewHolderForClinics holder,int position){
        final List<com.example.health965.Models.Options.Row> listOptions = new ArrayList<>();
        for (int i = 0;i < list.get(position).getClinicOptions().size();i++){
            for (int y = 0;y < option.getData().getRows().size() ; y++){
                if (list.get(position).getClinicOptions().get(i).getOptionId() == option.getData().getRows().get(y).getId()){
                    listOptions.add(option.getData().getRows().get(y));
                }
            }
        }
        holder.RecyclerOfOption.setAdapter(new AdapterForOptions(context,listOptions));
    }
}
