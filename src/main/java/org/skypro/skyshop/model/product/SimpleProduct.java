package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int productPrice;


    public SimpleProduct(String productName, int productPrice) {
        super(productName);
        this.productPrice = productPrice;
        if (productPrice <=0) {
            throw new IllegalArgumentException("Некорректная стоимость продукта");
        }
    }

    @Override
    public int getProductPrice() {
        return productPrice;
    }

    @Override
    public String getProductName() {
        return super.getProductName();
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return productName+", цена - "+productPrice;
    }
}
