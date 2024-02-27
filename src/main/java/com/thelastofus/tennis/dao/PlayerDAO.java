package com.thelastofus.tennis.dao;

import com.thelastofus.tennis.model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerDAO {
    Configuration configuration = new Configuration().addAnnotatedClass(Player.class);
    SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();

    public boolean findByName(Player player){
        boolean exists = false;
        try {
            session.beginTransaction();

            String hql = "FROM Player where name = :playerName";
            Query query = session.createQuery(hql);
            query.setParameter("playerName",player.getName());
            Player result = (Player) query.uniqueResult();

            exists = result != null;

            session.getTransaction().commit();
        }finally {
            sessionFactory.close();
        }
        return exists;
    }
    public void save(Player player){
        try {
            session.beginTransaction();

            session.persist(player);

            session.getTransaction().commit();
        }finally {
            sessionFactory.close();
        }
    }
}
