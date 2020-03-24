
package com.example.health965.Models.IsReserved;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("is_reserved")
    @Expose
    private Boolean isReserved;

    public Boolean getIsReserved() {
        return isReserved;
    }

    public void setIsReserved(Boolean isReserved) {
        this.isReserved = isReserved;
    }

}
