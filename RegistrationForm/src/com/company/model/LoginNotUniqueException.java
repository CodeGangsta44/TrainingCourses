package com.company.model;

public class LoginNotUniqueException extends Exception {
    private String login;

    public LoginNotUniqueException(String message, String login) {
        super(message);
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
