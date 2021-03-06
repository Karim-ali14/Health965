
package com.example.health965.Models.Doctors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Context {

    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("key")
    @Expose
    private String key;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Context withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Context withValue(Integer value) {
        this.value = value;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Context withLabel(String label) {
        this.label = label;
        return this;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Context withKey(String key) {
        this.key = key;
        return this;
    }

}
