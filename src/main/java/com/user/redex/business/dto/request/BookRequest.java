package com.user.redex.business.dto.request;

import com.google.gson.Gson;
import com.user.redex.business.enums.Category;
import com.user.redex.business.enums.Format;
import com.user.redex.business.enums.Language;
import java.time.LocalDate;

/**
 * @author Nabeel Ahmed
 */
public class BookRequest extends BaseEntityRequest {

    private String isbn; // yes
    private String title; // yes
    private Double price; // yes
    private String publisher; // yes
    private LocalDate publication; // yes
    private Language language; // yes
    private Category Category; // yes
    private Format format; // yes
    private String description; // yes
    private String coverImg; // yes
    private String bookUrl; // yes
    private String note; // yes
    private AuthorRequest author; // yes

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

    public LocalDate getPublication() {
        return publication;
    }

    public void setPublication(LocalDate publication) {
        this.publication = publication;
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

    public AuthorRequest getAuthor() {
        return author;
    }

    public void setAuthor(AuthorRequest author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
