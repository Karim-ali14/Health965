package com.example.health965.UI.Clinics_Details.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.health965.Adapters.AdapterForCertificates;
import com.example.health965.Adapters.AdapterForClinics;
import com.example.health965.Adapters.AdapterForDoctor;
import com.example.health965.Adapters.AdapterForOptions;
import com.example.health965.Common.Common;
import com.example.health965.Models.Clinics.Row;
import com.example.health965.Models.Doctors.Doctors;
import com.example.health965.Models.Options.Option;
import com.example.health965.Models.Reservation.Reservation;
import com.example.health965.R;
import com.example.health965.UI.Clinics_Details.Clinics_DetailsViewModel;
import com.example.health965.UI.Login_In.Login_Activity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;


/**
 * A simple {@link Fragment} subclass.
 */
public class Details_fragment extends Fragment {
    TextView description,numberOfDoctors,AddressText;
    CompositeDisposable disposable;
    ProgressDialog dialog;
    Row clinic;
    RecyclerView recyclerForDoctors,recyclerForOption;
    public static Button ReservationButton;
    public static Button WhatsAppButton;
    public static Button PhoneButton;
    public static LinearLayout ButtonsLayout;
    CardView CardCertificates,CardOfImages;
    public static int Doctor_Id = 0;
    Clinics_DetailsViewModel viewModel;
    public Details_fragment(Row clinic, Clinics_DetailsViewModel viewModel) {
        this.clinic = clinic;
        this.viewModel = viewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        disposable = new CompositeDisposable();
        dialog = new ProgressDialog(getContext());
        dialog.show();
        View view = inflater.inflate(R.layout.fragment_ditals, container, false);
        CardOfImages = view.findViewById(R.id.CardOfImages);
        CardCertificates = view.findViewById(R.id.CardCertificates);
        description = view.findViewById(R.id.description);
        numberOfDoctors = view.findViewById(R.id.NumberOfDoctors);
        AddressText = view.findViewById(R.id.AddressText);
        ReservationButton = view.findViewById(R.id.ReservationButton);
        WhatsAppButton = view.findViewById(R.id.WhatsAppButton);
        PhoneButton = view.findViewById(R.id.PhoneButton);
        ButtonsLayout = view.findViewById(R.id.ButtonsLayout);
        RecyclerView recyclerView = view.findViewById(R.id.RecyclerOfCertification);
        recyclerForOption = view.findViewById(R.id.RecyclerOfOption);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerForOption.setHasFixedSize(true);
        recyclerForOption.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        if (clinic.getClinicOptions() != null) {
            if (clinic.getClinicOptions().size() != 0 ) {
                CardOfImages.setVisibility(View.VISIBLE);
                getDataOptions();
                Log.i("OOOO",clinic.getClinicOptions().get(0).getId()+"");
            }else
                CardOfImages.setVisibility(View.GONE);
        }
        else
            CardOfImages.setVisibility(View.GONE);
        if (clinic.getClinicCertificate() != null) {
            if (clinic.getClinicCertificate().size() != 0) {
                CardCertificates.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(new AdapterForCertificates(clinic.getClinicCertificate(), getContext()));
            }else
                CardCertificates.setVisibility(View.GONE);
        }
        else
            CardCertificates.setVisibility(View.GONE);
        recyclerForDoctors = view.findViewById(R.id.RecyclerForDoctors);
        recyclerForDoctors.setHasFixedSize(true);
        recyclerForDoctors.setLayoutManager(new LinearLayoutManager(getContext()));
        getListDoctor();
        description.setText(clinic.getDescription());
        AddressText.setText(clinic.getAddress());
        ReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                if (Common.CurrentUser != null) {
                    viewModel.makeReservation(clinic,Doctor_Id,dialog,getContext()).observe(
                            getActivity() , new Observer<Reservation>() {
                        @Override
                        public void onChanged(Reservation reservation) {
                            if (reservation.getSuccess()){
                                if (AdapterForClinics.CLINIC.getContacts() != null) {
                                    Details_fragment.ReservationButton.setVisibility(View.GONE);
                                    Details_fragment.ButtonsLayout.setVisibility(View.VISIBLE);
                                    Details_fragment.PhoneButton.setText(AdapterForClinics.CLINIC.getContacts().getPhoneNumber1());
                                    Details_fragment.WhatsAppButton.setText(AdapterForClinics.CLINIC.getContacts().getWhatsApp());

                                    Details_fragment.WhatsAppButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            String url = "https://api.whatsapp.com/send?phone="+Details_fragment.WhatsAppButton.getText().toString();
                                            Intent i = new Intent(Intent.ACTION_VIEW);
                                            i.setData(Uri.parse(url));
                                            startActivity(i);
                                        }
                                    });

                                    Details_fragment.PhoneButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            String Phone = Details_fragment.PhoneButton.getText().toString();
                                            Intent intent = new Intent(Intent.ACTION_DIAL);
                                            intent.setData(Uri.parse("tel:" +Phone));
                                            startActivity(intent);
                                        }
                                    });
                                }else {
                                    Details_fragment.ReservationButton.setVisibility(View.GONE);
                                    Details_fragment.ButtonsLayout.setVisibility(View.VISIBLE);
                                    Details_fragment.PhoneButton.setText("-----");
                                    Details_fragment.WhatsAppButton.setText("-----");
                                }
                            }
                        }
                    });
                }else {
                    dialog.dismiss();
                    startActivity(new Intent(getActivity(), Login_Activity.class).putExtra("type","Login"));
                }
            }
        });
        return view;
    }

    private void getListDoctor(){
        viewModel.getListDoctor(getContext(),clinic,dialog).observe(getActivity()
                , new Observer<Doctors>() {
            @Override
            public void onChanged(Doctors doctors) {
                numberOfDoctors.setText("("+doctors.getData().getRows().size()+")");
                recyclerForDoctors.setAdapter(new AdapterForDoctor(doctors.getData().getRows(), getContext()));
            }
        });
    }

    private void getDataOptions(){
        final List<com.example.health965.Models.Options.Row> list = new ArrayList<>();
        viewModel.getDataOptions(getContext()).observe(getActivity(), new Observer<Option>() {
            @Override
            public void onChanged(Option option) {
                for (int i = 0;i < clinic.getClinicOptions().size();i++){
                    for (int y = 0;y < option.getData().getRows().size() ;y++){
                        if (clinic.getClinicOptions().get(i).getOptionId() == option.getData().getRows().get(y).getId()){
                            list.add(option.getData().getRows().get(y));
                        }
                    }
                }
                recyclerForOption.setAdapter(new AdapterForOptions(getActivity(),list));
            }
        });
    }
}