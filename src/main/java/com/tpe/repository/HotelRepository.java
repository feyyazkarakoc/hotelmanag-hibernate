package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HotelRepository {

    private Session session;

    public void save(Hotel hotel){
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(hotel);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtils.closeSession(session);
        }


    }


    public Hotel findById(Long id){


        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(Hotel.class,id);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }

        return null;

    }

    public List<Hotel> findAll(){
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            List<Hotel> hotelList =  session.createQuery("FROM Hotel").getResultList();
            return hotelList;
        } catch (HibernateException e) {
            e.getMessage();
        }

        return null;
    }
}
