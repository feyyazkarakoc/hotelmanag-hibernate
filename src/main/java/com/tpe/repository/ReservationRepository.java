package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Reservation;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class ReservationRepository {

    private Session session;


    public Reservation findById(Long id) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(Reservation.class,id);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;

    }

    public List<Reservation> findAll() {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            List<Reservation> reservationList = session.createQuery("FROM Reservation",Reservation.class).getResultList();
            return reservationList;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }
}
