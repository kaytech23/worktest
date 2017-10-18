package net.djkaytech.demo.hibernate.embedable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EmbTestApp {

    public static void main(String[] args) {
        EmbTestApp app = new EmbTestApp();
        app.start();
    }

    private void start() {

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        try(Session session = factory.openSession()) {

            session.beginTransaction();
            EmbId id = new EmbId();
            id.setId(1);
            EmbTest embTest = new EmbTest();
            embTest.setId(id);
            embTest.setName("Janusz");
            session.save(embTest);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }

    }
}
