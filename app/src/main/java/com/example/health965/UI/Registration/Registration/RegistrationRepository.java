package com.example.health965.UI.Registration.Registration;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.health965.Common.Common;
import com.example.health965.Models.Regestration.DataUserRegistration;
import com.example.health965.Models.Regestration.Registration;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationRepository {

    public static RegistrationRepository nARepository;
    public static RegistrationRepository getInstance(){
        if (nARepository == null)
            nARepository = new RegistrationRepository();
        return nARepository;
    }

    public MutableLiveData<Registration> onRegistration(DataUserRegistration dataUser, final ProgressDialog dialog, final Context context){
        final MutableLiveData<Registration> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().onRegistration(dataUser)
                .enqueue(new Callback<Registration>() {
                    @Override
                    public void onResponse(Call<Registration> call, Response<Registration> response) {
                        dialog.dismiss();
                        if (response.code() == 201){
                            mutableLiveData.setValue(response.body());
                        } else {
                            Log.i("TTTTTT",response.code()+"");
                            try {
                                JSONObject object = new JSONObject(response.errorBody().string());
                                Toast.makeText(context, object.getString("message"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Registration> call, Throwable t) {
                        dialog.dismiss();
                        Log.i(RegistrationActivity.TAG,t.getMessage());
                    }
                });
        return mutableLiveData;
    }
}
