package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductBasket {
    private final Map<UUID, Integer> totalBasket;

    public ProductBasket() {
        totalBasket = new HashMap<>();
    }

    public void addProduct(UUID key, Integer value) {
        totalBasket.put(key, value);
    }

    public Map<UUID, Integer> getTotalBasket() {
        return totalBasket.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(totalBasket);
    }
}
