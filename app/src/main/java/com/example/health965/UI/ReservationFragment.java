package com.example.health965.UI;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.health965.Adapters.AdapterForClinicRequests;
import com.example.health965.Models.ModelOfRequests;
import com.example.health965.R;

import java.util.ArrayList;
import java.util.List;


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
        recyclerView.setAdapter(new AdapterForClinicRequests(getData(),getContext()));
        return view;
    }

    private List<ModelOfRequests> getData(){
        List<ModelOfRequests> list = new ArrayList<>();
        list.add(new ModelOfRequests("محمود محمد بيومي", "info@gmail.com","+96512224464",
                " د/ أحمد محمد السالم" ,"28/1/2020 ","06:30 PM" ,"في الإنتظار"));
        list.add(new ModelOfRequests("محمود محمد بيومي", "info@gmail.com","+96512224464",
                " د/ أحمد محمد السالم" ,"28/1/2020 ","06:30 PM" ,"في الإنتظار"));
        list.add(new ModelOfRequests("محمود محمد بيومي", "info@gmail.com","+96512224464",
                " د/ أحمد محمد السالم" ,"28/1/2020 ","06:30 PM" ,"في الإنتظار"));
        return list;
    }

}


