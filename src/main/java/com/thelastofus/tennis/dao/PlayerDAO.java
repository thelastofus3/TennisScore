package com.thelastofus.tennis.dao;

import com.thelastofus.tennis.model.Player;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerDAO {
    private final SessionFactory sessionFactory ;
    private final Configuration  configuration;
    public PlayerDAO() {
        this.configuration = new Configuration().addAnnotatedClass(Player.class);
        this.sessionFactory = configuration.buildSessionFactory();
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
        }catch (HibernateException e) {
            // Обработка ошибок Hibernate
            e.printStackTrace();
            return Optional.empty();
        } catch (Exception e) {
            // Общая обработка других исключений
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public void save(Player player){
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.persist(player);

            session.getTransaction().commit();
        }catch (HibernateException e) {
            // Обработка ошибок Hibernate
            e.printStackTrace();
        } catch (Exception e) {
            // Общая обработка других исключений
            e.printStackTrace();
        }
    }

}
