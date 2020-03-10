
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
    @SerializedName("whoWeAreEn")
    @Expose
    private String whoWeAreEn;
    @SerializedName("privacy")
    @Expose
    private String privacy;
    @SerializedName("privacyEn")
    @Expose
    private String privacyEn;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("instagram")
    @Expose
    private String instagram;
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

    public String getWhoWeAre() {
        return whoWeAre;
    }

    public void setWhoWeAre(String whoWeAre) {
        this.whoWeAre = whoWeAre;
    }

    public String getWhoWeAreEn() {
        return whoWeAreEn;
    }

    public void setWhoWeAreEn(String whoWeAreEn) {
        this.whoWeAreEn = whoWeAreEn;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getPrivacyEn() {
        return privacyEn;
    }

    public void setPrivacyEn(String privacyEn) {
        this.privacyEn = privacyEn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getGooglePlus() {
        return googlePlus;
    }

    public void setGooglePlus(String googlePlus) {
        this.googlePlus = googlePlus;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSupportNum() {
        return supportNum;
    }

    public void setSupportNum(String supportNum) {
        this.supportNum = supportNum;
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