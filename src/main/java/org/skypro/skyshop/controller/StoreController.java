package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class StoreController {
    private final StorageService storageService;
    private final BasketService basketService;
    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

    public StoreController(StorageService storageService,BasketService basketService) {
        this.storageService = storageService;
        this.basketService = basketService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        Collection<Product> products = storageService.getAllProducts();
        logger.info("[{}] Returning products to browser: {}", java.time.Instant.now(), products);
        return products;
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getAllArticles();
    }

    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam String pattern) {
        return storageService.search(pattern);
    }

    @GetMapping("/search-terms")
    public Collection<String> getSearchTerms() {
        Collection<Searchable> searchables = storageService.getAllSearchables();
        return searchables.stream()
                .map(Searchable::getSearchTerm)
                .collect(Collectors.toList());

    }

    @GetMapping("/searchables")
    public Collection<Searchable> getAll() {
        return storageService.getAllSearchables();}

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        basketService.addProductToBasket(id);
        return "Продукт успешно добавлен";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }
}
