package com.example.health965.Common;

import com.example.health965.API.APIRequest;
import com.example.health965.API.RetrofitClient;
import com.example.health965.Models.LoginClinc.LoginClinc;
import com.example.health965.Models.LoginUser.LoginUser;

public class Common {
    //TODO Base Url
    public final static String BaseURL = "https://api.956health.martstations.com/";

    // TODO get APIRequest
    public static APIRequest getAPIRequest(){
        return RetrofitClient.getRetrofitClient().create(APIRequest.class);
    }

    public static LoginUser CurrentUser = null;
    public static LoginClinc CurrentClinic= null;
}
