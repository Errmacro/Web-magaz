package org.skypro.skyshop.service;

import org.skypro.skyshop.exceptions.NoSuchProductException;
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
        Product clue = new SimpleProduct("Клей", 110);
        Product notebook = new DiscountedProduct("Тетрадь", 30, 5);
        Article fortress = new Article("Крепость", "Книга о поиске себя");
        Article bridge = new Article("Мост на Дрине", "Книга о вечности и людских судьбах");
        Article magic = new Article("Магия Белграда", "Книга о неповторимом духе древнего города");
        products.put(book.getId(), book);
        products.put(clue.getId(), clue);
        products.put(notebook.getId(), notebook);

        articles.put(fortress.getId(), fortress);
        articles.put(bridge.getId(), bridge);
        articles.put(magic.getId(), magic);
    }

    public Optional<Product> getProductById(UUID id) {
        if (id == null) {
            throw new NoSuchProductException("ID продукта не может быть пустым");
        }
        System.out.println(products);
        return Optional.ofNullable(products.get(id));
    }

}
