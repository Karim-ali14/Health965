package com.example.health965.UI.Clinics;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.health965.Adapters.AdapterForClinics;
import com.example.health965.Adapters.AdapterForImages;
import com.example.health965.Adapters.AdapterForOptions;
import com.example.health965.Common.Common;
import com.example.health965.Models.BannerForCategory.BannerForCategory;
import com.example.health965.Models.Clinics.Clinics;
import com.example.health965.Models.Options.Option;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Clinics_Repository {
    public static Clinics_Repository cRepository;
    public static Clinics_Repository getInstance(){
        if (cRepository == null)
            cRepository = new Clinics_Repository();
        return cRepository;
    }

    public MutableLiveData<Clinics> getAllDataClinics(String id, final Context context){
        final MutableLiveData<Clinics> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().getAllClinics("image",id,"clinicOptions","clinicCertificate").enqueue(new Callback<Clinics>() {
            @Override
            public void onResponse(Call<Clinics> call, Response<Clinics> response) {
                if (response.code() == 200)
                    mutableLiveData.setValue(response.body());
                else {
                    try {
                        Toast.makeText(context, new JSONObject(response.errorBody()
                                .string()).getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Clinics> call, Throwable t) {
                Log.d("TTTTTTT",t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<BannerForCategory> getDataBannerForCategory(String id,
                                                                       final Context context){
        final MutableLiveData<BannerForCategory> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().getBannerForCategory(true, id ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BannerForCategory>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final BannerForCategory bannerForCategory) {
                if (bannerForCategory.getData().getRows().size() != 0){
                    mutableLiveData.setValue(bannerForCategory);
                }
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(context,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<Option> getDataOfOption(final Context context){
        final MutableLiveData<Option> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().getDataOfOption(true).enqueue(new Callback<Option>() {
            @Override
            public void onResponse(Call<Option> call, Response<Option> response) {
                if (response.code()==200){
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
            public void onFailure(Call<Option> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
