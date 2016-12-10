package Model;

import Classes.TResultEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 6/10/16.
 */
public class TResultModel {

    private static SessionFactory factory;

    public TResultModel(){
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /* Method to CREATE an employee in the database */
    public Integer addResult(TResultEntity Result){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer ResultID = null;
        try{
            tx = session.beginTransaction();
            ResultID = (Integer) session.save(Result);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return ResultID;
    }
    /* Method to  READ all the employees */
    public List<TResultEntity> listResults( ){
        Session session = factory.openSession();
        Transaction tx = null;
        List<TResultEntity> res = new LinkedList<>();
        try{
            tx = session.beginTransaction();
            List Results = session.createQuery("FROM TResultEntity ").list();
            for (Iterator iterator = Results.iterator(); iterator.hasNext();){
                TResultEntity Result = (TResultEntity) iterator.next();
                res.add(Result);
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
    public void updateResult(Integer id, TResultEntity Result){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Result.setIdResult(id);
            session.update(Result);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to DELETE an employee from the records */
    public void deleteResult(Integer id){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            TResultEntity Result = (TResultEntity)session.get(TResultEntity.class, id);
            session.delete(Result);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public List<TResultEntity> getResultsByBallot(int idBallot) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<TResultEntity> res = new LinkedList<>();
        try{
            tx = session.beginTransaction();
            List Results = session.createQuery("FROM TResultEntity where tBallotByIdBallot = :id").setInteger("id",idBallot).list();
            for (Iterator iterator = Results.iterator(); iterator.hasNext();){
                TResultEntity Result = (TResultEntity) iterator.next();
                res.add(Result);
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
}
