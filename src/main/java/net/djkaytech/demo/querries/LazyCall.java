package net.djkaytech.demo.querries;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Properties;

public class LazyCall {

    public static void main(String[] args) throws InterruptedException {
        new LazyCall().start();
    }

    private void start() throws InterruptedException {

        List<CarRegister> carRegisters = null;
        try(SessionFactory factory = getSessionFactory();
            Session session = factory.openSession()) {
            session.beginTransaction();
            carRegisters = session.createQuery("from CarRegister").list();
        }

        Thread.sleep(100);
        carRegisters.forEach(cr -> cr.getCars().forEach(System.out::println));
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
        prop.setProperty("hibernate.connection.url", "jdbc:derby:c:\\MyDB\\demo;create=true");
        prop.setProperty("hibernate.hbm2ddl.auto", "update");
        prop.setProperty("show_sql", "true");
        prop.setProperty("dialect", "org.hibernate.dialect.DerbyTenSevenDialect");
        return prop;
    }
}

