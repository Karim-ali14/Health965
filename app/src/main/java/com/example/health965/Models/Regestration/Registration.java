
package com.example.health965.Models.Regestration;
import com.google.gson.annotations.SerializedName;

public class Registration {

    @SerializedName("success")
    private Boolean success;
    @SerializedName("error")
    private java.lang.Error error;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Data data;
    @SerializedName("code")
    private Integer code;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public java.lang.Error getError() {
        return error;
    }

    public void setError(java.lang.Error error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
