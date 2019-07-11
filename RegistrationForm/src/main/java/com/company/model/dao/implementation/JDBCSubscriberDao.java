package com.company.model.dao.implementation;

import com.company.model.LoginNotUniqueException;
import com.company.model.dao.AddressDao;
import com.company.model.dao.SubscriberDao;
import com.company.model.entity.GroupName;
import com.company.model.entity.Subscriber;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class JDBCSubscriberDao implements SubscriberDao {
    private Connection connection;
    private AddressDao addressDao;

    private static final String SQL_INSERT = "INSERT INTO subscribers " +
            "(surname, " +
            "name, " +
            "patronymic, " +
            "login, " +
            "comment, " +
            "group_name, " +
            "home_phone_number, " +
            "mobile_phone_number, " +
            "mobile_phone_number_2, " +
            "email, " +
            "skype_nickname, " +
            "date_of_entry_into_book, " +
            "date_of_last_modification, " +
            "address_id) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM subscribers " +
            "WHERE id = ?";

    private static final String SQL_SELECT_ALL = "SELECT * FROM subscribers";

    private static final String SQL_UPDATE = "UPDATE subscribers SET " +
            "surname = ?, " +
            "name = ?, " +
            "patronymic = ?, " +
            "login = ?, " +
            "comment = ?, " +
            "group_name = ?, " +
            "home_phone_number = ?, " +
            "mobile_phone_number = ?, " +
            "mobile_phone_number_2 = ?, " +
            "email = ?, " +
            "skype_nickname = ?, " +
            "date_of_entry_into_book = ?, " +
            "date_of_last_modification = ?, " +
            "address_id = ?" +
            "WHERE id = ?";

    private static final String SQL_DELETE = "DELETE FROM subscribers " +
            "WHERE id = ?";


    JDBCSubscriberDao(Connection connection, AddressDao addressDao) {
        this.connection = connection;
        this.addressDao = addressDao;
    }

    @Override
    public void create(Subscriber entity){
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)){
            fillPreparedStatement(entity, preparedStatement);

            addressDao.create(entity.getAddress());

            preparedStatement.setInt(14, entity.getAddress().getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new LoginNotUniqueException("Entered login is not unique: ", entity.getLogin());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Subscriber findById(int id) {
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

    @Override
    public List<Subscriber> findAll() {
        List<Subscriber> result = new ArrayList<>();

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
    public void update(Subscriber entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){
            fillPreparedStatement(entity, preparedStatement);
            preparedStatement.setInt(14, entity.getAddress().getId());
            preparedStatement.setInt(15, entity.getId());

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

    private void fillPreparedStatement(Subscriber entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getSurname());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setString(3, entity.getPatronymic());
        preparedStatement.setString(4, entity.getLogin());
        preparedStatement.setString(5, entity.getComment());
        preparedStatement.setString(6, entity.getGroupName().name());
        preparedStatement.setString(7, entity.getHomePhoneNumber());
        preparedStatement.setString(8, entity.getMobilePhoneNumber());
        preparedStatement.setString(9, entity.getMobilePhoneNumber2());
        preparedStatement.setString(10, entity.getEmail());
        preparedStatement.setString(11, entity.getSkypeNickname());
        preparedStatement.setDate(12, Date.valueOf(entity.getDateOfEntryIntoBook()));
        preparedStatement.setDate(13, Date.valueOf(entity.getDateOfLastModification()));
    }

    private Subscriber executeFromResultSet(ResultSet resultSet) throws SQLException{
        Subscriber result = new Subscriber();

        result.setId(resultSet.getInt("id"));
        result.setSurname(resultSet.getString("surname"));
        result.setName(resultSet.getString("name"));
        result.setPatronymic(resultSet.getString("patronymic"));
        result.setLogin(resultSet.getString("login"));
        result.setComment(resultSet.getString("comment"));
        result.setGroupName(GroupName.valueOf(resultSet.getString("group_name")));
        result.setHomePhoneNumber(resultSet.getString("home_phone_number"));
        result.setMobilePhoneNumber(resultSet.getString("mobile_phone_number"));
        result.setMobilePhoneNumber2(resultSet.getString("mobile_phone_number2"));
        result.setEmail(resultSet.getString("email"));
        result.setSkypeNickname(resultSet.getString("skype_nickname"));

        Date dateOfEntryIntoBook = resultSet.getDate("date_of_entry_into_book");
        Date dateOfLastModification = resultSet.getDate("date_of_last_modification");

        result.setDateOfEntryIntoBook(dateOfEntryIntoBook
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        result.setDateOfLastModification(dateOfLastModification
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        result.setAddress(addressDao.findById(resultSet.getInt("address_id")));

        return result;
    }

}
