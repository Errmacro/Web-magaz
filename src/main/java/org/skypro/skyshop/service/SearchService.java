package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public void search(String keyword) {
        Collection<Searchable> searchables = storageService.getAllSearchables();

        searchables.stream()
                .filter(searchable -> searchable.getSearchTerm().toLowerCase().contains(keyword.toLowerCase()))
                .forEach(searchable -> System.out.println("Найдено: " + searchable.getSearchTerm()));
    }
}
