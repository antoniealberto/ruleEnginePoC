package com.ubs.eqdel.rules.model.explosion;

import com.ubs.eqdel.rules.model.Product;

public class EqiProduct implements Product {

    private final String productType;
    private final String productPayload;

    public EqiProduct(String productType, String productPayload){
        this.productType = productType;
        this.productPayload = productPayload;
    }

    @Override
    public String getProductType() {
        return productType;
    }

    @Override
    public String getProductAsString() {
        return productPayload;
    }
}
