package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    protected final String productName;
    private final UUID id;

    @Override
    public UUID getId() {
        return id;
    }

    public Product(UUID id,String productName, int productPrice) {
        this.productName = productName;
        this.id = id;
        if ((productName == null)||(productName.isBlank())) {
            throw new IllegalArgumentException("Некорректное имя продукта");
        }
    }

    public String getProductName() {
        return productName;
    }

    public abstract int getProductPrice();

    @Override
    public abstract String toString();

    public abstract boolean isSpecial();

    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return productName;
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productName);
    }
}
