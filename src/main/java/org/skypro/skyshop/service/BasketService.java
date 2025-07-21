package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;

import java.util.Optional;
import java.util.UUID;

public class BasketService {

    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        Product product = storageService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Продукт с ID " + id + " не найден"));

        Integer currentCount = productBasket.getProducts().getOrDefault(id, 0);
        productBasket.addProduct(id, currentCount + 1);
    }
}
