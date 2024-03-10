import com.thelastofus.tennis.model.Match;
import com.thelastofus.tennis.model.MatchScore;
import com.thelastofus.tennis.service.score.EPlayer;
import com.thelastofus.tennis.service.score.RegularMatchScore;
import com.thelastofus.tennis.service.score.SetMatchScore;
import com.thelastofus.tennis.service.score.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetMatchScoreTest {
    @Test
    public void SixGamesWinsSet() {
        SetMatchScore setMatchScore = new SetMatchScore();
        RegularMatchScore regularMatchScore = new RegularMatchScore();
        Match match = new Match();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) { // выиграть 4 очка в каждой игре
                regularMatchScore.calculateScore(match,EPlayer.PLAYER_ONE);
            }
        }

        setMatchScore.calculateScore(match,EPlayer.PLAYER_ONE);

        var playerOneSets = match.getMatchScore().getSets().get(EPlayer.PLAYER_ONE.ordinal());

        assertEquals(1, playerOneSets);
    }
    @Test
    public void testMatchWin() {
        SetMatchScore setMatchScore = new SetMatchScore();
        RegularMatchScore regularMatchScore = new RegularMatchScore();
        Match match = new Match();
        State state = State.ONGOING;

        for (int i = 0; i < 12; i++) { // выиграть 12 игр, т.е. 2 сета
            for (int j = 0; j < 4; j++) { // выиграть 4 очка в каждой игре
                regularMatchScore.calculateScore(match, EPlayer.PLAYER_ONE);
            }
             state = setMatchScore.calculateScore(match, EPlayer.PLAYER_ONE);
        }

        var playerOneSets = match.getMatchScore().getSets().get(EPlayer.PLAYER_ONE.ordinal());
        assertEquals(2, playerOneSets);
        assertEquals(State.PLAYER_ONE_WON, state);
    }
    @Test
    public void testSevenGamesWinSet() {
        SetMatchScore setMatchScore = new SetMatchScore();
        RegularMatchScore regularMatchScore = new RegularMatchScore();
        Match match = new Match();

        for (int i = 0; i < 7; i++) { // игрок 1 выигрывает 7 игр
            for (int j = 0; j < 4; j++) { // выиграть 4 очка в каждой игре
                regularMatchScore.calculateScore(match, EPlayer.PLAYER_ONE);
            }
        }

        for (int i = 0; i < 5; i++) { // игрок 2 выигрывает 5 игр
            for (int j = 0; j < 4; j++) { // выиграть 4 очка в каждой игре
                regularMatchScore.calculateScore(match, EPlayer.PLAYER_TWO);
            }
        }

        State state = setMatchScore.calculateScore(match, EPlayer.PLAYER_ONE);

        var playerOneSets = match.getMatchScore().getSets().get(EPlayer.PLAYER_ONE.ordinal());
        assertEquals(1, playerOneSets);
        assertEquals(State.ONGOING, state); // матч продолжается, так как нужно выиграть 2 сета для победы в матче
    }
    @Test
    public void testDrawInSet() {
        SetMatchScore setMatchScore = new SetMatchScore();
        RegularMatchScore regularMatchScore = new RegularMatchScore();
        Match match = new Match();

        for (int i = 0; i < 6; i++) { // оба игрока выигрывают по 6 игр
            for (int j = 0; j < 4; j++) { // выиграть 4 очка в каждой игре
                regularMatchScore.calculateScore(match, EPlayer.PLAYER_ONE);
                regularMatchScore.calculateScore(match, EPlayer.PLAYER_TWO);
            }
        }

        State state = setMatchScore.calculateScore(match, EPlayer.PLAYER_ONE);

        var playerOneSets = match.getMatchScore().getSets().get(EPlayer.PLAYER_ONE.ordinal());
        var playerTwoSets = match.getMatchScore().getSets().get(EPlayer.PLAYER_TWO.ordinal());
        assertEquals(0, playerOneSets);
        assertEquals(0, playerTwoSets);
        assertEquals(State.ONGOING, state); // сет продолжается, так как счет 6:6
    }

}
