package com.example.health965.UI.Main.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.Adapters.AdapterForClientReservation;
import com.example.health965.Adapters.AdapterForClinicRequests;
import com.example.health965.Common.Common;
import com.example.health965.Models.ClientReservation.ClientReservation;
import com.example.health965.Models.Reservation.Reservation;
import com.example.health965.R;
import com.example.health965.UI.Main.MainActivity;
import com.example.health965.UI.Main.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationFragment extends Fragment {

    RecyclerView recyclerView;
    MainViewModel viewModel;
    public ReservationFragment(MainViewModel viewModel) {
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
        /*viewModel.getAllClientReservation(getContext()).observe(getActivity()
                , new Observer<ClientReservation>() {
                    @Override
                    public void onChanged(ClientReservation clientReservation) {
                        recyclerView.setAdapter(new AdapterForClientReservation(
                                clientReservation.getData().getRows(),
                                getContext(),recyclerView,viewModel));
                    }
                });*/
    }

}


