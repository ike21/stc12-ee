package ru.innopolis.stc12.servlets.repository.dao;

import org.apache.log4j.Logger;
import ru.innopolis.stc12.servlets.pojo.User;
import ru.innopolis.stc12.servlets.repository.connectionManager.ConnectionManager;
import ru.innopolis.stc12.servlets.repository.connectionManager.ConnectionManagerJdbcImpl;
import ru.innopolis.stc12.servlets.repository.dao.mappers.SQL;
import ru.innopolis.stc12.servlets.repository.dao.mappers.UserMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();

    @Override
    public User getUserByLogin(String login) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SQL.SELECT_USER_BY_USERNAME)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return UserMapper.getUserFromSet(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public boolean addUser(String login, String password) {
        User user = new User(login, password);
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     SQL.INSERT_USER)) {
            UserMapper.getStateFromUser(statement, user);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }
}
