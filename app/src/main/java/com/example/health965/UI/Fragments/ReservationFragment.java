package com.example.health965.UI.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.health965.Adapters.AdapterForClinicRequests;
import com.example.health965.Common.Common;
import com.example.health965.Models.Governorate.Governorate;
import com.example.health965.Models.ModelOfRequests;
import com.example.health965.Models.Reservation.Reservation;
import com.example.health965.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationFragment extends Fragment {

    RecyclerView recyclerView;

    public ReservationFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservation, container, false);
        recyclerView = view.findViewById(R.id.Recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getData();
        return view;
    }

    private void getData(){
        Common.getAPIRequest().getReservation(Common.CurrentClinic.getData().getToken().getAccessToken(),
                Common.CurrentClinic.getData().getClinic().getId()+"").enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                if (response.code() == 200)
                    recyclerView.setAdapter(new AdapterForClinicRequests(response.body().getData().getRows(),getContext()));
                Log.i("TTTTTT",""+response.code());
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {

            }
        });
    }

}


