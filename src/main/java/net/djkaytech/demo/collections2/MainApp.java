package net.djkaytech.demo.collections2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class MainApp {

    public static void main(String[] args) {
        new MainApp().start();
    }

    private void start() {



        try(SessionFactory factory = getSessionFactory();
            Session session = factory.openSession()) {
            session.beginTransaction();




            Car car1 = new Car();
            car1.setId(4);
            car1.setName("Frajerari");

            System.out.println(session.contains(car1));

            Car car2 = new Car();
            car2.setId(5);
            car2.setName("Honderari");

            Car car3 = new Car();
            car3.setId(43);
            car3.setName("Fiat");

            CarRegister carRegister = new CarRegister();
            carRegister.addCar(car1);
            carRegister.addCar(car2);

            carRegister.addCar(car3);

            session.save(carRegister);

            session.save(car3);
            session.save(car1);
            session.save(car2);

            session.getTransaction().commit();

            session.beginTransaction();

            session.evict(car1);
            System.out.println(session.contains(car1));
            session.createQuery("from CarRegister").list().forEach(System.out::println);

            session.createQuery("from Car").list().forEach(System.out::println);
            session.getTransaction().commit();
        }

    }

    public SessionFactory getSessionFactory() {

        Properties prop = getProperties();

        Configuration configuration = new Configuration()
                .addPackage("net.djkaytech.demo.collections")
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

