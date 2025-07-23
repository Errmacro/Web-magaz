package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int productPrice;
    private final int discount;

    public DiscountedProduct(String productName, int productPrice, int discount) {
        super(productName);
        this.discount=discount;
        this.productPrice=productPrice;
        if (productPrice <=0) {
            throw new IllegalArgumentException("Некорректная стоимость продукта");
        }
        if (discount <0||discount>100) {
            throw new IllegalArgumentException("Некорректный коэффициент скидок");
        }
    }

    @Override
    public int getProductPrice() {
        return productPrice - (productPrice * discount / 100);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return "Продукт со скидкой - "+productName+", "+getProductPrice()+" р. (скидка "+discount+"%)";
    }
}
