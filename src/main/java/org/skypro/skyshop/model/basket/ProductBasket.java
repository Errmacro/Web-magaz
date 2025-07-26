package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@SessionScope
@Component
@Service
public class ProductBasket {

    private final Map<UUID, Integer> totalBasket = new HashMap<>();

    public void addProduct(UUID id) {
        totalBasket.put(id, totalBasket.getOrDefault(id, 0) + 1);
    }

    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(totalBasket);
    }
}

