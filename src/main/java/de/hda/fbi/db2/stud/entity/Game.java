package de.hda.fbi.db2.stud.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * Game class.
 * @author Xiaomin Jin
 * @version 0.10
 */
@Entity
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private int gameID;
    @Column(unique = true)
    private String playerName;

    public Game() {
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    //"TODO(xiaominjin): come back later when DB connection issue is resolved.
    //"TODO(xiaominjin): Will replace that giant code block in main."
    /*public void readCsvLine(@NotNull final String[] line) {
        boolean exists = true;
        final Map<String, String> mymap;
        final Integer questionID = Integer.parseInt(line[0]);
        final String questionText = line[1];
        final String answers1 = line[2];
        final String answers2 = line[3];
        final String answers3 = line[4];
        final String answers4 = line[5];
        final Integer solution = Integer.parseInt(line[6]);
        final String category = line[7];

        final ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer(answers1));
        answers.add(new Answer(answers2));
        answers.add(new Answer(answers3));
        answers.add(new Answer(answers4));

        final Question question = new Question(questionID, questionText, answers, solution);
        //final Category categories = new Category();
    }*/
}


