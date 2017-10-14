package com.TBX.tvs.waschen.DeliveryMethodPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tvs on 10/13/2017.
 */

public class DeliveryBean {

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
