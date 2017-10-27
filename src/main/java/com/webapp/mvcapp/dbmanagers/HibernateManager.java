package com.webapp.mvcapp.dbmanagers;

import com.webapp.mvcapp.datamodel.BaseUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateManager {

    public static void main(String[] args) {
        connect();
    }

    public static void connect() {
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        BaseUser user = new BaseUser("firstuser");
        session.save(user);

        session.getTransaction().commit();
        session.close();
    }
}
