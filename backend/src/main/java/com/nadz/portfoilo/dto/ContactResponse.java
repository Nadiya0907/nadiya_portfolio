package com.nadz.portfolio.dto;

public class ContactResponse {
    private boolean success;
    private String message;
    private Long id;

    public ContactResponse(boolean success, String message, Long id) {
        this.success = success;
        this.message = message;
        this.id = id;
    }
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public Long getId() { return id; }
}
