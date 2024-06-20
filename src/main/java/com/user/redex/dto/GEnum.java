package com.user.redex.dto;

import com.google.gson.Gson;

/**
 * @author Nabeel Ahmed
 * C stand for Code
 */
public class GEnum<C> {

    private C code;
    private String name;
    private Enum value;

    public GEnum() {
    }

    public GEnum(C code, String name, Enum value) {
        this.code = code;
        this.name = name;
        this.value = value;
    }

    public C getCode() {
        return code;
    }

    public void setCode(C code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enum getValue() {
        return value;
    }

    public void setValue(Enum value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
