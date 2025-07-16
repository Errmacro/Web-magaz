package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class StoreController {
    private final StorageService storageService;
    private Searchable searchable;

    public StoreController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getAllProducts();
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
}
