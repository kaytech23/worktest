package net.djkaytech.demo.one2many;

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

        List<Address> addresses = null;
        try (SessionFactory factory = configuration.buildSessionFactory();
             Session session = factory.openSession()) {
            session.beginTransaction();
            Person person1 = new Person("Wacek");
            Person person2 = new Person("Marysia");
            Person person3 = new Person("Jola");
            Address address = new Address("Starowislna 15");
            address.addPerson(person1);
            address.addPerson(person3);
            address.addPerson(person2);



            session.save(address);
            session.getTransaction().commit();

            session.beginTransaction();
            addresses = session.createQuery("from Address").list();
        }
        addresses.forEach(System.out::println);
    }
}
