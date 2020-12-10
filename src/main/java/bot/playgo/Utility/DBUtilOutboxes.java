package bot.playgo.Utility;

import bot.playgo.Model.Outbox;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DBUtilOutboxes {

    private static final SessionFactory factory= new Configuration().configure().buildSessionFactory();

    public static Integer saveOutbox(Outbox outbox) {
        Integer generatedOutboxId = null;

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.persist(outbox);
            tx.commit();
            generatedOutboxId = outbox.getId();

        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return generatedOutboxId;
    }

}
