package com.example.health965.Models.Regestration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataUserRegistration {
    @SerializedName("mobilePhone")
    private String mobilePhone;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("password")
    private String password;
    @SerializedName("email")
    private String email;

    public DataUserRegistration(String fullName, String mobilePhone ,String email, String password) {
        this.mobilePhone = mobilePhone;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
