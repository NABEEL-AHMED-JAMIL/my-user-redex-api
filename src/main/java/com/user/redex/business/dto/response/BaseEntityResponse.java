package com.user.redex.business.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import java.time.LocalDateTime;

/**
 * @author Nabeel Ahmed
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntityResponse {

    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private GEnum<Integer> status;

    public BaseEntityResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public GEnum<Integer> getStatus() {
        return status;
    }

    public void setStatus(GEnum<Integer> status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
