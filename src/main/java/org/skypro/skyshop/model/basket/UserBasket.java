package org.skypro.skyshop.model.basket;

import java.util.List;
import java.util.Objects;

public class UserBasket {
    private final List<BasketItem> items;
    private final int total;

    public UserBasket(List<BasketItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Items cannot be null or empty");
        }
        this.items = List.copyOf(items);
        this.total = calculateTotal();
    }

    private int calculateTotal() {
        return (int) items.stream()
                .mapToDouble(BasketItem::getTotalPrice)
                .sum();
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserBasket)) return false;
        UserBasket that = (UserBasket) o;
        return total == that.total && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, total);
    }

}
