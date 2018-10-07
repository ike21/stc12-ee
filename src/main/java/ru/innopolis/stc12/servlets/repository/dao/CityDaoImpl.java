package ru.innopolis.stc12.servlets.repository.dao;

import org.apache.log4j.Logger;
import ru.innopolis.stc12.servlets.pojo.City;
import ru.innopolis.stc12.servlets.repository.connectionManager.ConnectionManager;
import ru.innopolis.stc12.servlets.repository.connectionManager.ConnectionManagerJdbcImpl;
import ru.innopolis.stc12.servlets.repository.dao.mappers.CityMapper;
import ru.innopolis.stc12.servlets.repository.dao.mappers.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDaoImpl implements CityDao {
    static final Logger LOGGER = Logger.getLogger(CityDaoImpl.class);
    private static ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();

    @Override
    public boolean addCity(City city) {
        if (city != null) {
            Connection connection = connectionManager.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    SQL.INSERT_CITIES)
            ) {
                CityMapper.getStateFromCity(statement, city);
                statement.execute();
            } catch (SQLException e) {
                LOGGER.error(e);
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public City getCityById(int id) {
        Connection connection = connectionManager.getConnection();
        City city;
        try (PreparedStatement statement = connection.prepareStatement(
                SQL.SELECT_CITY_BY_ID)
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                city = CityMapper.getCityFromSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
        return city;
    }

    @Override
    public City getCityByName(String name) {
        Connection connection = connectionManager.getConnection();
        City city;
        try (PreparedStatement statement = connection.prepareStatement(
                SQL.SELECT_CITY_BY_NAME)
        ) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                city = CityMapper.getCityFromSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
        return city;
    }

    @Override
    public boolean updateCity(City city) {
        if (city.getId() != 0) {
            Connection connection = connectionManager.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    SQL.UPDATE_CITY_BY_ID)
            ) {
                CityMapper.getStateFromCity(statement, city);
                statement.setInt(3, city.getId());
                statement.execute();
            } catch (SQLException e) {
                LOGGER.error(e);
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteCityById(int id) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                SQL.DELETE_CITY_BY_ID)
        ) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCityByName(City city) {
        if (city.getName() != null) {
            Connection connection = connectionManager.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SQL.DELETE_CITY_BY_NAME)) {
                statement.setString(1, city.getName());
                statement.execute();
            } catch (SQLException e) {
                LOGGER.error(e);
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
