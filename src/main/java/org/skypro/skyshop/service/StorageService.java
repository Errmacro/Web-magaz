package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService(){
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
        return products;
    }

    public Map<UUID, Article> getArticles() {
        return articles;
    }

    private void addToStorage() {
        products.put(UUID.randomUUID(),new SimpleProduct(UUID.randomUUID(), "Книга", 500));
        products.put(UUID.randomUUID(),new SimpleProduct(UUID.randomUUID(), "Клей", 110));
        products.put(UUID.randomUUID(),new DiscountedProduct(UUID.randomUUID(), "Тетрадь", 30, 5));

        articles.put(UUID.randomUUID(), new Article(UUID.randomUUID(),"Крепость", "Книга о поиске себя"));
        articles.put(UUID.randomUUID(), new Article(UUID.randomUUID(),"Мост на Дрине", "Книга о вечности и людских судьбах"));
        articles.put(UUID.randomUUID(), new Article(UUID.randomUUID(),"Магия Белграда","Книга о неповторимом духе древнего города"));
    }
}
