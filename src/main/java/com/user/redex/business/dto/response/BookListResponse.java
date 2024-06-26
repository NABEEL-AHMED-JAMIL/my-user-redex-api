package com.user.redex.business.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import java.util.List;

/**
 * @author Nabeel Ahmed
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookListResponse {

    private List<BookResponse> books;

    public BookListResponse() {
    }

    public BookListResponse(List<BookResponse> books) {
        this.books = books;
    }

    public List<BookResponse> getBooks() {
        return books;
    }

    public void setBooks(List<BookResponse> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
