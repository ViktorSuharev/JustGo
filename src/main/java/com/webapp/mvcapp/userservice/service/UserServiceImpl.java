package com.webapp.mvcapp.userservice.service;

import com.sun.istack.internal.NotNull;
import com.webapp.mvcapp.userservice.datamodel.AppUser;
import com.webapp.mvcapp.userservice.utils.HashingUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class UserServiceImpl implements UserService {
    private void createUser(String login, String password, String name, long photoId, String description) {
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hashedPassword = HashingUtil.encrypt(password);

        AppUser user = new AppUser();
        user = user.builder()
                .login(login)
                .password(hashedPassword)
                .name(name)
                .photoId(photoId)
                .description(description)
                .build();
        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

    public boolean register(String login, String password, String name, long photoId) {
        return register(login, password, name, photoId, null);
    }

    public boolean register(String login, String password, String name, long photoId, String description) {
        if (userExist(login)) {
            createUser(login, password, name, photoId, description);
            return true;
        } else {
            return false;
        }
    }

    public AppUser login(String login, String inputPassword) {
        AppUser user = getUserByLogin(login);
        String encryptedPassword = user.getPassword();
        if (!HashingUtil.isPasswordCorrect(encryptedPassword, inputPassword)) {
            return null;
        }

        return user;
    }

    public boolean unregister(String login) {
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        AppUser user = getUserByLogin(login);
        session.delete(user);

        session.getTransaction().commit();
        session.close();

        return true;
    }

    private boolean userExist(String login) {
        return getUserByLogin(login) != null;
    }

    private AppUser getUserByLogin(@NotNull final String login) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();


        Criteria criteria = session.createCriteria(AppUser.class);
        AppUser user = (AppUser) criteria.add(Restrictions.eq("login", login))
                .uniqueResult();

        return user;
    }
}