package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Guest;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GuestRepository {


    private Session session;

    public Guest findById(Long id) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(Guest.class, id);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }

    public List<Guest> findAll() {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            List<Guest> guestList = session.createQuery("FROM Guest", Guest.class).getResultList();
            return guestList;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtils.closeSession(session);
        }
        return null;

    }

    public void save(Guest guest) {

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(guest);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public void delete(Guest guest) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(guest);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtils.closeSession(session);
        }


    }
}
