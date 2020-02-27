
package com.example.health965.Models.DoctorsWithClinics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorsWithClinics {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private Object error;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public DoctorsWithClinics withSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DoctorsWithClinics withCode(Integer code) {
        this.code = code;
        return this;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public DoctorsWithClinics withData(Data data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DoctorsWithClinics withMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public DoctorsWithClinics withError(Object error) {
        this.error = error;
        return this;
    }

}
