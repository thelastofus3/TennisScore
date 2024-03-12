package com.thelastofus.tennis.dao;

import com.thelastofus.tennis.model.Match;
import com.thelastofus.tennis.model.Player;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class MatchDAO {
    private final SessionFactory sessionFactory;
    private final Configuration configuration;

    public MatchDAO() {
        this.configuration = new Configuration().addAnnotatedClass(Match.class).addAnnotatedClass(Player.class);
        this.sessionFactory = configuration.buildSessionFactory();
    }
    public void save(Match match){
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.persist(match);

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
