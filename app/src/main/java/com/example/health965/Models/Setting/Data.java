
package com.example.health965.Models.Setting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("whoWeAre")
    @Expose
    private String whoWeAre;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("linkedIn")
    @Expose
    private String linkedIn;
    @SerializedName("googlePlus")
    @Expose
    private String googlePlus;
    @SerializedName("youtube")
    @Expose
    private String youtube;
    @SerializedName("whatsApp")
    @Expose
    private String whatsApp;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("supportNum")
    @Expose
    private String supportNum;
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

    public Data withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getWhoWeAre() {
        return whoWeAre;
    }

    public void setWhoWeAre(String whoWeAre) {
        this.whoWeAre = whoWeAre;
    }

    public Data withWhoWeAre(String whoWeAre) {
        this.whoWeAre = whoWeAre;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Data withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public Data withFacebook(String facebook) {
        this.facebook = facebook;
        return this;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public Data withTwitter(String twitter) {
        this.twitter = twitter;
        return this;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public Data withLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
        return this;
    }

    public String getGooglePlus() {
        return googlePlus;
    }

    public void setGooglePlus(String googlePlus) {
        this.googlePlus = googlePlus;
    }

    public Data withGooglePlus(String googlePlus) {
        this.googlePlus = googlePlus;
        return this;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public Data withYoutube(String youtube) {
        this.youtube = youtube;
        return this;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }

    public Data withWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Data withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Data withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getSupportNum() {
        return supportNum;
    }

    public void setSupportNum(String supportNum) {
        this.supportNum = supportNum;
    }

    public Data withSupportNum(String supportNum) {
        this.supportNum = supportNum;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Data withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Data withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

}
