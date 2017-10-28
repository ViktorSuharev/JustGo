package com.webapp.mvcapp.userservice.service;

public interface UserService {

    void register(String login, String password, String name, long photoId);

    void register(String login, String password, String name, long photoId, String description);

    void login();

    void unregister();
}
