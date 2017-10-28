package com.TBX.tvs.waschen.GetZipPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tvs on 10/28/2017.
 */

public class GetZipBean {

    @SerializedName("delivery_method")
    @Expose
    private List<DeliveryMethod> deliveryMethod = null;

    public List<DeliveryMethod> getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(List<DeliveryMethod> deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

}
