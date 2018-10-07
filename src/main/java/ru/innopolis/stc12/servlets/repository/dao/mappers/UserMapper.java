package ru.innopolis.stc12.servlets.repository.dao.mappers;

import ru.innopolis.stc12.servlets.pojo.User;
import ru.innopolis.stc12.servlets.service.utils.HashUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    private UserMapper() {
    }

    public static PreparedStatement getStateFromUser(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getName());
        statement.setString(2, HashUtil.stringToMD5(user.getPassword()));
        return statement;
    }

    public static User getUserFromSet(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4));
    }
}
