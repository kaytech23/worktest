package net.djkaytech.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {

    public static void main(String[] args) throws ClassNotFoundException {

        //Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {

            // --- get All
            //session.createQuery("from Users");
            List<User> users = session.createQuery("from User").list();
            users.forEach(System.out::println);
        }

        try (Session session = sessionFactory.openSession()) {

            // --- save
            session.beginTransaction();
            User user = new User();
            user.setName("Frajerari");
            System.out.println(user);
            session.save(user);
            session.getTransaction().commit();
            System.out.println(user);
        }


        sessionFactory.close();
    }
}
