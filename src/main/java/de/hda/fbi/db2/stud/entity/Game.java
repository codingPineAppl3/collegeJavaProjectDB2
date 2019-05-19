package de.hda.fbi.db2.stud.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


/**
 * Game class.
 * @author Xiaomin Jin
 * @version 0.10
 */
@Entity
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_seq")
    private int gameID;

    public Game() {
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }




}


