
package com.example.health965.Models.DoctorsWithClinics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Row {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("specialty")
    @Expose
    private String specialty;
    @SerializedName("clinic_id")
    @Expose
    private Integer clinicId;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("clinic")
    @Expose
    private Clinic clinic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Row withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Row withName(String name) {
        this.name = name;
        return this;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Row withSpecialty(String specialty) {
        this.specialty = specialty;
        return this;
    }

    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }

    public Row withClinicId(Integer clinicId) {
        this.clinicId = clinicId;
        return this;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Row withImage(Image image) {
        this.image = image;
        return this;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Row withClinic(Clinic clinic) {
        this.clinic = clinic;
        return this;
    }

}
