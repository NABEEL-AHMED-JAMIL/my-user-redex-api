package com.user.redex.enums;

import com.google.gson.Gson;

/**
 * @author Nabeel Ahmed
 */
public enum Language {

    ENGLISH("0148", "English"),
    SPANISH("0149", "Spanish"),
    ARABIC("0177", "Arabic");

    private String code;
    private String name;

    Language(String code, String name) {
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