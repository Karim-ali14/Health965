
package com.example.health965.Models.Clinics;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Row {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("isSpecial")
    @Expose
    private Boolean isSpecial;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("clinicOptions")
    @Expose
    private List<ClinicOption> clinicOptions = null;
    @SerializedName("clinicCertificate")
    @Expose
    private List<ClinicCertificate> clinicCertificate;
    @SerializedName("contacts")
    @Expose
    private Contacts contacts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Boolean isSpecial) {
        this.isSpecial = isSpecial;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<ClinicOption> getClinicOptions() {
        return clinicOptions;
    }

    public void setClinicOptions(List<ClinicOption> clinicOptions) {
        this.clinicOptions = clinicOptions;
    }

    public List<ClinicCertificate> getClinicCertificate() {
        return clinicCertificate;
    }

    public void setClinicCertificate(List<ClinicCertificate> clinicCertificate) {
        this.clinicCertificate = clinicCertificate;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

}
