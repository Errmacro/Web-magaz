package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
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

    public UserBasket getUserBasket(){
        Map<UUID, Integer>productMap = productBasket.getProducts();
        List<BasketItem> items = productMap.entrySet().stream()
                .map(entry -> {
                    UUID productId = entry.getKey();
            Integer quantity = entry.getValue();
            Optional<Product> product = storageService.getProductById(productId);
                    Product actualProduct = product.orElseThrow(() -> new IllegalArgumentException("Product not found"));
            return new BasketItem(actualProduct, quantity);
        })
                .collect(Collectors.toList());
        return new UserBasket(items);
    }

    public ProductBasket getProductBasket() {
        return productBasket;
    }

    public StorageService getStorageService() {
        return storageService;
    }
}
