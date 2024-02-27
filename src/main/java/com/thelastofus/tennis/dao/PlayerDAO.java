package com.thelastofus.tennis.dao;

import com.thelastofus.tennis.model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerDAO {
    private final SessionFactory sessionFactory ;
    public PlayerDAO() {
        this.sessionFactory = new Configuration().configure().addAnnotatedClass(Player.class).buildSessionFactory();
    }
    public Optional<Player> findByName(String playerName){
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String hql = "FROM Player where name = :playerName";
            Query<Player> query = session.createQuery(hql, Player.class);
            query.setParameter("playerName",playerName);
            Player result = query.uniqueResult();
            session.getTransaction().commit();
            return Optional.ofNullable(result);
        }
    }
    public void save(Player player){
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.persist(player);

            session.getTransaction().commit();
        }finally {
            sessionFactory.close();
        }
    }
}
