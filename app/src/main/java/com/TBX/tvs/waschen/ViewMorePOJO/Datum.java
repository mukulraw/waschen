package com.TBX.tvs.waschen.ViewMorePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tvs on 11/17/2017.
 */

public class Datum {

    @SerializedName("productId")
    @Expose
    private String productId;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("unit_price")
    @Expose
    private String unitPrice;
    @SerializedName("totalPrice")
    @Expose
    private String totalPrice;
    @SerializedName("image")
    @Expose
    private String image;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
