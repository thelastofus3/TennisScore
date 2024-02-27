package com.thelastofus.tennis.service;

import com.thelastofus.tennis.dao.PlayerDAO;
import com.thelastofus.tennis.model.Player;

import java.io.FilterOutputStream;

public class NewMatchService {
    public static void startMatch() {
        //

        Player player = new Player("Den");
        PlayerDAO playerDAO = new PlayerDAO();
        boolean result = playerDAO.findByName(player);
        System.out.println(result);

    }

    public static void main(String[] args) {
        startMatch();
    }

}
