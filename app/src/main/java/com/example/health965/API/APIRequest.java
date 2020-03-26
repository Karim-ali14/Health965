package com.example.health965.API;

import com.example.health965.Common.Common;
import com.example.health965.Models.BannerForCategory.BannerForCategory;
import com.example.health965.Models.Category.Category;
import com.example.health965.Models.ChangePassword.ResponseChangePassword.ResponseChangePassword;
import com.example.health965.Models.ChangePassword.ChangePassword;
import com.example.health965.Models.ClientReservation.ClientReservation;
import com.example.health965.Models.Clinics.Clinics;
import com.example.health965.Models.Doctors.Doctors;
import com.example.health965.Models.DoctorsWithClinics.DoctorsWithClinics;
import com.example.health965.Models.FireBaseToken.FireBaseToken;
import com.example.health965.Models.FireBaseToken.FireBaseTokenRespons;
import com.example.health965.Models.Governorate.Governorate;
import com.example.health965.Models.IsReserved.IsReserved;
import com.example.health965.Models.LoginClient.LoginClient;
import com.example.health965.Models.LoginClinc.LoginClinc;
import com.example.health965.Models.MakeReservation.RequestOfReservation;
import com.example.health965.Models.Notification.Notifications;
import com.example.health965.Models.OfferForClinic.OfferForClinic;
import com.example.health965.Models.Offers.Offers;
import com.example.health965.Models.Options.Option;
import com.example.health965.Models.ReSetPassword;
import com.example.health965.Models.Regestration.DataUserRegistration;
import com.example.health965.Models.Regestration.Registration;
import com.example.health965.Models.Reservation.Reservation;
import com.example.health965.Models.Reservation.UpDate.UpdateStatusOfReservation;
import com.example.health965.Models.Reservation.UpDate.ModelOfUpDate;
import com.example.health965.Models.Setting.Setting;
import com.example.health965.Models.UpdateClientInfo.RequestUpdateClientInfo;
import com.example.health965.Models.UpdateClientInfo.ResponseUpdateClientInfo.ResponseUpdateClientInfo;

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

    // TODO User LoginClient
    @FormUrlEncoded
    @POST("client/signin")
    Call<LoginClient> onLoginAsClient(@Field("mobilePhone") String phone, @Field("password") String password);

    // TODO Partner LoginClient
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

    //TODO Change Password to Clinic
    @GET("client/{id}")
    Call<LoginClient> getDataOfClient(@Header ("Authorization") String Auth,
                                                    @Path("id") String id);

    //TODO Change Password to Clinic
    @GET("clinic/{id}")
    Call<LoginClinc> getDataOfClinic(@Header ("Authorization") String Auth,
                                                    @Path("id") String id);

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
    Call<Clinics> getAllClinics(@Query("select") String image,
                                @Query("category_id") String category_Id,
                                @Query("select") String clinicOptions,
                                @Query("select") String clinicCertificate,
                                @Query("select") String contacts);

    //TODO Get All Clinics
    @GET("clinic")
    Call<Clinics> getClinicsByGovernorate(@Query("select") String image,
                                          @Query("select") String clinicOptions,
                                          @Query("select") String clinicCertificate,
                                          @Query("category_id") String category_Id,
                                          @Query("governorate_id") String Governorate_Id,
                                          @Query("select") String contacts);

    //TODO Get Clinic Info
    @GET("clinic")
    Observable<Clinics> getClinicInfo(@Query("select") String image,
                                      @Query("id") String ID,
                                      @Query("select") String clinicOptions,
                                      @Query("select") String clinicCertificate,
                                      @Query("select") String contacts);

    //TODO Get All Doctors
    @GET("doctor")
    Call<Doctors> getDoctors(@Query("clinic_id") String Id, @Query("select") String image);

    //TODO Get All Doctors With his Clinics
    @GET("doctor")
    Call<DoctorsWithClinics> getDoctorsWithClinics(@Query("select") String image,@Query("category_id") String categoryId);

    //TODO Get All Governorate
    @GET("governorate")
    Call<Governorate> getAllGovernorate();


    //TODO Get All Reservation
    @GET("/clinic/{id}/reservation")
    Call<Reservation> getAllClinicReservation(@Header("Authorization") String Token
            , @Path("id") String id);

    //TODO Get All Reservation
    @GET("/client/{id}/reservation")
    Call<ClientReservation> getAllClientReservation(@Header("Authorization") String Token
            , @Path("id") String id);


    //TODO Reservation
    @POST("reservation")
    Call<Reservation> makeReservation(@Header("Authorization") String Token ,@Header("Content-Type") String Type,@Body RequestOfReservation request);


    //TODO Setting
    @GET("setting")
    Call<Setting> getSetting();

    @GET("offer")
    Observable<Offers> getOffersForHome(@Query("is_featured") boolean is_featured,
                                  @Query("include_background") boolean include_background,
                                  @Query("include_icon") boolean include_icon);

    @GET("offer")
    Call<OfferForClinic> getOffersForClinic(@Query("clinic_id") String clinic_id,
                                            @Query("include_background") boolean include_background,
                                            @Query("include_icon") boolean include_icon);

    @GET("offer")
    Call<OfferForClinic> getAllOffersF(@Query("include_background") boolean include_background,
                                            @Query("include_icon") boolean include_icon);

    @GET("offer")
    Call<OfferForClinic> getAllOffersByCategory(@Query("include_background") boolean include_background,
                                            @Query("include_icon") boolean include_icon,
                                            @Query("category_id") String  category_id);

    @GET("banner")
    Observable<BannerForCategory> getBannerForCategories(@Query("include_image") boolean include_image,
                                             @Query("for") String forHome);
    @GET("banner")
    Observable<BannerForCategory> getBannerForCategory(@Query("include_image") boolean include_image,
                                             @Query("category_id") String category_id);
    @GET("banner")
    Observable<BannerForCategory> getBannerForClinic(@Query("include_image") boolean include_image,
                                             @Query("clinic_id") String clinic_id,
                                             @Query("for") String forClinic);

    @GET("banner")
    Observable<BannerForCategory> getBannerForGovernorate(@Query("include_image") boolean include_image,
                                             @Query("governorate_id") String governorate_id);

    @GET("notification")
    Observable<Notifications> getNotification(@Header("Authorization") String Authorization);

    @PUT("clinic/{clinic_id}/device-id")
    Call<FireBaseTokenRespons> onUpDateFireBaseTokenClinic(@Header("Authorization") String Authorization,
                                                           @Path("clinic_id")String clinic_id,
                                                           @Body FireBaseToken token);
    @PUT("client/{client_id}/device-id")
    Call<FireBaseTokenRespons> onUpDateFireBaseTokenClient(@Header("Authorization") String Authorization,@Header("Content-Type") String Content_Type,
                                              @Path("client_id")String client_id,
                                              @Body FireBaseToken token);

    @PUT("clinic/{clinic_id}/reservation/{reservation_id}")
    Call<UpdateStatusOfReservation> onUpDateReservation(@Header("Authorization") String Authorization,
                                                        @Path("clinic_id") String clinic_id,
                                                        @Path("reservation_id") String reservation_id,
                                                        @Body ModelOfUpDate model);
    @PUT("client/{client_id}/reservation/{reservation_id}")
    Call<UpdateStatusOfReservation> onUpDateReservationForClient(@Header("Authorization") String Authorization,
                                                        @Path("client_id") String client_id,
                                                        @Path("reservation_id") String reservation_id,
                                                        @Body ModelOfUpDate model);

    @GET("option")
    Call<Option> getDataOfOption(@Query("include_image") boolean image);

    @FormUrlEncoded
    @POST("clinic/forgot-password")
    Call<ReSetPassword> onSendCodeVerficationClinic(@Field("email")String email);

    @FormUrlEncoded
    @POST("clinic/reset-password")
    Call<ReSetPassword> onVerficationClinic(@Field("email")String email,
                                            @Field("passwordResetToken")String passwordResetToken,
                                            @Field("password")String password);

    @FormUrlEncoded
    @POST("client/forgot-password")
    Call<ReSetPassword> onSendCodeVerficationClient(@Field("email")String email);

    @FormUrlEncoded
    @POST("client/reset-password")
    Call<ReSetPassword> onVerficationClient(@Field("email")String email,
                                            @Field("passwordResetToken")String passwordResetToken,
                                            @Field("password")String password);

    @GET("client/{id}/reservation/is-reserved")
    Call<IsReserved> isReserved(@Header("Authorization") String Authorization,
                                @Path("id") String client_id,
                                @Query("clinic_id") String clinic_id);
}
