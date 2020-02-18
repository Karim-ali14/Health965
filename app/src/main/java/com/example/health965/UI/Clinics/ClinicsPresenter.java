package com.example.health965.UI.Clinics;

import com.example.health965.Models.ModelsForCilinics;
import com.example.health965.R;

import java.util.ArrayList;
import java.util.List;

public class ClinicsPresenter {
    IClinics iClinics;

    public ClinicsPresenter(IClinics iClinics) {
        this.iClinics = iClinics;
    }

    void onInit(){
        iClinics.init();
    }

    List<ModelsForCilinics> getdata(){
        List<ModelsForCilinics> list = new ArrayList<>();
        list.add(new ModelsForCilinics(R.drawable.hebaclinic,"عيادة هبة دينتال كلينيك",
                "السالمية , حي الامير صباح الأحمد",
                ", زراعة اسنان , تبييض , تلميع , تنظيف , تقويم بوليش , تركيبات"));
        list.add(new ModelsForCilinics(R.drawable.hebaclinic,"عيادة هبة دينتال كلينيك",
                "السالمية , حي الامير صباح الأحمد",
                ", زراعة اسنان , تبييض , تلميع , تنظيف , تقويم بوليش , تركيبات"));
        return list;
    }
}
