package com.example.health965.Common;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.health965.API.APIRequest;
import com.example.health965.API.RetrofitClient;
import com.example.health965.Models.LoginClinc.LoginClinc;
import com.example.health965.Models.LoginClient.LoginClient;

public class Common {
    //TODO Base Url
    public final static String BaseURL = "https://api.app.965health.com/";
    public final static String BaseURLForImage = "https://app.965health.com/";
    //public final static String BaseURL = "https://api.956health.martstations.com/";

    // TODO get APIRequest
    public static APIRequest getAPIRequest(){
        return RetrofitClient.getRetrofitClient().create(APIRequest.class);
    }

    public static LoginClient CurrentUser = null;

    public static LoginClinc CurrentClinic = null;

    public static String FileName = "TokenFile";
    public static String Token = "Token";
    public static String ID = "Client_id";
    public static String Type = "Type";

}
