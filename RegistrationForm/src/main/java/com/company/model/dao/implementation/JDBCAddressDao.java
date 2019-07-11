package com.company.model.dao.implementation;

import com.company.model.dao.AddressDao;
import com.company.model.entity.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAddressDao implements AddressDao {

    private Connection connection;

    private static final String SQL_INSERT = "INSERT INTO addresses " +
            "(post_index, city, street, building_number, apartment_number) " +
            "VALUES (?,?,?,?,?)";

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM addresses " +
            "WHERE id = ?";

    private static final String SQL_SELECT_BY_FULL_ADDRESS = "SELECT * FROM addresses WHERE " +
            "post_index = ? AND " +
            "city = ? AND " +
            "street = ? AND " +
            "building_number = ? AND " +
            "apartment_number = ?";

    private static final String SQL_SELECT_ALL = "SELECT * FROM addresses";

    private static final String SQL_UPDATE = "UPDATE addresses SET " +
            "post_index = ?," +
            "city = ?," +
            "street = ?," +
            "building_number = ?," +
            "apartment_number = ?" +
            "WHERE id = ?";

    private static final String SQL_DELETE = "DELETE FROM addresses " +
            "WHERE id = ?";

    JDBCAddressDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Address entity) {
        if (!exists(entity)) {
            insert(entity);
        }
    }

    @Override
    public Address findById(int id) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            return executeFromResultSet(resultSet);


        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean exists(Address entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_FULL_ADDRESS)) {
            preparedStatement.setString(1, entity.getIndex());
            preparedStatement.setString(2, entity.getCity());
            preparedStatement.setString(3, entity.getStreet());
            preparedStatement.setString(4, entity.getBuildingNumber());
            preparedStatement.setString(5, entity.getApartmentNumber());

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.last();
            int rows = resultSet.getRow();

            if (rows == 0) {
                return false;
            }

            entity.setId(resultSet.getInt("id"));

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private void insert(Address entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT,
                Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, entity.getIndex());
            preparedStatement.setString(2, entity.getCity());
            preparedStatement.setString(3, entity.getStreet());
            preparedStatement.setString(4, entity.getBuildingNumber());
            preparedStatement.setString(5, entity.getApartmentNumber());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Address> findAll() {
        List<Address> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(executeFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void update(Address entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){
            preparedStatement.setString(1, entity.getIndex());
            preparedStatement.setString(2, entity.getCity());
            preparedStatement.setString(3, entity.getStreet());
            preparedStatement.setString(4, entity.getBuildingNumber());
            preparedStatement.setString(5, entity.getApartmentNumber());
            preparedStatement.setInt(6, entity.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)){

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    private Address executeFromResultSet(ResultSet resultSet) throws SQLException{
        Address result = new Address();

        result.setId(resultSet.getInt("id"));
        result.setIndex(resultSet.getString("post_index"));
        result.setCity(resultSet.getString("city"));
        result.setStreet(resultSet.getString("street"));
        result.setBuildingNumber(resultSet.getString("building_number"));
        result.setApartmentNumber(resultSet.getString("apartment_number"));

        return result;
    }
}
