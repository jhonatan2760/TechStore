package com.jhonatansouza.techstore.controller.response;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String message;
    private String timeStamp;

    public ErrorResponse(String message) {
        this.message = message;
        this.timeStamp = LocalDateTime.now().toString();
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }


}
