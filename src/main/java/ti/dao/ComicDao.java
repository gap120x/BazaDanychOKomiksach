package ti.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ti.model.Comic;
import ti.model.User;
import ti.util.HibernateUtil;

import java.util.List;


public class ComicDao {


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



