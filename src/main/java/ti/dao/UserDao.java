package ti.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import ti.model.Comic;
import ti.model.User;
import ti.util.HibernateUtil;

import java.util.List;


public class UserDao {

    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the user object
            session.save(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public User getUserByUsername(String user_username){

        Session session = null;
        User user = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Query<User> query = session.createQuery("from User u where u.username=:username");
            query.setParameter("username", user_username);
            user = query.uniqueResult();


            //user =  (User) session.get(User.class, user_username);
            Hibernate.initialize(user);


        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return user;
    }


    public User getUserByEmail(String user_username){

        Session session = null;
        User user = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Query<User> query = session.createQuery("from User u where u.email=:email");
            query.setParameter("email", user_username);
            user = query.uniqueResult();


            //user =  (User) session.get(User.class, user_username);
            Hibernate.initialize(user);


        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return user;
    }



    public User getUserById(int user_id) {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user =  (User) session.get(User.class, user_id);
            Hibernate.initialize(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }
    public void deleteUserById(int id)
    {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a persistent object
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public List<User> getAll() {
        Session session = null;
        List<User> user=null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            user=session.createQuery("from User").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }


    public void updateUser(User user){

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the user object
            session.evict(user);
            session.update(user);
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



