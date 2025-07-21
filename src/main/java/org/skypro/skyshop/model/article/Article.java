package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private final String articleName;
    private final String articleText;
    private final UUID id;

    @Override
    public UUID getId() {
        return id;
    }

    public Article(UUID id,String articleName, String articleText) {
        this.articleName = articleName;
        this.articleText = articleText;
        this.id = id;
    }

    @Override
    public String toString() {
        return "'" +articleName + "'" +
                ", содержание: " + articleText + '\'';
    }

    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return articleName + ": " + articleText;
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(articleName, article.articleName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(articleName);
    }

    public String getArticleName() {
        return articleName;
    }
}