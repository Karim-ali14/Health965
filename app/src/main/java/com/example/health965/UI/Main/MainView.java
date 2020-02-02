package com.example.health965.UI.Main;

import com.example.health965.Models.Model;

import java.util.List;

public interface MainView {
    void onInit();
    void putData(List<Model> ModelList,List<String> StringList);
}
