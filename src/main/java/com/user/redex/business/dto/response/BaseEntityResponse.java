package com.user.redex.business.dto.response;

import com.google.gson.Gson;
import com.user.redex.business.enums.Status;
import java.time.LocalDateTime;

/**
 * @author Nabeel Ahmed
 */
public class BaseEntityResponse {

    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private GEnum<Status> status;

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

    public GEnum<Status> getStatus() {
        return status;
    }

    public void setStatus(GEnum<Status> status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}