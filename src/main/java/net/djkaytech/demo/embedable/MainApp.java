package net.djkaytech.demo.embedable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        try (Session session = factory.openSession()) {

            PhoneNumber phoneNumber = new PhoneNumber(123, "999-432023");
            Person person = new Person("Monika", phoneNumber);
            session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();

            List<Person> personList = session.createQuery("from Person").list();
            personList.forEach(System.out::println);

        }

        factory.close();

    }
}
