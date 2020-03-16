package com.example.health965.UI.ClinicRequests.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health965.Adapters.AdapterForNotifyPage;
import com.example.health965.Common.Common;
import com.example.health965.Models.Notification.Notifications;
import com.example.health965.R;
import com.example.health965.UI.ClinicRequests.ClinicRequestsViewModel;
import com.example.health965.UI.Main.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotifyPageFragment extends Fragment {
    RecyclerView recyclerView;
    TextView Empty;
    ClinicRequestsViewModel viewModel;
    public NotifyPageFragment(ClinicRequestsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        recyclerView = view.findViewById(R.id.Recycler);
        Empty = view.findViewById(R.id.Empty);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getNotification();
        return view;
    }
    private void getNotification(){
        if (Common.CurrentUser != null) {
            viewModel.getNotification(Common.CurrentClinic.getData().getToken().getAccessToken())
                    .observe(getActivity(), new Observer<Notifications>() {
                @Override
                public void onChanged(Notifications notification) {
                    recyclerView.setVisibility(View.VISIBLE);
                    Empty.setVisibility(View.GONE);
                    recyclerView.setAdapter(new AdapterForNotifyPage(notification.getData(),
                            getContext()));
                }
            });
        }else {
            recyclerView.setVisibility(View.GONE);
            Empty.setVisibility(View.VISIBLE);
        }
    }
}
