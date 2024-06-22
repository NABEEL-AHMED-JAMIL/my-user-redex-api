package com.user.redex.business.enums;

import com.google.gson.Gson;

/**
 * @author Nabeel Ahmed
 */
public enum Format {

    HARD_COVER("017-954", "Hard Cover"),
    PAPER_BACK("654-987-44", "Paper Back"),
    EBOOK("147-963", "E-Book");

    // hardcover, paperback, ebook
    private String code;
    private String name;

    Format(String code, String name) {
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
