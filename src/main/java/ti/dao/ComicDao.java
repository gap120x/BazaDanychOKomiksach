package ti.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ti.model.Comic;
import ti.model.User;
import ti.util.HibernateUtil;

import java.util.List;


public class ComicDao {

    public void saveComic(Comic comic) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the comic object
            session.save(comic);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public List<Comic> getAll() {
        Session session = null;
        List<Comic> comics=null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            comics=session.createQuery("from Comic").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return comics;
    }

    }



