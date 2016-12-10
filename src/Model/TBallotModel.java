package Model;

import Classes.TBallotEntity;
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
public class TBallotModel {

    private static SessionFactory factory;

    public TBallotModel(){
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /* Method to CREATE an employee in the database */
    public Integer addBallot(TBallotEntity Ballot){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer BallotID = null;
        try{
            tx = session.beginTransaction();
            BallotID = (Integer) session.save(Ballot);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return BallotID;
    }
    /* Method to  READ all the employees */
    public List<TBallotEntity> listBallots( ){
        Session session = factory.openSession();
        Transaction tx = null;
        List<TBallotEntity> res = new LinkedList<>();
        try{
            tx = session.beginTransaction();
            List Ballots = session.createQuery("FROM TBallotEntity ").list();
            for (Iterator iterator = Ballots.iterator(); iterator.hasNext();){
                TBallotEntity Ballot = (TBallotEntity) iterator.next();
                res.add(Ballot);
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
    public void updateBallot(Integer id, TBallotEntity Ballot){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Ballot.setIdBallot(id);
            session.update(Ballot);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to DELETE an employee from the records */
    public void deleteBallot(Integer id){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            TBallotEntity Ballot = (TBallotEntity)session.get(TBallotEntity.class, id);
            session.delete(Ballot);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public TBallotEntity getBallotById(Integer idBallot) {
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            TBallotEntity Ballot = (TBallotEntity)session.get(TBallotEntity.class, idBallot);
            tx.commit();
            return Ballot;
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    public Integer getAdmin(Integer idBallot) {
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            TBallotEntity Ballot = (TBallotEntity)session.get(TBallotEntity.class, idBallot);
            tx.commit();
            return Ballot.gettAdministratorByIdAdmin().getIdAdmin();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
