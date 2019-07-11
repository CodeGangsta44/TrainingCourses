package com.company.model.dao.implementation;

import com.company.model.dao.AddressDao;
import com.company.model.dao.DaoFactory;
import com.company.model.dao.SubscriberDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    @Override
    public SubscriberDao createSubscriberDAO() {
        return new JDBCSubscriberDao(getConnection(), createAddressDAO());
    }

    @Override
    public AddressDao createAddressDAO() {
        return new JDBCAddressDao(getConnection());
    }

    private Connection getConnection(){
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/registration_form",
                    "root" ,
                    "12345678" );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
