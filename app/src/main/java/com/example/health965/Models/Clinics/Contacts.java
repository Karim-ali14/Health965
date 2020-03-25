
package com.example.health965.Models.Clinics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contacts {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("phoneNumber1")
    @Expose
    private String phoneNumber1;
    @SerializedName("phoneNumber2")
    @Expose
    private Object phoneNumber2;
    @SerializedName("phoneNumber3")
    @Expose
    private Object phoneNumber3;
    @SerializedName("whatsApp")
    @Expose
    private String whatsApp;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public Object getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(Object phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public Object getPhoneNumber3() {
        return phoneNumber3;
    }

    public void setPhoneNumber3(Object phoneNumber3) {
        this.phoneNumber3 = phoneNumber3;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
