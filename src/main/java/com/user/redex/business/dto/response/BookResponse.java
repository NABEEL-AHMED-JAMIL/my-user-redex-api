package com.user.redex.business.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.user.redex.business.enums.Category;
import com.user.redex.business.enums.Format;
import com.user.redex.business.enums.Language;
import java.time.LocalDate;

/**
 * @author Nabeel Ahmed
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse extends BaseEntityResponse {

    private String isbn;
    private String title;
    private Double price;
    private String publisher;
    private LocalDate publication;
    private GEnum<String> language;
    private GEnum<String> category;
    private GEnum<String> format;
    private String description;
    private String coverImg;
    private String bookUrl;
    private String note;
    private AuthorResponse author;

    public BookResponse() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublication() {
        return publication;
    }

    public void setPublication(LocalDate publication) {
        this.publication = publication;
    }

    public GEnum<String> getLanguage() {
        return language;
    }

    public void setLanguage(GEnum<String> language) {
        this.language = language;
    }

    public GEnum<String> getCategory() {
        return category;
    }

    public void setCategory(GEnum<String> category) {
        this.category = category;
    }

    public GEnum<String> getFormat() {
        return format;
    }

    public void setFormat(GEnum<String> format) {
        this.format = format;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public AuthorResponse getAuthor() {
        return author;
    }

    public void setAuthor(AuthorResponse author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
