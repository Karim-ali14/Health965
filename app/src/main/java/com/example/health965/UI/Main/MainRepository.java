package com.example.health965.UI.Main;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.MutableLiveData;

import com.example.health965.Common.Common;
import com.example.health965.Models.BannerForCategory.BannerForCategory;
import com.example.health965.Models.Category.Category;
import com.example.health965.Models.ClientReservation.ClientReservation;
import com.example.health965.Models.Notification.Notifications;
import com.example.health965.Models.OfferForClinic.OfferForClinic;
import com.example.health965.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    public static MainRepository mRepository;

    public static MainRepository getInstance(){
        if (mRepository  == null)
            mRepository = new MainRepository();
        return mRepository;
    }

    // TODO get Data of Category for Main Fragment
    public MutableLiveData<Category> getDataOfCategories(final Context context, final ProgressDialog dialog){
        final MutableLiveData<Category> categoryData = new MutableLiveData<>();
        Observable<Category> allCategory = Common.getAPIRequest().getAllCategory(true);
        allCategory.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Category>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final Category category) {
                        categoryData.setValue(category);

                    }

                    @Override
                    public void onError(Throwable e) {
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
                                mRepository.getDataOfCategories(context,dialog);
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
        return categoryData;
    }
    //TODO get Data Of Banners for Main Fragment
    public MutableLiveData<BannerForCategory> getDataOfBanners(final Context context, final ProgressDialog dialog) {
        final MutableLiveData<BannerForCategory> mutableLiveData = new MutableLiveData<>();
        Observable<BannerForCategory> bannerForCategoryObservable = Common.getAPIRequest().getBannerForCategories(true, "home");
        bannerForCategoryObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BannerForCategory>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final BannerForCategory bannerForCategory) {
                mutableLiveData.setValue(bannerForCategory);
            }

            @Override
            public void onError(Throwable e) {
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
                        mRepository.getDataOfBanners(context,dialog);
                    }
                });
                if (e instanceof SocketTimeoutException) {
                    Title.setText("تعذر الأتصال بالخادم");
                    Message.setText("خطأ في تحميل البيانات من الخادم اضغط علي زر لأعاده تحميل البيانات");
                    dialog1.show();
                } else if (e instanceof UnknownHostException) {
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

    //TODO get Data Of Notifications for Notify Fragment
    public MutableLiveData<Notifications> getDataOfNotification(){
        final MutableLiveData<Notifications> notificationData = new MutableLiveData<>();
        Common.getAPIRequest().getNotification(Common.CurrentUser.getData().getToken().getAccessToken())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Notifications>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Notifications notification) {
                        notificationData.setValue(notification);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return notificationData;
    }

    //TODO get Data Of Offers for Offer Fragment
    public MutableLiveData<OfferForClinic> getDataOffer(final ProgressDialog dialog){
        final MutableLiveData<OfferForClinic> offerForClinicData = new MutableLiveData<>();
        Common.getAPIRequest().getAllOffersF(true,true).
                enqueue(new Callback<OfferForClinic>() {
                    @Override
                    public void onResponse(Call<OfferForClinic> call, Response<OfferForClinic> response) {
                        dialog.dismiss();
                        if (response.code() == 200){
                            offerForClinicData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<OfferForClinic> call, Throwable t) {

                    }
                });
        return offerForClinicData;
    }

    public MutableLiveData<OfferForClinic> getDataOfferByCategory(final ProgressDialog dialog,
                                                                  String category_id){
        final MutableLiveData<OfferForClinic> offerForClinicData = new MutableLiveData<>();
        Common.getAPIRequest().getAllOffersByCategory(true,
                true,category_id).
                enqueue(new Callback<OfferForClinic>() {
                    @Override
                    public void onResponse(Call<OfferForClinic> call, Response<OfferForClinic> response) {
                        dialog.dismiss();
                        if (response.code() == 200){
                            offerForClinicData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<OfferForClinic> call, Throwable t) {

                    }
                });
        return offerForClinicData;
    }
    //TODO get Data Of Reservation for Reservation Fragment
    public MutableLiveData<ClientReservation> getAllClientReservation(final Context context){
        final MutableLiveData<ClientReservation> mutableLiveData = new MutableLiveData<>();
        if (Common.CurrentUser != null) {
            Common.getAPIRequest().getAllClientReservation(
                    Common.CurrentUser.getData().getToken().getAccessToken(),
                    Common.CurrentUser.getData().getUser().getId() + "").enqueue(new Callback<ClientReservation>() {
                @Override
                public void onResponse(Call<ClientReservation> call, Response<ClientReservation> response) {
                    if (response.code() == 200)
                        mutableLiveData.setValue(response.body());
                    else {
                        try {
                            Toast.makeText(context, new JSONObject(
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
                public void onFailure(Call<ClientReservation> call, Throwable t) {

                }
            });
        }
        return mutableLiveData;
    }
}
