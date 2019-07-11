package com.company.model;

import com.company.model.dao.DaoFactory;
import com.company.model.dao.SubscriberDao;
import com.company.model.dao.implementation.JDBCDaoFactory;
import com.company.model.entity.Subscriber;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


public class Model {
    private DaoFactory daoFactory;
    private SubscriberDao notebook;

    public Model() {
        this.daoFactory = new JDBCDaoFactory();
        this.notebook = daoFactory.createSubscriberDAO();
    }

    public void addRecord(Subscriber record) {
        record.setDateOfEntryIntoBook(LocalDate.now());
        notebook.create(record);
    }
}
