package ti.dao;
import ti.model.Favorites;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ti.model.Comic;
import ti.model.User;
import ti.util.HibernateUtil;

import java.util.List;

public class FavoritesDao {

    public List<Comic> getComicsByUserId(int userid){

        Session session = null;
        List<Comic> comic=null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            String queryy= "select c from Comic c INNER JOIN c.favorites f WHERE f.user="+userid;
            comic=session.createQuery(queryy).list();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return comic;
    }


    public void saveFavourites(Favorites favorites) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the user object
            session.save(favorites);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }







}
