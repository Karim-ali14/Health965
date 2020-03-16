package com.example.health965.UI.Main.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
import com.example.health965.UI.Main.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class OfferPageFragment extends Fragment {

    RecyclerView recyclerView;
    ProgressDialog dialog;
    MainViewModel viewModel;
    public OfferPageFragment(MainViewModel viewModel) {
        this.viewModel = viewModel;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_offer_page, container, false);
        init(inflate);
        return inflate;
    }

    private void init(View inflate){
        dialog = new ProgressDialog(getActivity());
        dialog.show();
        recyclerView = inflate.findViewById(R.id.RecyclerOffer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getData();
    }

    private void getData(){
        viewModel.getOfferClinic(dialog).observe(getActivity(), new Observer<OfferForClinic>() {
            @Override
            public void onChanged(OfferForClinic offerForClinic) {
                recyclerView.setAdapter(new AdapterForOfferPage(offerForClinic.getData().getRows(),getContext(),false));
            }
        });
    }
}
