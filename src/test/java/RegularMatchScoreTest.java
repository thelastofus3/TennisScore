import com.thelastofus.tennis.model.Match;
import com.thelastofus.tennis.service.score.EPlayer;
import com.thelastofus.tennis.service.score.RegularGamePlayerPoints;
import com.thelastofus.tennis.service.score.RegularMatchScore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegularMatchScoreTest {
    @Test
    public void fourPointsWin(){
        RegularMatchScore regularMatchScore = new RegularMatchScore();
        Match match = new Match();
        for (int i = 0; i < 3; i++) {
            //40:0
            regularMatchScore.calculateScore(match, EPlayer.PLAYER_ONE);
        }
        //win game
        regularMatchScore.calculateScore(match,EPlayer.PLAYER_ONE);

        var playerOneGames = match.getMatchScore().getGames().get(EPlayer.PLAYER_ONE.ordinal());
        assertEquals(1,playerOneGames);
    }
    @Test
    public void testAdvantage(){
        RegularMatchScore regularMatchScore = new RegularMatchScore();
        Match match = new Match();

        for (int i = 0; i < 3; i++) {
            //both player win 3 points
            //Score 40:40
            regularMatchScore.calculateScore(match, EPlayer.PLAYER_ONE);
            regularMatchScore.calculateScore(match, EPlayer.PLAYER_TWO);
        }

        regularMatchScore.calculateScore(match, EPlayer.PLAYER_ONE);
        var playerOnePoints = match.getMatchScore().getPoints().get(EPlayer.PLAYER_ONE.ordinal());
        //AD:40
        assertEquals(RegularGamePlayerPoints.ADVANTAGE,playerOnePoints);

        regularMatchScore.calculateScore(match, EPlayer.PLAYER_TWO);
        playerOnePoints = match.getMatchScore().getPoints().get(EPlayer.PLAYER_ONE.ordinal());
        //40:40
        assertEquals(RegularGamePlayerPoints.FORTY,playerOnePoints);

        regularMatchScore.calculateScore(match, EPlayer.PLAYER_TWO);
        regularMatchScore.calculateScore(match, EPlayer.PLAYER_TWO);
        var playerTwoGame = match.getMatchScore().getGames().get(EPlayer.PLAYER_TWO.ordinal());
        //player 2 win games
        assertEquals(1,playerTwoGame);
    }
    @Test
    public void twentyFourPointsWinsSixGames(){
        RegularMatchScore regularMatchScore = new RegularMatchScore();
        Match match = new Match();

        for (int i = 0; i < 23 ; i ++){
            regularMatchScore.calculateScore(match, EPlayer.PLAYER_ONE);
        }

        regularMatchScore.calculateScore(match, EPlayer.PLAYER_ONE);
        var playerOneGame = match.getMatchScore().getGames().get(EPlayer.PLAYER_ONE.ordinal());

        assertEquals(6,playerOneGame);
    }
    @Test
    public void testDeuceScore() {
        RegularMatchScore regularMatchScore = new RegularMatchScore();
        Match match = new Match();

        for (int i = 0; i < 3; i++) {
            // оба игрока выигрывают по 3 очка
            regularMatchScore.calculateScore(match, EPlayer.PLAYER_ONE);
            regularMatchScore.calculateScore(match, EPlayer.PLAYER_TWO);
        }

        // один из игроков выигрывает очко при счете 40:40
        regularMatchScore.calculateScore(match, EPlayer.PLAYER_ONE);

        var playerOnePoints = match.getMatchScore().getPoints().get(EPlayer.PLAYER_ONE.ordinal());
        assertEquals(RegularGamePlayerPoints.ADVANTAGE, playerOnePoints);
    }
    @Test
    public void testDeuceToRegularScore() {
        RegularMatchScore regularMatchScore = new RegularMatchScore();
        Match match = new Match();

        for (int i = 0; i < 3; i++) {
            // оба игрока выигрывают по 3 очка
            regularMatchScore.calculateScore(match, EPlayer.PLAYER_ONE);
            regularMatchScore.calculateScore(match, EPlayer.PLAYER_TWO);
        }

        // один из игроков выигрывает очко при счете 40:40
        regularMatchScore.calculateScore(match, EPlayer.PLAYER_ONE);

        // противник выигрывает очко при счете "деюс"
        regularMatchScore.calculateScore(match, EPlayer.PLAYER_TWO);

        var playerOnePoints = match.getMatchScore().getPoints().get(EPlayer.PLAYER_ONE.ordinal());
        var playerTwoPoints = match.getMatchScore().getPoints().get(EPlayer.PLAYER_TWO.ordinal());

        assertEquals(RegularGamePlayerPoints.FORTY, playerOnePoints);
        assertEquals(RegularGamePlayerPoints.FORTY, playerTwoPoints);
    }
}
