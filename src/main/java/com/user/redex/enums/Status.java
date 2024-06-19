package com.user.redex.enums;

import com.google.gson.Gson;

public enum Status {

    INACTIVE(0, "Inactive"),
    ACTIVE(1, "Active"),
    DELETE(2, "Delete");

    private Integer code;
    private String name;

    Status(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
