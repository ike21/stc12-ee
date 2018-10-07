package ru.innopolis.stc12.servlets.repository.dao;

import ru.innopolis.stc12.servlets.pojo.City;

public interface CityDao {
    boolean addCity(City city);

    City getCityById(int id);

    City getCityByName(String name);

    boolean updateCity(City city);

    boolean deleteCityById(int id);

    boolean deleteCityByName(City city);
}
