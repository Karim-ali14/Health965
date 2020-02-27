
package com.example.health965.Models.DoctorsWithClinics;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clinic {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("image_id")
    @Expose
    private Integer imageId;
    @SerializedName("image")
    @Expose
    private ImageClinic image;
    @SerializedName("clinicOptions")
    @Expose
    private List<Object> clinicOptions = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Clinic withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clinic withName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Clinic withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Clinic withStatus(String status) {
        this.status = status;
        return this;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Clinic withImageId(Integer imageId) {
        this.imageId = imageId;
        return this;
    }

    public ImageClinic getImage() {
        return image;
    }

    public void setImage(ImageClinic image) {
        this.image = image;
    }

    public Clinic withImage(ImageClinic image) {
        this.image = image;
        return this;
    }

    public List<Object> getClinicOptions() {
        return clinicOptions;
    }

    public void setClinicOptions(List<Object> clinicOptions) {
        this.clinicOptions = clinicOptions;
    }

    public Clinic withClinicOptions(List<Object> clinicOptions) {
        this.clinicOptions = clinicOptions;
        return this;
    }

}
