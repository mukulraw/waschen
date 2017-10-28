package com.TBX.tvs.waschen.GetZipPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tvs on 10/28/2017.
 */

public class DeliveryMethod {

    @SerializedName("coupon")
    @Expose
    private String coupon;

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

}
