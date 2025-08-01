package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    String getSearchTerm();
    String getContentType();
    default String getStringRepresentation(){
        return getSearchTerm()+", тип - "+getContentType();
    }
    UUID getId();
}
