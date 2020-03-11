package com.example.health965.UI.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.health965.Adapters.AdapterForNotifyPage;
import com.example.health965.Common.Common;
import com.example.health965.Models.Notification.Notifications;
import com.example.health965.R;
import com.example.health965.UI.Main.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotifyPage extends Fragment {
    RecyclerView recyclerView;
    TextView Empty;
    MainViewModel viewModel;
    public NotifyPage(MainViewModel viewModel) {
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
//            Common.getAPIRequest().getNotification(Common.CurrentUser.getData().getToken().getAccessToken())
//                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<List<Notification>>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//
//                        }
//
//                        @Override
//                        public void onNext(List<Notification> notifications) {
//                            if (notifications.size() != 0) {
//                                recyclerView.setVisibility(View.VISIBLE);
//                                Empty.setVisibility(View.GONE);
//                                recyclerView.setAdapter(new AdapterForNotifyPage(notifications,
//                                        getContext()));
//                            }
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                        }
//                    });v
            viewModel.getNotification().observe(getActivity(), new Observer<Notifications>() {
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
