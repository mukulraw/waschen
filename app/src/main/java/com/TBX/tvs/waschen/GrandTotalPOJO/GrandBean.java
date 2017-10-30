package com.TBX.tvs.waschen.GrandTotalPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tvs on 10/30/2017.
 */

public class GrandBean {


    @SerializedName("cart_sub_total")
    @Expose
    private String cartSubTotal;
    @SerializedName("delivery_price")
    @Expose
    private String deliveryPrice;
    @SerializedName("coupon_price")
    @Expose
    private String couponPrice;
    @SerializedName("gst_price")
    @Expose
    private Double gstPrice;
    @SerializedName("grand_total")
    @Expose
    private Double grandTotal;

    public String getCartSubTotal() {
        return cartSubTotal;
    }

    public void setCartSubTotal(String cartSubTotal) {
        this.cartSubTotal = cartSubTotal;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(String couponPrice) {
        this.couponPrice = couponPrice;
    }

    public Double getGstPrice() {
        return gstPrice;
    }

    public void setGstPrice(Double gstPrice) {
        this.gstPrice = gstPrice;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

}
