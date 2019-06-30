package ua.dovhopoliuk.springtask.exceptions;

public class NoSuchUserException extends RuntimeException {
    private String login;

    public NoSuchUserException(String message, String login) {
        super(message);
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
