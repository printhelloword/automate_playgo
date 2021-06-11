package bot.playgo.Utility;

import bot.playgo.Model.Inbox;
import bot.playgo.MlbbApplication;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class DBUtilInboxes {

    private static final SessionFactory factory= new Configuration().configure().buildSessionFactory();

    public static Integer saveInbox(Inbox inbox) {
        Integer generatedID=null;

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.persist(inbox);
            tx.commit();
            generatedID = inbox.getId();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return generatedID;
    }

    public static boolean isTrxIdExists(String trxId) {
        Session session = factory.openSession();
        boolean result=false;

        try {
            Query query = session.
                    createSQLQuery("select * from inboxes where trx_id=:trxId");
            query.setParameter("trxId", trxId);
            query.setMaxResults(1);

            List<Object[]> results = query.getResultList();
            if (!results.isEmpty()) {
                result=true;
            }
        }
        catch(Exception e) {
            MlbbApplication.logger.info(e.getMessage());
        }
        finally {
            session.close();
        }
        return result;
    }

}
