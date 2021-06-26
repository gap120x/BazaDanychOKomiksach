package ti.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
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

}
