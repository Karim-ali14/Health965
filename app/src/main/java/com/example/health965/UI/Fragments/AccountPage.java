package com.example.health965.UI.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.health965.Common.Common;
import com.example.health965.UI.AboutAppActivity;
import com.example.health965.UI.Login_Activity;
import com.example.health965.UI.PasswordModificationActivity;
import com.example.health965.UI.PersonalInformationActivity;
import com.example.health965.UI.PrivacyPolicyActivity;
import com.example.health965.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountPage extends Fragment {
    RelativeLayout LayOutPersonalInformation,LayOutModify,LayOutAbout,LayOutPersonal;
    Context context;

    public AccountPage(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_page, container, false);
        LayOutPersonalInformation = view.findViewById(R.id.LayOutPersonalInformation);
        LayOutModify = view.findViewById(R.id.LayOutModify);
        LayOutAbout = view.findViewById(R.id.LayOutAbout);
        LayOutPersonal = view.findViewById(R.id.LayOutPersonal);
        LayOutPersonalInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Common.CurrentUser!=null)
                    context.startActivity(new Intent(context, PersonalInformationActivity.class));
                else
                    context.startActivity(new Intent(context, Login_Activity.class).putExtra("type","getPass"));
            }
        });
        LayOutModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Common.CurrentUser!=null)
                    context.startActivity(new Intent(context, PasswordModificationActivity.class).putExtra("Type","User"));
                else
                    context.startActivity(new Intent(context, Login_Activity.class).putExtra("type","getPass"));
            }
        });
        LayOutAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AboutAppActivity.class));
            }
        });
        LayOutPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, PrivacyPolicyActivity.class));
            }
        });
        return view;
    }

}
