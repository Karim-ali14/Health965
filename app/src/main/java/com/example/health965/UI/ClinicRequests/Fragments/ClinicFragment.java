package com.example.health965.UI.ClinicRequests.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.health965.R;
import com.example.health965.UI.AboutApp.AboutAppActivity;
import com.example.health965.UI.ClinicInformation.ClinicInformationActivity;
import com.example.health965.UI.PasswordModificationActivity.PasswordModificationActivity;
import com.example.health965.UI.PrivacyPolicy.PrivacyPolicyActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClinicFragment extends Fragment {


    public ClinicFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_clinic, container, false);
        RelativeLayout LayOutPersonalInformation = v.findViewById(R.id.LayOutPersonalInformation);
        LayOutPersonalInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), ClinicInformationActivity.class));
            }
        });
        RelativeLayout LayOutModify = v.findViewById(R.id.LayOutModify);
        LayOutModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), PasswordModificationActivity.class).putExtra("Type","Clinic"));
            }
        });
        RelativeLayout LayOutAbout = v.findViewById(R.id.LayOutAbout);
        LayOutAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), AboutAppActivity.class));
            }
        });
        RelativeLayout LayOutPersonal = v.findViewById(R.id.LayOutPersonal);
        LayOutPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), PrivacyPolicyActivity.class));
            }
        });
        return v;
    }

}
