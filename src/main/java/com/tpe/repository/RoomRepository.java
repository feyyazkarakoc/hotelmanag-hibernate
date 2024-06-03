package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;


public class RoomRepository {

    private Session session;


    public void save(Room room){
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.save(room);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
    }

    public Room findRoomById(Long id){

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(Room.class,id);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
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







}
