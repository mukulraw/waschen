package com.TBX.tvs.waschen.ViewMorePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tvs on 11/17/2017.
 */

public class ViewMoreBean {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("status1")
    @Expose
    private String status1;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("isCancelable")
    @Expose
    private String isCancelable;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("placed date")
    @Expose
    private String placedDate;
    @SerializedName("total items")
    @Expose
    private String totalItems;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("order status")
    @Expose
    private String orderStatus;
    @SerializedName("order status1")
    @Expose
    private String orderStatus1;
    @SerializedName("trackingStatus")
    @Expose
    private String trackingStatus;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIsCancelable() {
        return isCancelable;
    }

    public void setIsCancelable(String isCancelable) {
        this.isCancelable = isCancelable;
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

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus1() {
        return orderStatus1;
    }

    public void setOrderStatus1(String orderStatus1) {
        this.orderStatus1 = orderStatus1;
    }

    public String getTrackingStatus() {
        return trackingStatus;
    }

    public void setTrackingStatus(String trackingStatus) {
        this.trackingStatus = trackingStatus;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }




}
