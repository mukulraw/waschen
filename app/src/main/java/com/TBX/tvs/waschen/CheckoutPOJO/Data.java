package com.TBX.tvs.waschen.CheckoutPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Data {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("placedDate")
    @Expose
    private String placedDate;
    @SerializedName("trackingId")
    @Expose
    private String trackingId;
    @SerializedName("address")
    @Expose
    private String address;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPlacedDate() {
        return placedDate;
    }

    public void setPlacedDate(String placedDate) {
        this.placedDate = placedDate;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
