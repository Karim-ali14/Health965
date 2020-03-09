package com.example.health965.Models.Reservation.UpDate;

import com.google.gson.annotations.SerializedName;

public class ModelOfUpDate {
    @SerializedName("status")
    private String status;

    public ModelOfUpDate(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
