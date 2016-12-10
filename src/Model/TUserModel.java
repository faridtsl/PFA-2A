package Model;

import Classes.TUserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 6/10/16.
 */
public class TUserModel {

    private static SessionFactory factory;

    public TUserModel(){
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /* Method to CREATE an User in the database */
    public Integer addUser(TUserEntity User){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer UserID = null;
        try{
            tx = session.beginTransaction();
            UserID = (Integer) session.save(User);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return UserID;
    }
    /* Method to  READ all the User */
    public List<TUserEntity> listUsers( ){
        Session session = factory.openSession();
        Transaction tx = null;
        List<TUserEntity> res = new LinkedList<>();
        try{
            tx = session.beginTransaction();
            List Users = session.createQuery("FROM TUserEntity ").list();
            for (Iterator iterator = Users.iterator(); iterator.hasNext();){
                TUserEntity User = (TUserEntity) iterator.next();
                res.add(User);
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            return res;
        }
    }

    /* Method to  READ all the User */
    public TUserEntity userByName(String username){
        Session session = factory.openSession();
        Transaction tx = null;
        List<TUserEntity> res = new LinkedList<>();
        try{
            tx = session.beginTransaction();
            List Users = session.createQuery("FROM TUserEntity where username = :username").setString("username",username).list();
            for (Iterator iterator = Users.iterator(); iterator.hasNext();){
                TUserEntity User = (TUserEntity) iterator.next();
                return User;
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    /* Method to UPDATE salary for an User */
    public void updateUser(Integer id, TUserEntity User){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            User.setIdUser(id);
            session.update(User);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to DELETE an User from the records */
    public void deleteUser(Integer id){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            TUserEntity User = (TUserEntity)session.get(TUserEntity.class, id);
            session.delete(User);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
