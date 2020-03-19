package com.example.health965.UI.Clinics_Details;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.health965.Adapters.AdapterForDoctor;
import com.example.health965.Adapters.AdapterForOptions;
import com.example.health965.Common.Common;
import com.example.health965.Models.BannerForCategory.BannerForCategory;
import com.example.health965.Models.Clinics.Clinics;
import com.example.health965.Models.Clinics.Row;
import com.example.health965.Models.Doctors.Doctors;
import com.example.health965.Models.MakeReservation.RequestOfReservation;
import com.example.health965.Models.OfferForClinic.OfferForClinic;
import com.example.health965.Models.Options.Option;
import com.example.health965.Models.Reservation.Reservation;

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

    // TODO All of Details Fragment

        //TODO get list of Doctors
        public MutableLiveData<Doctors> getListDoctor(final Context context,
                                                      Row clinic, final ProgressDialog dialog){
            final MutableLiveData<Doctors> mutableLiveData = new MutableLiveData<>();
            Common.getAPIRequest().getDoctors( clinic.getId()+"","image").enqueue(new Callback<Doctors>() {
                @Override
                public void onResponse(Call<Doctors> call, Response<Doctors> response) {
                    dialog.dismiss();
                    if (response.code() == 200) {
                        mutableLiveData.setValue(response.body());
                    }else {
                        Log.i("TTTTTT",response.code()+"");
                    }
                }

                @Override
                public void onFailure(Call<Doctors> call, Throwable t) {

                }
            });
            return mutableLiveData;
        }
        //TODO get list of Options
        public MutableLiveData<Option> getDataOptions(final Context context){
            final MutableLiveData<Option> mutableLiveData = new MutableLiveData<>();
            Common.getAPIRequest().getDataOfOption(true).enqueue(new Callback<Option>() {
                @Override
                public void onResponse(Call<Option> call, Response<Option> response) {
                    if (response.code()==200){
                        mutableLiveData.setValue(response.body());
                    }else {
                        try {
                            Toast.makeText(context,new JSONObject(
                                    response.errorBody().string()).getString("message"),
                                    Toast.LENGTH_SHORT).show();
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

        //TODO Make Reservation
        public MutableLiveData<Reservation> makeReservation(Row clinic, int Doctor_Id,
                                                            final ProgressDialog dialog,
                                                            final Context context){
            final MutableLiveData<Reservation> mutableLiveData = new MutableLiveData<>();
            if (Doctor_Id == 0) {
                Common.getAPIRequest().makeReservation(Common.CurrentUser.getData().getToken().getAccessToken(), "application/json"
                        , new RequestOfReservation(clinic.getId()))
                        .enqueue(new Callback<Reservation>() {
                            @Override
                            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                                dialog.dismiss();
                                if (response.code() == 201) {
                                    mutableLiveData.setValue(response.body());
                                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                } else {
                                    try {
                                        Toast.makeText(context, new JSONObject(response.errorBody().string()).getString("message"),
                                                Toast.LENGTH_LONG).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<Reservation> call, Throwable t) {
                                dialog.dismiss();
                            }
                        });
            }
            else {
                Common.getAPIRequest().makeReservation(Common.CurrentUser.getData().getToken().getAccessToken(), "application/json"
                        , new RequestOfReservation(clinic.getId(),
                                Doctor_Id)).enqueue(new Callback<Reservation>() {
                    @Override
                    public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                        dialog.dismiss();
                        if (response.code() == 201) {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            mutableLiveData.setValue(response.body());
                        } else {
                            try {
                                Toast.makeText(context, new JSONObject(response.errorBody().string()).getString("message"),
                                        Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Reservation> call, Throwable t) {
                        dialog.dismiss();
                    }
                });
            }
            return mutableLiveData;
        }
        // TODO Get data of Clinic
        public MutableLiveData<Clinics> getDataOfClinic(String Clinic_Id) {
            final MutableLiveData<Clinics> mutableLiveData = new MutableLiveData<>();
            Common.getAPIRequest().getClinicInfo("image", Clinic_Id,
                    "clinicOptions",
                    "clinicCertificate").subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Clinics>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Clinics clinics) {
                    mutableLiveData.setValue(clinics);
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
}
