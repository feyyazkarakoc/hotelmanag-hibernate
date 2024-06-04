package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class RoomRepository {

    private Session session;


    public void save(Room room){
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(room);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
    }

    public Room findById(Long roomId){

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(Room.class,roomId);
        } catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }



    public List<Room>  findAll(){

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.createQuery("FROM Room").getResultList();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }

        return null;


    }


    public List<Room> getAll() {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            List<Room> rooms = session.createQuery("FROM Room", Room.class).getResultList();
            return rooms;
        } catch (HibernateException e) {
           e.printStackTrace();
            return null;
        }finally {
            HibernateUtils.closeSession(session);

        }
    }
}
