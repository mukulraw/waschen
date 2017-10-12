package com.TBX.tvs.waschen.BucketCountPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BucketCountBean {


    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("bucket_count")
    @Expose
    private Integer bucketCount;
    @SerializedName("cartid")
    @Expose
    private String cartid;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBucketCount() {
        return bucketCount;
    }

    public void setBucketCount(Integer bucketCount) {
        this.bucketCount = bucketCount;
    }

    public String getCartid() {
        return cartid;
    }

    public void setCartid(String cartid) {
        this.cartid = cartid;
    }

}
