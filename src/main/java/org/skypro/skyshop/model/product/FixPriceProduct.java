package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product{
    private static final int FIX_PRICE=100;

    public FixPriceProduct(UUID id, String productName, int FIX_PRICE) {
        super(id,productName, FIX_PRICE);
    }

    @Override
    public int getProductPrice() {
        return FIX_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return "Fix price товар: "+productName+", фиксированная цена - "+FIX_PRICE;
    }
}
