package com.user.redex.business.dto.request;

import com.google.gson.Gson;
import java.util.List;

/**
 * @author Nabeel Ahmed
 */
public class AuthorRequest extends BaseEntityRequest {

    private String firstName; // yes
    private String lastName; // yes
    private String email; // yes
    private String username; // yes
    private String password; // yes
    private String biography; // yes
    private String nationality; // yes
    private String expertise; // yes
    private String image;
    private List<BookRequest> books;

    public AuthorRequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<BookRequest> getBooks() {
        return books;
    }

    public void setBooks(List<BookRequest> bookRequests) {
        this.books = bookRequests;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
