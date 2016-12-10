package Model;

import Classes.TAdministratorEntity;
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
public class TAdministratorModel {

    private static SessionFactory factory;

    public TAdministratorModel(){
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /* Method to CREATE an employee in the database */
    public Integer addAdministrator(TAdministratorEntity Administrator){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer AdministratorID = null;
        try{
            tx = session.beginTransaction();
            AdministratorID = (Integer) session.save(Administrator);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return AdministratorID;
    }
    /* Method to  READ all the employees */
    public List<TAdministratorEntity> listAdministrators( ){
        Session session = factory.openSession();
        Transaction tx = null;
        List<TAdministratorEntity> res = new LinkedList<>();
        try{
            tx = session.beginTransaction();
            List Administrators = session.createQuery("FROM TAdministratorEntity ").list();
            for (Iterator iterator = Administrators.iterator(); iterator.hasNext();){
                TAdministratorEntity Administrator = (TAdministratorEntity) iterator.next();
                res.add(Administrator);
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
    public void updateAdministrator(Integer id, TAdministratorEntity Administrator){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Administrator.setIdAdmin(id);
            session.update(Administrator);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to DELETE an employee from the records */
    public void deleteAdministrator(Integer id){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            TAdministratorEntity Administrator = (TAdministratorEntity)session.get(TAdministratorEntity.class, id);
            session.delete(Administrator);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public TAdministratorEntity getAdminById(int idAdmin) {
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            TAdministratorEntity Administrator = (TAdministratorEntity)session.get(TAdministratorEntity.class, idAdmin);
            tx.commit();
            return Administrator;
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
