package de.hda.fbi.db2.stud.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import oracle.sql.TIMESTAMP;

/**
 * Game class.
 * @author Xiaomin Jin
 * @version 0.10
 */
@Entity
@Table(name = "Game", schema = "wissensdatenbank")
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_seq")
    private int gameID;

    private TIMESTAMP gameStartTime;
    private TIMESTAMP gameEndTime;
    private List<Category> categories = new ArrayList<>();
    private List<Question> questions = new ArrayList<>();
    private Map<Integer, Integer> answerMap = new HashMap<>();
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

    public TIMESTAMP getGameStartTime() {
        return gameStartTime;
    }

    public void setGameStartTime(TIMESTAMP gameStartTime) {
        //"TODO(xiaominjin): change to currentTimeMillis()."
        this.gameStartTime = gameStartTime;
    }

    public TIMESTAMP getGameEndTime() {
        return gameEndTime;
    }

    public void setGameEndTime(TIMESTAMP gameEndTime) {
        //"TODO(xiaominjin): change to currentTimeMillis()."
        this.gameEndTime = gameEndTime;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategories(Category categories) {
        this.categories.add(categories);
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

    public void addAnswerMap(int k, int v) {
        this.answerMap.put(k, v);
    }
}


