
package com.example.health965.Models.FireBaseToken;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("firebase_device_id")
    @Expose
    private String firebaseDeviceId;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirebaseDeviceId() {
        return firebaseDeviceId;
    }

    public void setFirebaseDeviceId(String firebaseDeviceId) {
        this.firebaseDeviceId = firebaseDeviceId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
