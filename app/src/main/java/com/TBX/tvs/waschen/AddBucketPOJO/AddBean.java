package com.TBX.tvs.waschen.AddBucketPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class AddBean {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("bucketCount")
    @Expose
    private String bucketCount;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBucketCount() {
        return bucketCount;
    }

    public void setBucketCount(String bucketCount) {
        this.bucketCount = bucketCount;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
