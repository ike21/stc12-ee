package ru.innopolis.stc12.servlets.service;

public interface UserService {
    int getRole(String login);

    boolean checkAuth(String login, String password);

    boolean addUser(String login, String password);
}
