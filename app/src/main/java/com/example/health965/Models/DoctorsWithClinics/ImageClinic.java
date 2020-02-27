
package com.example.health965.Models.DoctorsWithClinics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageClinic {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("alt")
    @Expose
    private Object alt;
    @SerializedName("for")
    @Expose
    private String _for;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ImageClinic withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageClinic withName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageClinic withDescription(String description) {
        this.description = description;
        return this;
    }

    public Object getAlt() {
        return alt;
    }

    public void setAlt(Object alt) {
        this.alt = alt;
    }

    public ImageClinic withAlt(Object alt) {
        this.alt = alt;
        return this;
    }

    public String getFor() {
        return _for;
    }

    public void setFor(String _for) {
        this._for = _for;
    }

    public ImageClinic withFor(String _for) {
        this._for = _for;
        return this;
    }

}
