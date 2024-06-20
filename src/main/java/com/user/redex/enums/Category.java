package com.user.redex.enums;

import com.google.gson.Gson;

/**
 * @author Nabeel Ahmed
 */
public enum Category {

    FICTION("014-97-98", "Fiction"),
    NO_FICTION("014-97-91", "No Fiction"),
    MYSTERY("014-97-78", "Mystery"),
    SCIENCE_FICTION("014-47-98", "Science Fiction");

    private String code;
    private String name;

    Category(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() { return new Gson().toJson(this); }

}
