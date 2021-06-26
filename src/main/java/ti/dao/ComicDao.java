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

    public Comic getComicByTitle(String comic_title){

        Session session = null;
        Comic comic = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Query<Comic> query = session.createQuery("from Comic c where c.title=:title");
            query.setParameter("title", comic_title);
            comic = query.uniqueResult();



            Hibernate.initialize(comic);


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

    public Comic getComicById(int comic_id) {
        Session session = null;
        Comic comic = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            comic =  (Comic) session.get(Comic.class, comic_id);
            Hibernate.initialize(comic);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return comic;
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

    public void updateComic(Comic comic){

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the user object
            session.evict(comic);
            session.update(comic);
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



