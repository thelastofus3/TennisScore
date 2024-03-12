package com.thelastofus.tennis.controller;

import com.thelastofus.tennis.dao.MatchDAO;
import com.thelastofus.tennis.dao.PlayerDAO;
import com.thelastofus.tennis.service.MatchScoreCalculationService;
import com.thelastofus.tennis.service.NewMatchService;
import com.thelastofus.tennis.service.OngoingMatchesService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener
public class ContextListenerController implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PlayerDAO playerDAO = new PlayerDAO();
        OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("newMatchService", new NewMatchService(playerDAO,ongoingMatchesService));
        servletContext.setAttribute("onGoingMatchesService", ongoingMatchesService);
        servletContext.setAttribute("matchScoreCalculationService", new MatchScoreCalculationService());
    }
}
