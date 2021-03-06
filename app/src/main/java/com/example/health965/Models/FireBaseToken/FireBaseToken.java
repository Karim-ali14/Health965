package com.example.health965.Models.FireBaseToken;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FireBaseToken {

    @SerializedName("firebase_device_id")
    @Expose
    private String firebaseDeviceId;

    public FireBaseToken(String firebaseDeviceId) {
        this.firebaseDeviceId = firebaseDeviceId;
    }

    public String getFirebaseDeviceId() {
        return firebaseDeviceId;
    }

    public void setFirebaseDeviceId(String firebaseDeviceId) {
        this.firebaseDeviceId = firebaseDeviceId;
    }

}
