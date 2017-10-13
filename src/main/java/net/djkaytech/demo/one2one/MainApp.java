package net.djkaytech.demo.one2one;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Properties;

/**
 * Created by PLKASIU on 2017-10-11.
 */
public class MainApp {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", "jdbc:derby:c:\\MyDB\\demo;create=true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("show_sql", "true");
        properties.setProperty("dialect", "org.hibernate.dialect.DerbyTenSevenDialect");

        configuration.addProperties(properties)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Address.class);

        List<Person> people = null;
        try (SessionFactory factory = configuration.buildSessionFactory();
             Session session = factory.openSession()) {
            session.beginTransaction();
            Address address = new Address("Starowislna 15");
            Person person = new Person("Wacek", address);

            session.save(person);
            session.getTransaction().commit();

            session.beginTransaction();
            people = session.createQuery("from Person").list();
        }
        people.forEach(System.out::println);
    }
}
