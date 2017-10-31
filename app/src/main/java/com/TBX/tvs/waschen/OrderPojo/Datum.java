package com.TBX.tvs.waschen.OrderPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum  {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("placed_date")
    @Expose
    private String placedDate;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;

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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
