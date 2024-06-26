package com.user.redex.business.dto.request;

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
public class BookRequest extends BaseEntityRequest {

    private String isbn;
    private String title;
    private Double price;
    private String publisher;
    private Language language;
    private Category Category;
    private Format format;
    private String description;
    private String note;

    public BookRequest() {
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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public com.user.redex.business.enums.Category getCategory() {
        return Category;
    }

    public void setCategory(com.user.redex.business.enums.Category category) {
        Category = category;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
