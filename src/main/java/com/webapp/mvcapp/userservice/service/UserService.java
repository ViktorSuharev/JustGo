package com.webapp.mvcapp.userservice.service;

import com.webapp.mvcapp.userservice.datamodel.AppUser;

public interface UserService {

    boolean register(String login, String password, String name, long photoId);

    boolean register(String login, String password, String name, long photoId, String description);

    AppUser login(String login, String password);

    boolean unregister(String login);
}
