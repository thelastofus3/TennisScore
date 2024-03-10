import com.thelastofus.tennis.model.Match;
import com.thelastofus.tennis.service.score.EPlayer;
import com.thelastofus.tennis.service.score.SetMatchScore;
import com.thelastofus.tennis.service.score.TieBreak;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TieBreakTest {
    @Test
    public void testSevenPointsWinTieBreak() {
        TieBreak tieBreak = new TieBreak();
        Match match = new Match();

        for (int i = 0; i < 7; i++) {
            tieBreak.calculateScore(match, EPlayer.PLAYER_ONE);
        }

        var playerOneTieBreakPoints = match.getMatchScore().getTieBreak().get(EPlayer.PLAYER_ONE.ordinal());
        assertEquals(7, playerOneTieBreakPoints);
    }

    @Test
    public void testTieBreakWinWithTwoPointLead() {
        TieBreak tieBreak = new TieBreak();
        Match match = new Match();
        SetMatchScore setMatchScore = new SetMatchScore();

        for (int i = 0; i < 8; i++) {
            tieBreak.calculateScore(match, EPlayer.PLAYER_ONE);
        }
        for (int i = 0; i < 6; i++) {
            tieBreak.calculateScore(match, EPlayer.PLAYER_TWO);
        }

        var playerOneSets = match.getMatchScore().getSets().get(EPlayer.PLAYER_ONE.ordinal());
        assertEquals(1, playerOneSets);
    }

    @Test
    public void testTieBreakContinuesAtSixSix() {
        TieBreak tieBreak = new TieBreak();
        Match match = new Match();

        for (int i = 0; i < 6; i++) {
            tieBreak.calculateScore(match, EPlayer.PLAYER_ONE);
            tieBreak.calculateScore(match, EPlayer.PLAYER_TWO);
        }

        var playerOneTieBreakPoints = match.getMatchScore().getTieBreak().get(EPlayer.PLAYER_ONE.ordinal());
        var playerTwoTieBreakPoints = match.getMatchScore().getTieBreak().get(EPlayer.PLAYER_TWO.ordinal());
        assertEquals(6, playerOneTieBreakPoints);
        assertEquals(6, playerTwoTieBreakPoints);
    }
}
