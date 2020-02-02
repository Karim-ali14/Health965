package com.example.health965.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.health965.Adapters.AdapterForOfferPage;
import com.example.health965.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OfferPage extends Fragment {


    public OfferPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_offer_page, container, false);
        RecyclerView recyclerView = inflate.findViewById(R.id.RecyclerOffer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new AdapterForOfferPage(getData(),getContext()));
        return inflate;
    }

    private List<Integer> getData(){
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.adds1);
        list.add(R.drawable.adds2);
        return list;
    }
}
