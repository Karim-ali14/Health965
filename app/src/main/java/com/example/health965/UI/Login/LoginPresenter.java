package com.example.health965.UI.Login;

import com.example.health965.R;

import java.util.ArrayList;
import java.util.List;

public class LoginPresenter {
    ILogin iLogin;

    public LoginPresenter(ILogin iLogin) {
        this.iLogin = iLogin;
    }

    List<Integer> getImageList(){
        List<Integer> listImage = new ArrayList<>();
        listImage.add(R.drawable.p1);
        listImage.add(R.drawable.p2);
        listImage.add(R.drawable.p1);
        return listImage;
    }

    void OnAddPoints(int position){
        iLogin.addPoints(position);
    }

    void onInit(){
        iLogin.Init();
    }
}