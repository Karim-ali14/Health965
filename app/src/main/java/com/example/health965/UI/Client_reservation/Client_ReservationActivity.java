package com.example.health965.UI.Client_reservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.health965.Adapters.AdapterForClientReservation;
import com.example.health965.Common.Common;
import com.example.health965.Models.ClientReservation.ClientReservation;
import com.example.health965.R;

public class Client_ReservationActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Client_ReservationViewModels viewModel;
    ProgressDialog dialog;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client__reservation);
        preferences = getSharedPreferences(Common.FileName,MODE_PRIVATE);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        recyclerView = findViewById(R.id.Recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel = ViewModelProviders.of(this).get(Client_ReservationViewModels.class);
        dialog = new ProgressDialog(this);
        getData();
    }

    private void getData() {
        dialog.show();
        viewModel.getAllClientReservation(this,dialog,preferences).observe(this
                , new Observer<ClientReservation>() {
                    @Override
                    public void onChanged(ClientReservation clientReservation) {
                        if (clientReservation.getData().getRows().size() != 0) {
                            dialog.dismiss();
                            recyclerView.setAdapter(new AdapterForClientReservation(
                                    clientReservation.getData().getRows(),
                                    Client_ReservationActivity.this, recyclerView, viewModel));
                        }else {
                            Toast.makeText(Client_ReservationActivity.this, "لا يوجد اي حجوزات", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void Back(View view) {
        finish();
    }
}
