package de.hda.fbi.db2.stud.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Player class.
 * @author xiaominjin
 * @version 0.10
 */
@Entity
public class Player implements Serializable {
    @Id
    private int playerID;
    public Player() {}

}
