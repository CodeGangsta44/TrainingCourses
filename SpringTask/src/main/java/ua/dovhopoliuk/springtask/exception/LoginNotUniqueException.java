package ua.dovhopoliuk.springtask.exception;

import ua.dovhopoliuk.springtask.dto.RegNoteDTO;

public class LoginNotUniqueException extends RuntimeException {
    private RegNoteDTO regNoteDTO;
    public LoginNotUniqueException(String message) {
        super(message);
    }

    public RegNoteDTO getRegNoteDTO() {
        return regNoteDTO;
    }

    public void setRegNoteDTO(RegNoteDTO regNoteDTO) {
        this.regNoteDTO = regNoteDTO;
    }
}
