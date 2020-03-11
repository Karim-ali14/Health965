package com.example.health965.UI.AboutApp;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.health965.Common.Common;
import com.example.health965.Models.Setting.Setting;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutRepository {
    public static AboutRepository aRepository;

    public static AboutRepository getInstance(){
        if (aRepository == null)
            aRepository = new AboutRepository();
        return aRepository;
    }

    public MutableLiveData<Setting> getDataOfSettings(final Context context){
        final MutableLiveData<Setting> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().getSetting().enqueue(new Callback<Setting>() {
            @Override
            public void onResponse(Call<Setting> call, Response<Setting> response) {
                if (response.code() == 200){
                    mutableLiveData.setValue(response.body());
                }else{
                    try {
                        Toast.makeText(context, new JSONObject(response.errorBody().string()).getString("message")
                                , Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Setting> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
