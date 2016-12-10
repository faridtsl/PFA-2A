package Model;

import Classes.TVoterEntity;
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
public class TVoterModel {

    private static SessionFactory factory;

    public TVoterModel(){
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /* Method to CREATE an employee in the database */
    public Integer addVoter(TVoterEntity voter){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer VoterID = null;
        try{
            tx = session.beginTransaction();
            VoterID = (Integer) session.save(voter);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return VoterID;
    }
    /* Method to  READ all the employees */
    public List<TVoterEntity> listVoters( ){
        Session session = factory.openSession();
        Transaction tx = null;
        List<TVoterEntity> res = new LinkedList<>();
        try{
            tx = session.beginTransaction();
            List voters = session.createQuery("FROM TVoterEntity ").list();
            for (Iterator iterator = voters.iterator(); iterator.hasNext();){
                TVoterEntity voter = (TVoterEntity) iterator.next();
                res.add(voter);
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

    /* Method to GET Voter By Cin */
    public TVoterEntity voterByCin(String cin){
        Session session = factory.openSession();
        Transaction tx = null;
        List<TVoterEntity> res = new LinkedList<>();
        try{
            tx = session.beginTransaction();
            List voters = session.createQuery("FROM TVoterEntity where cin = :cin ").setString("cin",cin).list();
            for (Iterator iterator = voters.iterator(); iterator.hasNext();){
                TVoterEntity voter = (TVoterEntity) iterator.next();
                return voter;
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


    /* Method to UPDATE salary for an employee */
    public void updateVoter(Integer id, TVoterEntity voter){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            voter.setIdVoter(id);
            session.update(voter);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to DELETE an employee from the records */
    public void deleteVoter(Integer id){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            TVoterEntity voter = (TVoterEntity)session.get(TVoterEntity.class, id);
            session.delete(voter);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public TVoterEntity getVoterById(int idUser) {
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            TVoterEntity voter = (TVoterEntity)session.get(TVoterEntity.class, idUser);
            tx.commit();
            return voter;
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
