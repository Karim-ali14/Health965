
package com.example.health965.Models.Clinics;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("path")
    @Expose
    private List<String> path = null;
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

    public Error withMessage(String message) {
        this.message = message;
        return this;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public Error withPath(List<String> path) {
        this.path = path;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Error withType(String type) {
        this.type = type;
        return this;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Error withContext(Context context) {
        this.context = context;
        return this;
    }

}
