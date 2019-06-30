package ua.dovhopoliuk.springtask.exceptions;

public class LoginNotUniqueException extends RuntimeException {
    private String login;

    public LoginNotUniqueException(String message, String login) {
        super(message);
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
