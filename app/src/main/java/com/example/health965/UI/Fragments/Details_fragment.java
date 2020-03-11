package com.example.health965.UI.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health965.Adapters.AdapterForCertificates;
import com.example.health965.Adapters.AdapterForDoctor;
import com.example.health965.Adapters.AdapterForOptions;
import com.example.health965.Common.Common;
import com.example.health965.Models.Clinics.Row;
import com.example.health965.Models.Doctors.Doctors;
import com.example.health965.Models.MakeReservation.RequestOfReservation;
import com.example.health965.Models.Options.Option;
import com.example.health965.Models.Reservation.Reservation;
import com.example.health965.R;
import com.example.health965.UI.Login_Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Details_fragment extends Fragment {
    TextView description,numberOfDoctors;
    CompositeDisposable disposable;
    ProgressDialog dialog;
    Row clinic;
    RecyclerView recyclerForDoctors,recyclerForOption;
    Button ReservationButton;
    CardView CardCertificates,CardOfImages;
    public static int Doctor_Id = 0;
    public Details_fragment(Row clinic) {
        this.clinic = clinic;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        disposable = new CompositeDisposable();
        dialog = new ProgressDialog(getContext());
        dialog.show();
        View view = inflater.inflate(R.layout.fragment_ditals, container, false);
        CardOfImages = view.findViewById(R.id.CardOfImages);
        CardCertificates = view.findViewById(R.id.CardCertificates);
        description = view.findViewById(R.id.description);
        numberOfDoctors = view.findViewById(R.id.NumberOfDoctors);
        ReservationButton = view.findViewById(R.id.ReservationButton);
        RecyclerView recyclerView = view.findViewById(R.id.RecyclerOfCertification);
        recyclerForOption = view.findViewById(R.id.RecyclerOfOption);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerForOption.setHasFixedSize(true);
        recyclerForOption.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        if (clinic.getClinicOptions() != null) {
            if (clinic.getClinicOptions().size() != 0 ) {
                CardOfImages.setVisibility(View.VISIBLE);
                getDataOptions();
                Log.i("OOOO",clinic.getClinicOptions().get(0).getId()+"");
            }else
                CardOfImages.setVisibility(View.GONE);
        }
        else
            CardOfImages.setVisibility(View.GONE);
        if (clinic.getClinicCertificate() != null) {
            if (clinic.getClinicCertificate().size() != 0) {
                CardCertificates.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(new AdapterForCertificates(clinic.getClinicCertificate(), getContext()));
            }else
                CardCertificates.setVisibility(View.GONE);
        }
        else
            CardCertificates.setVisibility(View.GONE);
        recyclerForDoctors = view.findViewById(R.id.RecyclerForDoctors);
        recyclerForDoctors.setHasFixedSize(true);
        recyclerForDoctors.setLayoutManager(new LinearLayoutManager(getContext()));
        getListDoctor();
        description.setText(clinic.getDescription());
        ReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                if (Common.CurrentUser != null) {
                    if (Doctor_Id == 0) {
                        Common.getAPIRequest().makeReservation(Common.CurrentUser.getData().getToken().getAccessToken(), "application/json"
                                , new RequestOfReservation(clinic.getId()))
                                .enqueue(new Callback<Reservation>() {
                                    @Override
                                    public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                                        dialog.dismiss();
                                        if (response.code() == 201) {
                                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                                        } else {
                                            try {
                                                Toast.makeText(getContext(), new JSONObject(response.errorBody().string()).getString("message"),
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
                    } else {
                        Common.getAPIRequest().makeReservation(Common.CurrentUser.getData().getToken().getAccessToken(), "application/json"
                                , new RequestOfReservation(clinic.getId(),
                                        Doctor_Id)).enqueue(new Callback<Reservation>() {
                            @Override
                            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                                dialog.dismiss();
                                if (response.code() == 201) {
                                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                                } else {
                                    try {
                                        Toast.makeText(getContext(), new JSONObject(response.errorBody().string()).getString("message"),
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
                    }
                }else {
                    dialog.dismiss();
                    startActivity(new Intent(getActivity(), Login_Activity.class).putExtra("type","getPass"));
                }
            }
        });
        return view;
    }
    private void getListDoctor(){
        Common.getAPIRequest().getDoctors( clinic.getId()+"","image").enqueue(new Callback<Doctors>() {
            @Override
            public void onResponse(Call<Doctors> call, Response<Doctors> response) {
                if (response.code() == 200) {
                    dialog.dismiss();
                    numberOfDoctors.setText("("+response.body().getData().getRows().size()+")");
                    recyclerForDoctors.setAdapter(new AdapterForDoctor(response.body().getData().getRows(), getContext()));
                }else {
                    Log.i("TTTTTT",response.code()+"");
                }
            }

            @Override
            public void onFailure(Call<Doctors> call, Throwable t) {

            }
        });
    }


    private void getDataOptions(){
        final List<com.example.health965.Models.Options.Row> list = new ArrayList<>();
        Common.getAPIRequest().getDataOfOption(true).enqueue(new Callback<Option>() {
            @Override
            public void onResponse(Call<Option> call, Response<Option> response) {
                if (response.code()==200){
                    for (int i = 0;i < clinic.getClinicOptions().size();i++){
                        for (int y = 0;y < response.body().getData().getRows().size() ;y++){
                            if (clinic.getClinicOptions().get(i).getOptionId() == response.body().getData().getRows().get(y).getId()){
                                list.add(response.body().getData().getRows().get(y));
                            }
                        }
                    }
                    recyclerForOption.setAdapter(new AdapterForOptions(getActivity(),list));
                }else {
                    try {
                        Toast.makeText(getActivity(),new JSONObject(response.errorBody().string()).getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Option> call, Throwable t) {

            }
        });
    }
}