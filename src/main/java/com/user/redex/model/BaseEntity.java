package com.user.redex.model;

import com.google.gson.Gson;
import com.user.redex.enums.Status;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

public class BaseEntity {

    @Id
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;

    public BaseEntity() {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
