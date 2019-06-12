package com.company.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class Model {
    private ArrayList<Subscriber> notebook;

    public Model() {
        notebook = new ArrayList<>();
    }

    public void addRecord(Subscriber record) throws LoginNotUniqueException {
        for (Subscriber i : notebook) {
            if (i.getLogin().equals(record.getLogin())){
                throw new LoginNotUniqueException("Entered login is not unique.", record.getLogin());
            }
        }

        record.setDateOfEntryIntoBook(new Date());

        notebook.add(record);
    }
}
