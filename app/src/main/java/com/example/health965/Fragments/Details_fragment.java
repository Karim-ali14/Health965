package com.example.health965.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.health965.Adapters.AdapterForCertificates;
import com.example.health965.Adapters.AdapterForDoctor;
import com.example.health965.Models.ModelDoctor;
import com.example.health965.Models.ModelOfCertificates;
import com.example.health965.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Details_fragment extends Fragment {


    public Details_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ditals, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.RecyclerOfCertification);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new AdapterForCertificates(getList(),getContext()));
        RecyclerView RecyclerForDoctors = view.findViewById(R.id.RecyclerForDoctors);
        RecyclerForDoctors.setHasFixedSize(true);
        RecyclerForDoctors.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerForDoctors.setAdapter(new AdapterForDoctor(getListDoctor(),getContext()));
        return view;
    }
    private List<ModelOfCertificates> getList(){
        List<ModelOfCertificates> list = new ArrayList<>();
        list.add(new ModelOfCertificates("الجودة الصحية العالمية","2011"));
        list.add(new ModelOfCertificates(" شهادة الأيزو العليا","2008"));
        list.add(new ModelOfCertificates("زمالة العيادات","2005"));
        return list;
    }
    private List<ModelDoctor> getListDoctor(){
        List<ModelDoctor> list = new ArrayList<>();
        list.add(new ModelDoctor(R.drawable.doctorw,"د / أحمد محمد السالم","ماجستير طب وجراحة الفم والأسنان",false));
        list.add(new ModelDoctor(R.drawable.doctorw,"د / أحمد محمد السالم","ماجستير طب وجراحة الفم والأسنان",false));
        list.add(new ModelDoctor(R.drawable.doctorw,"د / أحمد محمد السالم","ماجستير طب وجراحة الفم والأسنان",false));
        list.add(new ModelDoctor(R.drawable.doctorw,"د / أحمد محمد السالم","ماجستير طب وجراحة الفم والأسنان",false));
        list.add(new ModelDoctor(R.drawable.doctorw,"د / أحمد محمد السالم","ماجستير طب وجراحة الفم والأسنان",false));

        return list;
    }
}
