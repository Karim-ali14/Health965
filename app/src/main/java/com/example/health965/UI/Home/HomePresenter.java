package com.example.health965.UI.Home;

import com.example.health965.R;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter {
    ILogin iLogin;

    public HomePresenter(ILogin iLogin) {
        this.iLogin = iLogin;
    }


    void OnAddPoints(int position,int listSize){
        iLogin.addPoints(position,listSize);
    }

    void onInit(){
        iLogin.Init();
    }
}