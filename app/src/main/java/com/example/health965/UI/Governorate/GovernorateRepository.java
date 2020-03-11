package com.example.health965.UI.Governorate;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.health965.Adapters.AdapterForArea;
import com.example.health965.Common.Common;
import com.example.health965.Models.Governorate.Governorate;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GovernorateRepository {
    public static GovernorateRepository gRepository;
    public static GovernorateRepository getInstance(){
        if (gRepository == null)
            gRepository = new GovernorateRepository();
        return gRepository;
    }

    public MutableLiveData<Governorate> getDataOfGovernorate(final Context context){
        final MutableLiveData<Governorate> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().getAllGovernorate().enqueue(new Callback<Governorate>() {
            @Override
            public void onResponse(Call<Governorate> call, Response<Governorate> response) {
                if (response.code() == 200) {
                    mutableLiveData.setValue(response.body()); 
                }else {
                    try {
                        Toast.makeText(context,new JSONObject(response.errorBody().string()).getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Governorate> call, Throwable t) {
                Log.i("TTTTTT",t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
