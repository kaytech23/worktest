package net.djkaytech.demo.hibernate.querries;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

/**
 * Created by PLKASIU on 2017-10-13.
 */
public class TableFillupper {

    public static void main(String[] args) {
        //new TableFillupper().start();
        new TableFillupper().get();
    }

    private void get() {
        SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String query = "SELECT id, name FROM Car as c GROUP BY c.name";
        session.createQuery(query).list();

        //cars.forEach(System.out::println);
        sessionFactory.close();
    }

    private void start() {

        SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        for(int i = 0; i < 100; i++) {
            Car car = new Car();
            car.setId(i + 3);
            car.setName("Name" + (i % 5));
            session.save(car);
        }
        session.getTransaction().commit();
        sessionFactory.close();
    }


    public SessionFactory getSessionFactory() {

        Properties prop = getProperties();

        Configuration configuration = new Configuration()
                .addPackage("net.djkaytech.demo.hibernate.querries")
                .addProperties(prop)
                .addAnnotatedClass(CarRegister.class)
                .addAnnotatedClass(Car.class);

        return configuration.buildSessionFactory();
    }

    public Properties getProperties() {
        Properties prop = new Properties();
        prop.setProperty("hibernate.connection.driver_class", "org.apache.derby.jdbc.EmbeddedDriver");
        prop.setProperty("hibernate.connection.url", "jdbc:derby:c:\\MyDB\\demo;create=true");
        prop.setProperty("hibernate.hbm2ddl.auto", "update");
        prop.setProperty("hibernate.current_session_context_class", "thread");
        prop.setProperty("hibernate.show_sql", "true");
        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyTenSevenDialect");
        return prop;
    }

}
