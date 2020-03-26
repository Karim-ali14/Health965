package com.example.health965.UI.AreaDetails;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.MutableLiveData;

import com.example.health965.Common.Common;
import com.example.health965.Models.BannerForCategory.BannerForCategory;
import com.example.health965.Models.Clinics.Clinics;
import com.example.health965.Models.Options.Option;
import com.example.health965.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AreaDetailsRepository {
    public static AreaDetailsRepository aDRepository;
    public static AreaDetailsRepository getInstance(){
        if (aDRepository == null)
            aDRepository = new AreaDetailsRepository();
        return aDRepository;
    }

    public MutableLiveData<Clinics> getDataClinicsByGovernorate(final Context context, final ProgressDialog dialog, String C_ID, String G_ID){
        final MutableLiveData<Clinics> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().getClinicsByGovernorate("image","clinicOptions",
                "clinicCertificate",C_ID+"",
                G_ID+"").enqueue(new Callback<Clinics>() {
            @Override
            public void onResponse(Call<Clinics> call, Response<Clinics> response) {
                if (response.code() == 200) {
                    if (response.body().getData().getCount() == 0){
                        dialog.dismiss();
                        Toast.makeText(context, "لا يوجد عيادات في هذه المنطقة", Toast.LENGTH_LONG).show();
                    }
                    else {
                        dialog.dismiss();
                        mutableLiveData.setValue(response.body());
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

    public MutableLiveData<BannerForCategory> getDataBannerForGovernorate(final Context context, final String G_ID, final ProgressDialog dialog){
        final MutableLiveData<BannerForCategory> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().getBannerForGovernorate(true,
                G_ID).subscribeOn(Schedulers.io())
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
                dialog.dismiss();
                final AlertDialog.Builder Adialog = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.error_dialog, null);
                TextView Title = view.findViewById(R.id.Title);
                TextView Message = view.findViewById(R.id.Message);
                Button button = view.findViewById(R.id.Again);
                Adialog.setView(view);
                final AlertDialog dialog1 = Adialog.create();
                dialog1.setCanceledOnTouchOutside(false);
                dialog1.setCancelable(false);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                        dialog.show();
                        aDRepository.getDataBannerForGovernorate(context,G_ID,dialog);
                    }
                });
                if(e instanceof SocketTimeoutException) {
                    Title.setText("تعذر الأتصال بالخادم");
                    Message.setText("خطأ في تحميل البيانات من الخادم اضغط علي زر لأعاده تحميل البيانات");
                    dialog1.show();
                }

                else if (e instanceof UnknownHostException) {
                    Title.setText("لا يوجد اتصال بالانترنت");
                    Message.setText("تأكد من أتصالك بالأنترنت ثم أعد المحاولة");
                    dialog1.show();
                }else {
                    Title.setText("تعذر الأتصال بالخادم");
                    Message.setText("خطأ في تحميل البيانات من الخادم اضغط اعد المحاولة في وقت لاحق");
                    dialog1.show();
                }
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
