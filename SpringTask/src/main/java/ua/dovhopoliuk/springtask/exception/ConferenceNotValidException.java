package ua.dovhopoliuk.springtask.exception;

public class ConferenceNotValidException extends RuntimeException {
    public ConferenceNotValidException() {}

    public ConferenceNotValidException(String message) {
        super(message);
    }
}
