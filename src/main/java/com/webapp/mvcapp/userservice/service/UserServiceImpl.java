package com.webapp.mvcapp.userservice.service;

import com.sun.istack.internal.NotNull;
import com.webapp.mvcapp.userservice.datamodel.AppUser;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class UserServiceImpl implements UserService {
    public void connect() {
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        AppUser user = new AppUser();
        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

    private void createUser(String login, String password, String name, long photoId, String description) {
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        AppUser user = new AppUser();
        user = user.builder()
                .login(login)
                .password(password)
                .name(name)
                .photoId(photoId)
                .description(description)
                .build();
        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

    public void register(String login, String password, String name, long photoId) {
        register(login, password, name, photoId, null);
    }

    public void register(String login, String password, String name, long photoId, String description) {
        if (userExist(login)) {
            createUser(login, password, name, photoId, description);
        }
    }

    public void unregister() {

    }

    private boolean userExist(String login) {
        return getUserByLogin(login) != null;
    }

    public void login() {

    }

    public AppUser getUserByLogin(@NotNull final String login) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();


        Criteria criteria = session.createCriteria(AppUser.class);
        AppUser user = (AppUser) criteria.add(Restrictions.eq("login", login))
                .uniqueResult();

        return user;
    }
}