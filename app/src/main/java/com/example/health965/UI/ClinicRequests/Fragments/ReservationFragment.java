package com.example.health965.UI.ClinicRequests.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.health965.Adapters.AdapterForClinicRequests;
import com.example.health965.Common.Common;
import com.example.health965.Models.Reservation.Reservation;
import com.example.health965.R;
import com.example.health965.UI.ClinicRequests.ClinicRequestsViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationFragment extends Fragment {

    RecyclerView recyclerView;
    ClinicRequestsViewModel viewModel;
    public ReservationFragment(ClinicRequestsViewModel viewModel) {
        this.viewModel = viewModel;
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
        SharedPreferences preferences =
                getActivity().getSharedPreferences(Common.FileName, getActivity().MODE_PRIVATE);

        viewModel.getDataReservation(getContext(),preferences.getString(Common.Token,""),
                preferences.getString(Common.ID,"")).observe(getActivity()
                , new Observer<Reservation>() {
            @Override
            public void onChanged(Reservation reservation) {
                recyclerView.setAdapter(new AdapterForClinicRequests(
                        reservation.getData().getRows(),getContext(),recyclerView,viewModel));
            }
        });
    }
}


