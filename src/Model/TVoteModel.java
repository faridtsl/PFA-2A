package Model;

import Classes.TVoteEntity;
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
public class TVoteModel {

    private static SessionFactory factory;

    public TVoteModel(){
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /* Method to CREATE an employee in the database */
    public Integer addVote(TVoteEntity Vote){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer VoteID = null;
        try{
            tx = session.beginTransaction();
            VoteID = (Integer) session.save(Vote);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return VoteID;
    }
    /* Method to  READ all the employees */
    public List<TVoteEntity> listVotes( ){
        Session session = factory.openSession();
        Transaction tx = null;
        List<TVoteEntity> res = new LinkedList<>();
        try{
            tx = session.beginTransaction();
            List Votes = session.createQuery("FROM TVoteEntity ").list();
            for (Iterator iterator = Votes.iterator(); iterator.hasNext();){
                TVoteEntity Vote = (TVoteEntity) iterator.next();
                res.add(Vote);
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
    /* Method to UPDATE salary for an employee */
    public void updateVote(Integer id, TVoteEntity Vote){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Vote.setIdVote(id);
            session.update(Vote);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to DELETE an employee from the records */
    public void deleteVote(Integer id){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            TVoteEntity Vote = (TVoteEntity)session.get(TVoteEntity.class, id);
            session.delete(Vote);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
