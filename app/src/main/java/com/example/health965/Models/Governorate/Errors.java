
package com.example.health965.Models.Governorate;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Errors {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("path")
    @Expose
    private List<Object> path = null;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("context")
    @Expose
    private Context context;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Errors withMessage(String message) {
        this.message = message;
        return this;
    }

    public List<Object> getPath() {
        return path;
    }

    public void setPath(List<Object> path) {
        this.path = path;
    }

    public Errors withPath(List<Object> path) {
        this.path = path;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Errors withType(String type) {
        this.type = type;
        return this;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Errors withContext(Context context) {
        this.context = context;
        return this;
    }

}
