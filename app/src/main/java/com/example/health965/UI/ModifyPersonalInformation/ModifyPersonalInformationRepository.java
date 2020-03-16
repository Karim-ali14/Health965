package com.example.health965.UI.ModifyPersonalInformation;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.health965.Common.Common;
import com.example.health965.Models.UpdateClientInfo.RequestUpdateClientInfo;
import com.example.health965.Models.UpdateClientInfo.ResponseUpdateClientInfo.ResponseUpdateClientInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyPersonalInformationRepository {
    public static ModifyPersonalInformationRepository mPIRepository;
    public static ModifyPersonalInformationRepository getInstance(){
        if (mPIRepository == null)
            mPIRepository = new ModifyPersonalInformationRepository();
        return mPIRepository;
    }

    public MutableLiveData<ResponseUpdateClientInfo> onUpdateClientInfo(
            RequestUpdateClientInfo model, final Context context){
        final MutableLiveData<ResponseUpdateClientInfo> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().onUpdateClientInfo(Common.CurrentUser.getData().getToken().getAccessToken(),"application/json",
                Common.CurrentUser.getData().getUser().getId().toString(),model).enqueue(new Callback<ResponseUpdateClientInfo>() {
            @Override
            public void onResponse(Call<ResponseUpdateClientInfo> call, Response<ResponseUpdateClientInfo> response) {
                if (response.code() == 200){
                    mutableLiveData.setValue(response.body());
                }else {
                    try {
                        Toast.makeText(context,new JSONObject(response.errorBody().string())
                                .getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateClientInfo> call, Throwable t) {
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return mutableLiveData;
    }
}
