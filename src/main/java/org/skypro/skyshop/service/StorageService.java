package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        addToStorage();
    }

    public Collection<Searchable> getAllSearchables() {
        Set<Searchable> searchables = new HashSet<>();
        searchables.addAll(products.values());
        searchables.addAll(articles.values());
        return searchables;
    }

    public Map<UUID, Product> getProducts() {
        System.out.println("Data from getProducts: " + products);
        return products;
    }

    public Map<UUID, Article> getArticles() {
        return articles;
    }

    public Collection<Product> getAllProducts() {
        System.out.println("Data from storageService: " + products);
        return getProducts().values();
    }

    public Collection<Article> getAllArticles() {
        return getArticles().values();
    }

    public Collection<SearchResult> search(String pattern) {
        Collection<Searchable> searchables = getAllSearchables();
        return searchables.stream()
                .filter(searchable -> searchable.getStringRepresentation().toLowerCase().contains(pattern.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }

    private void addToStorage() {
        Product book = new SimpleProduct("Книга", 500);
        products.put(book.getId(),book);
        products.put(UUID.randomUUID(), new SimpleProduct("Клей", 110));
        products.put(UUID.randomUUID(), new DiscountedProduct("Тетрадь", 30, 5));

        articles.put(UUID.randomUUID(), new Article( "Крепость", "Книга о поиске себя"));
        articles.put(UUID.randomUUID(), new Article( "Мост на Дрине", "Книга о вечности и людских судьбах"));
        articles.put(UUID.randomUUID(), new Article( "Магия Белграда", "Книга о неповторимом духе древнего города"));
    }

    public Optional<Product> getProductById(UUID id) {
        System.out.println(products);
        return Optional.ofNullable(products.get(id));
    }

}
