package com.thelastofus.tennis.model;

import com.thelastofus.tennis.dto.MatchScoreDTO;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "Player1")
    Player playerOne;
    @ManyToOne
    @JoinColumn(name="Player2")
    Player playerTwo;
    @ManyToOne
    @Builder.Default
    @JoinColumn(name = "Winner")
    Player winner = new Player();
    @Transient
    @Builder.Default
    MatchScoreDTO matchScore = new MatchScoreDTO();

    public Match(Player playerOne, Player getPlayerTwo, Player winner) {
        this.playerOne = playerOne;
        this.playerTwo = getPlayerTwo;
        this.winner = winner;
    }
    public Match(Player playerOne, Player getPlayerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = getPlayerTwo;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", playerOne=" + playerOne +
                ", playerTwo=" + playerTwo +
                ", winner=" + winner +
                ", matchScore=" + matchScore +
                '}';
    }
}
