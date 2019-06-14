package de.hda.fbi.db2.stud.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.*;

import oracle.sql.TIMESTAMP;

/**
 * Game class.
 *
 * @author Xiaomin Jin
 * @version 0.10
 */
@Entity
@Table(name = "Game", schema = "wissensdatenbank")

public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            //       @GeneratedValue(strategy = GenerationType.SEQUENCE,
     //       generator = "wissensdatenbank.game_seq")
    private int gameID;
    @Column(name = "gameStartTime", updatable = false)
    private Timestamp gameStartTime;
    @Column(name = "gameEndTime", updatable = false)
    private Timestamp gameEndTime;
    //private List<Category> categories = new ArrayList<>();
    @OneToMany
    private List<Question> questions = new ArrayList<>();
    @ElementCollection
    @MapKeyColumn(name = "question")
    @Column(name = "answer")
    @OneToMany
    private Map<Integer, Integer> answerMap = new HashMap<Integer, Integer>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playerID")
    private Player player1;
    //private int countCorrectAnswers = 0;

    public Game() {
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public Player getPlayer() {
        return player1;
    }

    public void setPlayer(Player player) {
        this.player1 = player;
    }

    public Timestamp getGameStartTime() {
        Timestamp ts = new Timestamp(gameStartTime.getTime());
        return ts;
    }


    public void setGameStartTime(Timestamp ts) {
        this.gameStartTime = new Timestamp(ts.getTime());
    }

    public Timestamp getGameEndTime() {
        Timestamp ts = new Timestamp(gameEndTime.getTime());
        return ts;
    }

    public void setGameEndTime(Timestamp ts) {
        this.gameEndTime = new Timestamp(ts.getTime());
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestions(Question questions) {
        this.questions.add(questions);
    }

    public Map<Integer, Integer> getAnswerMap() {
        return answerMap;
    }

    public void addAnswerMap(int key, int value) {
        answerMap.put(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Game)) {
            return false;
        }
        Game game = (Game) o;
        return getGameID() == game.getGameID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameID());
    }
}


