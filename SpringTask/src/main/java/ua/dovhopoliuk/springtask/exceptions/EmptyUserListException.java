package ua.dovhopoliuk.springtask.exceptions;


public class EmptyUserListException extends RuntimeException {

    public EmptyUserListException(String message) {
        super(message);
    }

}