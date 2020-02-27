
package com.example.health965.Models.UpdateClientInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestUpdateClientInfo {

    @SerializedName("mobilePhone")
    @Expose
    private String mobilePhone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("fullName")
    @Expose
    private String fullName;

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public RequestUpdateClientInfo withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public RequestUpdateClientInfo(String mobilePhone, String email, String fullName) {
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RequestUpdateClientInfo withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public RequestUpdateClientInfo withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

}
