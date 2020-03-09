
package com.example.health965.Models.Clinics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Context {

    @SerializedName("child")
    @Expose
    private String child;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("pos")
    @Expose
    private Integer pos;

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public Context withChild(String child) {
        this.child = child;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Context withValue(String value) {
        this.value = value;
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

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public Context withPos(Integer pos) {
        this.pos = pos;
        return this;
    }

}
