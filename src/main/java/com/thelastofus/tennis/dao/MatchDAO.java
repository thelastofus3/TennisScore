package com.thelastofus.tennis.dao;

import com.thelastofus.tennis.model.Match;
import com.thelastofus.tennis.model.Player;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

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
            throw new Error("Hibernate Error Match: " + e.getMessage());
        } catch (Exception e) {
            throw new Error("Exception Match: " + e.getMessage());
        }
    }
    public List<Match> getMatchesForPage(int page) {
        List<Match> matches ;
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("from Match");
            query.setFirstResult((page-1) * 5);
            query.setMaxResults(5);

            matches = query.list();

            session.getTransaction().commit();
        }catch (HibernateException e) {
            throw new Error("Hibernate Error Match: " + e.getMessage());
        } catch (Exception e) {
            throw new Error("Exception Match: " + e.getMessage());
        }
        return matches;
    }
    public List<Match> getPlayerMatches(int page, String playerName) {
        List<Match> matches ;
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("from Match m where" +
                    " m.playerOne.name = :playerName or m.playerTwo.name = :playerName");
            query.setParameter("playerName", playerName);
            query.setFirstResult((page-1) * 5);
            query.setMaxResults(5);

            matches = query.list();

            session.getTransaction().commit();
        }catch (HibernateException e) {
            throw new Error("Hibernate Error Match: " + e.getMessage());
        } catch (Exception e) {
            throw new Error("Exception Match: " + e.getMessage());
        }
        return matches;
    }
    public long getNumberOfElement() {
        long count ;
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            count = (Long)session.createQuery("select count(m.id) from Match m").uniqueResult();

            session.getTransaction().commit();
        }catch (HibernateException e) {
            throw new Error("Hibernate Error Match: " + e.getMessage());
        } catch (Exception e) {
            throw new Error("Exception Match: " + e.getMessage());
        }
        return count;
    }
    public long getNumberOfElementForPlayer(String playerName) {
        long count ;
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("select count(m.id) from Match m where" +
                    " m.playerOne.name = :playerName or m.playerTwo.name = :playerName");
            query.setParameter("playerName", playerName);
            count = (Long) query.uniqueResult();

            session.getTransaction().commit();
        }catch (HibernateException e) {
            throw new Error("Hibernate Error Match: " + e.getMessage());
        } catch (Exception e) {
            throw new Error("Exception Match: " + e.getMessage());
        }
        return count;
    }
}