package com.example.health965.UI.Clinics_Details;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.health965.Adapters.AdapterForClinics;
import com.example.health965.Adapters.AdapterForImages;
import com.example.health965.Common.Common;
import com.example.health965.Models.BannerForCategory.BannerForCategory;
import com.example.health965.Models.OfferForClinic.OfferForClinic;
import com.example.health965.UI.Fragments.Details_fragment;
import com.example.health965.UI.Fragments.OfferClinic;

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

public class Clinics_DetailsRepository {
    public static Clinics_DetailsRepository cDRepository;

    public static Clinics_DetailsRepository getInstance(){
        if (cDRepository == null)
            cDRepository = new Clinics_DetailsRepository();
        return cDRepository;
    }

    public MutableLiveData<BannerForCategory> getDataBannerForClinic(String ClinicId){
        final MutableLiveData<BannerForCategory> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().getBannerForClinic(true,ClinicId
                , "clinic").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BannerForCategory>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final BannerForCategory bannerForCategory) {
                if (bannerForCategory.getData().getRows().size() != 0) {
                    mutableLiveData.setValue(bannerForCategory);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<OfferForClinic> getDataOffersForClinic(String ClinicId ,
                                                              final Context context){
        final MutableLiveData<OfferForClinic> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().getOffersForClinic(ClinicId, true, true).
                enqueue(new Callback<OfferForClinic>() {
                    @Override
                    public void onResponse(Call<OfferForClinic> call, Response<OfferForClinic> response) {
                        if (response.code() == 200) {
                            mutableLiveData.setValue(response.body());
                        }else{
                            try {
                                Toast.makeText(context,new JSONObject(response.errorBody().string())
                                                .getString("message")
                                        , Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<OfferForClinic> call, Throwable t) {

                    }
                });
        return mutableLiveData;
    }

}
