
package com.example.health965.Models.Reservation.UpDate;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Errors {

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

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
