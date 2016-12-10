package Model;

import Classes.TOptionEntity;
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
public class TOptionModel {

    private static SessionFactory factory;

    public TOptionModel(){
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /* Method to CREATE an employee in the database */
    public Integer addOption(TOptionEntity Option){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer OptionID = null;
        try{
            tx = session.beginTransaction();
            OptionID = (Integer) session.save(Option);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return OptionID;
    }
    /* Method to  READ all the employees */
    public List<TOptionEntity> listOptions( ){
        Session session = factory.openSession();
        Transaction tx = null;
        List<TOptionEntity> res = new LinkedList<>();
        try{
            tx = session.beginTransaction();
            List Options = session.createQuery("FROM TOptionEntity ").list();
            for (Iterator iterator = Options.iterator(); iterator.hasNext();){
                TOptionEntity Option = (TOptionEntity) iterator.next();
                res.add(Option);
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
    public void updateOption(Integer id, TOptionEntity Option){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Option.setIdOption(id);
            session.update(Option);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to DELETE an employee from the records */
    public void deleteOption(Integer id){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            TOptionEntity Option = (TOptionEntity)session.get(TOptionEntity.class, id);
            session.delete(Option);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    
    public TOptionEntity getOption(Integer id){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            TOptionEntity Option = (TOptionEntity)session.get(TOptionEntity.class, id);
            return Option;
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }


    public List getOptionsByBallot(Integer id) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<TOptionEntity> res = new LinkedList<>();
        try{
            tx = session.beginTransaction();
            List Options = session.createQuery("FROM TOptionEntity where tBallotByIdBallot = :id").setInteger("id",id).list();
            for (Iterator iterator = Options.iterator(); iterator.hasNext();){
                TOptionEntity Option = (TOptionEntity) iterator.next();
                res.add(Option);
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
