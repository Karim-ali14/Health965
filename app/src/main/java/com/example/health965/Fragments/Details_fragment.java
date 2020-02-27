package com.example.health965.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

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
import com.example.health965.Common.Common;
import com.example.health965.Models.Doctors.Doctors;
import com.example.health965.Models.MakeReservation.RequestOfReservation;
import com.example.health965.Models.ModelDoctor;
import com.example.health965.Models.ModelOfCertificates;
import com.example.health965.Models.Reservation.Reservation;
import com.example.health965.Models.getClincs.Clinics;
import com.example.health965.Models.getClincs.Row;
import com.example.health965.R;
import com.example.health965.UI.Login_Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Details_fragment extends Fragment {
    TextView description;
    CompositeDisposable disposable;
    ProgressDialog dialog;
    Row clinic;
    RecyclerView RecyclerForDoctors;
    Button ReservationButton;
    public static int Doctor_Id = 0;
    public Details_fragment(Row clinic) {
        this.clinic = clinic;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        disposable = new CompositeDisposable();
        dialog = new ProgressDialog(getContext());
        dialog.show();
        View view = inflater.inflate(R.layout.fragment_ditals, container, false);
        description = view.findViewById(R.id.description);
        ReservationButton = view.findViewById(R.id.ReservationButton);
        RecyclerView recyclerView = view.findViewById(R.id.RecyclerOfCertification);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new AdapterForCertificates(getList(),getContext()));
        RecyclerForDoctors = view.findViewById(R.id.RecyclerForDoctors);
        RecyclerForDoctors.setHasFixedSize(true);
        RecyclerForDoctors.setLayoutManager(new LinearLayoutManager(getContext()));
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
                    startActivity(new Intent(getActivity(), Login_Activity.class));
                }
            }
        });
        return view;
    }

    private List<ModelOfCertificates> getList(){
        List<ModelOfCertificates> list = new ArrayList<>();
        list.add(new ModelOfCertificates("الجودة الصحية العالمية","2011"));
        list.add(new ModelOfCertificates(" شهادة الأيزو العليا","2008"));
        list.add(new ModelOfCertificates("زمالة العيادات","2005"));
        return list;
    }

    private void getListDoctor(){
        Common.getAPIRequest().getDoctors( clinic.getId()+"","image").enqueue(new Callback<Doctors>() {
            @Override
            public void onResponse(Call<Doctors> call, Response<Doctors> response) {
                if (response.code() == 200) {
                    dialog.dismiss();
                    RecyclerForDoctors.setAdapter(new AdapterForDoctor(response.body().getData().getRows(), getContext()));
                }else {
                    Log.i("TTTTTT",response.code()+"");
                }
            }

            @Override
            public void onFailure(Call<Doctors> call, Throwable t) {

            }
        });
    }
}