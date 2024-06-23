package com.user.redex.business.dto.response;

import com.google.gson.Gson;

import java.util.List;

public class AuthorListResponse {

    private List<AuthorResponse> authors;

    public AuthorListResponse() {
    }

    public AuthorListResponse(List<AuthorResponse> authors) {
        this.authors = authors;
    }

    public List<AuthorResponse> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorResponse> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
