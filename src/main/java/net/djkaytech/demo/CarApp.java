package net.djkaytech.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class CarApp {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Car car = new Car();
            car.setName("Fiat");
            session.save(car);
            session.getTransaction().commit();

            Query<Car> query = session.createQuery("from Car");
            List<Car> carList = session.createQuery("from Car").list();
            carList.forEach(System.out::println);

        }

        sessionFactory.close();
    }
}
