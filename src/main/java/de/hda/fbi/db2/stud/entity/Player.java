package de.hda.fbi.db2.stud.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 * Player class.
 * @author xiaominjin
 * @version 0.10
 */
@Entity
@Table(name = "Player", schema = "wissensdatenbank")
public class Player implements Serializable {
  //  public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq")
    private int playerID;
    @Column(unique = true)
    private String playerName;
    @OneToMany(mappedBy = "player1")
    private List<Game> gameList = new ArrayList<>();

    public Player() {}

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public void addGame(Game game) {
        this.gameList.add(game);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }
        Player player = (Player) o;
        return getPlayerID() == player.getPlayerID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerID());
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerID=" + playerID +
                ", playerName='" + playerName + '\'' +
                ", gameList=" + gameList +
                '}';
    }
}
