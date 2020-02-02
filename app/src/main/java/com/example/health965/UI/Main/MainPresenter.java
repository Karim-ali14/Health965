package com.example.health965.UI.Main;

import com.example.health965.Models.Model;
import com.example.health965.R;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {
    MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
        view.onInit();
    }

    public List<Model> getModelList(){
        List<Model> list = new ArrayList<>();
        list.add(new Model("عيادة الاسنان","افضل عيادات الاسنان الكويت الشاملة", R.drawable. tooth));
        list.add(new Model("عيادة تجميل","افضل عيادات تجميل الكويت",R.drawable.hair));
        list.add(new Model("عروص مميزة","افضل عروض عيادات الكويت",R.drawable.discount));
        return list;
    }

    public List<String> getStringList(){
        List<String> list = new ArrayList<>();
        list.add("سيتم التواصل معك في خلال ربع ساعة مع مراعاة اوقات العمل");
        list.add("من فضلك قم بالتسجيل اولا لتتمكن من الحجز");
        list.add("سيتم التواصل معك في خلال ربع ساعة مع مراعاة اوقات العمل");
        list.add("سيتم التواصل معك في خلال ربع ساعة مع مراعاة اوقات العمل");
        list.add("سيتم التواصل معك في خلال ربع ساعة مع مراعاة اوقات العمل");

        return list;
    }

    public void OnPutDAta(){
        view.putData(getModelList(),getStringList());
    }
}
