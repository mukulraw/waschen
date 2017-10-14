package com.TBX.tvs.waschen.DeliveryMethodPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryMethod {


    @SerializedName("method_id")
    @Expose
    private String methodId;
    @SerializedName("method_name")
    @Expose
    private String methodName;
    @SerializedName("method_price")
    @Expose
    private String methodPrice;
    @SerializedName("delivery_time")
    @Expose
    private String deliveryTime;

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodPrice() {
        return methodPrice;
    }

    public void setMethodPrice(String methodPrice) {
        this.methodPrice = methodPrice;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
