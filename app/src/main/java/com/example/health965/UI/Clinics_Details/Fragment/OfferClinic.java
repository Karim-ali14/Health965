package com.example.health965.UI.Clinics_Details.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.health965.Adapters.AdapterForOfferPage;
import com.example.health965.Models.OfferForClinic.Row;
import com.example.health965.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfferClinic extends Fragment {

    List<Row> rows;

    public OfferClinic(List<Row> rows) {
        this.rows = rows;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_offer_page, container, false);
        RecyclerView recyclerView = inflate.findViewById(R.id.RecyclerOffer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new AdapterForOfferPage(rows, getContext(),true));
        return inflate;
    }
}