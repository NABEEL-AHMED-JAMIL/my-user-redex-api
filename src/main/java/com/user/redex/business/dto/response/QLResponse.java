package com.user.redex.business.dto.response;

import com.google.gson.Gson;

/**
 * @author Nabeel Ahmed
 */
public class QLResponse<T> {

    private T data;
    private final String status;
    private final String message;

    public QLResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public QLResponse(String message, String status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}