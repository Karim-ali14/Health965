package com.example.health965.API;

import com.example.health965.Common.Common;
import com.example.health965.Models.Category.Category;
import com.example.health965.Models.ChangePassword.ResponseChangePassword.ResponseChangePassword;
import com.example.health965.Models.ChangePassword.ChangePassword;
import com.example.health965.Models.Doctors.Doctors;
import com.example.health965.Models.DoctorsWithClinics.DoctorsWithClinics;
import com.example.health965.Models.Governorate.Governorate;
import com.example.health965.Models.LoginClinc.LoginClinc;
import com.example.health965.Models.LoginUser.LoginUser;
import com.example.health965.Models.MakeReservation.RequestOfReservation;
import com.example.health965.Models.Regestration.DataUserRegistration;
import com.example.health965.Models.Regestration.Registration;
import com.example.health965.Models.Reservation.Reservation;
import com.example.health965.Models.Setting.Setting;
import com.example.health965.Models.UpdateClientInfo.RequestUpdateClientInfo;
import com.example.health965.Models.UpdateClientInfo.ResponseUpdateClientInfo.ResponseUpdateClientInfo;
import com.example.health965.Models.getClincs.Clinics;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIRequest {
    String nn = Common.CurrentClinic.getData().getToken().getAccessToken();
    // TODO User Registration
    @POST("client/signup")
    Call<Registration> onRegistration(@Body DataUserRegistration data);

    // TODO User LoginUser
    @FormUrlEncoded
    @POST("client/signin")
    Call<LoginUser> onLoginAsUser(@Field("mobilePhone") String phone, @Field("password") String password);

    // TODO Partner LoginUser
    @FormUrlEncoded
    @POST("clinic/signin")
    Call<LoginClinc> onLoginAsPartner(@Field("mobilePhone") String phone, @Field("password") String password);

    //TODO Get All Category
    @GET("category/")
    Observable<Category> getAllCategory(@Query("include_icon") boolean include_icon);

    //TODO Change Password to Clinic
    @PUT("clinic/{id}/password")
    Call<ResponseChangePassword> onChangeClinicPass(@Header ("Authorization") String Auth,
                                                    @Header("Content-Type") String Type,
                                                    @Path("id") String id,
                                                    @Body ChangePassword body);
    //TODO Change Password to Client
    @PUT("client/{id}/password")
    Call<ResponseChangePassword> onChangeClientPass(@Header ("Authorization") String Auth,
                                                    @Header("Content-Type") String Type,
                                                    @Path("id") String id,
                                                    @Body ChangePassword body);
    //TODO Change Password to Client
    @PUT("client/{id}")
    Call<ResponseUpdateClientInfo> onUpdateClientInfo(@Header ("Authorization") String Auth,
                                                      @Header("Content-Type") String Type,
                                                      @Path("id") String id,
                                                      @Body RequestUpdateClientInfo body);

    //TODO Get All Clinics
    @GET("clinic")
    Call<Clinics> getAllClinics(@Query("select") String image,@Query("category_id") String category_Id);

    //TODO Get All Clinics
    @GET("clinic")
    Call<Clinics> getClinicsByGovernorate(@Query("select") String image,@Query("category_id") String category_Id, @Query("governorate_id") String Governorate_Id);

    //TODO Get Clinic Info
//    @GET("clinic")
//    Observable<Clinics> getClinicInfo(@Query("select") String image, @Query("id") String ID);

    //TODO Get All Doctors
    @GET("doctor")
    Call<Doctors> getDoctors(@Query("clinic_id") String Id, @Query("select") String image);

    //TODO Get All Doctors With his Clinics
    @GET("doctor")
    Call<DoctorsWithClinics> getDoctorsWithClinics(@Query("select") String image);

    //TODO Get All Governorate
    @GET("governorate")
    Call<Governorate> getAllGovernorate();


    //TODO Get All Reservation
    @GET("/clinic/{id}/reservation")
    Call<Reservation> getReservation(@Header("Authorization") String Token , @Path("id") String id);


    //TODO Reservation
    @POST("reservation")
    Call<Reservation> makeReservation(@Header("Authorization") String Token ,@Header("Content-Type") String Type,@Body RequestOfReservation request);


    //TODO Setting
    @GET("setting")
    Call<Setting> getSetting();
}
