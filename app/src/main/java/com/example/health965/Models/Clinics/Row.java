
package com.example.health965.Models.Clinics;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Row implements Parcelable {

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
    private List<ClinicOption> clinicOptions;
    @SerializedName("clinicCertificate")
    @Expose
    private List<ClinicCertificate> clinicCertificate;


    protected Row(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        address = in.readString();
        description = in.readString();
        byte tmpIsSpecial = in.readByte();
        isSpecial = tmpIsSpecial == 0 ? null : tmpIsSpecial == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(description);
        dest.writeByte((byte) (isSpecial == null ? 0 : isSpecial ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Row> CREATOR = new Creator<Row>() {
        @Override
        public Row createFromParcel(Parcel in) {
            return new Row(in);
        }

        @Override
        public Row[] newArray(int size) {
            return new Row[size];
        }
    };

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

}
