package com.example.health965.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.health965.Adapters.AdapterForNotifyPage;
import com.example.health965.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotifyPage extends Fragment {

   List<String> list;

    public NotifyPage(List<String> list) {
        this.list = list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.Recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new AdapterForNotifyPage(list,getContext()));
        return view;
    }

}
