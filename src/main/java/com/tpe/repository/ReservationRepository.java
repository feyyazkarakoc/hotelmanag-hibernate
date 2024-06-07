package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Reservation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReservationRepository {

    private Session session;


    public Reservation findById(Long id) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(Reservation.class, id);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtils.closeSession(session);
        }
        return null;

    }

    public List<Reservation> findAll() {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            List<Reservation> reservationList = session.createQuery("FROM Reservation", Reservation.class).getResultList();
            return reservationList;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }

    public void save(Reservation reservation) {

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(reservation);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public void delete(Reservation reservation) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(reservation);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
