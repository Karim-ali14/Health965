
package com.example.health965.Models.MakeReservation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestOfReservation {

    @SerializedName("clinic_id")
    @Expose
    private Integer clinicId;
    @SerializedName("doctor_id")
    @Expose
    private Integer doctorId;

    public RequestOfReservation(Integer clinicId) {
        this.clinicId = clinicId;
    }

    public RequestOfReservation(Integer clinicId, Integer doctorId) {
        this.clinicId = clinicId;
        this.doctorId = doctorId;
    }

    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }

    public RequestOfReservation withClinicId(Integer clinicId) {
        this.clinicId = clinicId;
        return this;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public RequestOfReservation withDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
        return this;
    }

}
