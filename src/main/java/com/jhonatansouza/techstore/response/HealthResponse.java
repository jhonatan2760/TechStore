package com.jhonatansouza.techstore.response;

import java.time.LocalDateTime;

public class HealthResponse {

    private String status;
    private String createdAt;

    public HealthResponse() {
        this.status = "UP";
        this.createdAt = LocalDateTime.now().toString();
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
