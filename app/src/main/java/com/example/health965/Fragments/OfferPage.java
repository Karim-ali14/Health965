package com.example.health965.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.health965.Adapters.AdapterForClinics;
import com.example.health965.Adapters.AdapterForOfferPage;
import com.example.health965.Common.Common;
import com.example.health965.Models.BannerForCategory.Row;
import com.example.health965.Models.OfferForClinic.OfferForClinic;
import com.example.health965.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class OfferPage extends Fragment {

    RecyclerView recyclerView;
    ProgressDialog dialog;
    public OfferPage() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dialog = new ProgressDialog(getActivity());
        dialog.show();
        View inflate = inflater.inflate(R.layout.fragment_offer_page, container, false);
        recyclerView = inflate.findViewById(R.id.RecyclerOffer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getData();
        return inflate;
    }

    private void getData(){
        Common.getAPIRequest().getAllOffersF(true,true).
                enqueue(new Callback<OfferForClinic>() {
                    @Override
                    public void onResponse(Call<OfferForClinic> call, Response<OfferForClinic> response) {
                        dialog.dismiss();
                        if (response.code() == 200){
                            recyclerView.setAdapter(new AdapterForOfferPage(response.body().getData().getRows(),getContext(),false));
                        }
                    }

                    @Override
                    public void onFailure(Call<OfferForClinic> call, Throwable t) {

                    }
                });
    }
}
