package com.company.model;

import java.util.ArrayList;
import java.util.Date;

public class Model {
    private ArrayList<Subscriber> notebook;

    public Model() {
        notebook = new ArrayList<>();
    }

    public void addRecord(Subscriber record){
        record.setDateOfEntryIntoBook(new Date());

        notebook.add(record);
    }
}
