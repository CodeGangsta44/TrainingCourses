package ua.dovhopoliuk.springtask.exceptions;

import ua.dovhopoliuk.springtask.dto.RegNoteDTO;

public class LoginNotUniqueException extends RuntimeException {
    private RegNoteDTO note;

    public LoginNotUniqueException(String message) {
        super(message);
    }

    public void setNote(RegNoteDTO note) {
        this.note = note;
    }

    public RegNoteDTO getNote() {
        return note;
    }
}
