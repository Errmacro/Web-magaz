package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class StoreController {
    private final StorageService storageService;

    public StoreController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts(){
        return storageService.getProducts().values();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles(){
        return storageService.getArticles().values();
    }

    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam String pattern) {
        Collection<Searchable> searchables = storageService.getAllSearchables();

        return searchables.stream()
                .filter(searchable -> searchable.getStringRepresentation().toLowerCase().contains(pattern.toLowerCase()))
                .map(searchable -> new SearchResult(searchable.getId().toString(), searchable.getSearchTerm(), searchable.getContentType()))
                .collect(Collectors.toList());
    }
}
