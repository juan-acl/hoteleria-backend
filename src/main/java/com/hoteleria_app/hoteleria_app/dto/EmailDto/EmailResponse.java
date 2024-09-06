package com.hoteleria_app.hoteleria_app.dto.EmailDto;

public class EmailResponse {
    private int status;
    private String message;

    public EmailResponse() {
    }

    public EmailResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
