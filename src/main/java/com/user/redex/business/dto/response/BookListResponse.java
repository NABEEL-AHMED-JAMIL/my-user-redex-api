package com.user.redex.business.dto.response;

import com.google.gson.Gson;
import java.util.List;

public class BookListResponse {

    private List<BookResponse> books;

    public BookListResponse() {
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
