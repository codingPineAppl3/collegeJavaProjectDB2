package de.hda.fbi.db2.stud;
//import de.hda.fbi.db2.stud.entity.Player;
//import org.apache.commons.lang.RandomStringUtils;
//import java.util.*;
import javax.persistence.*;


/**
 * Generate Game Data.
 *
 * @author xiaom
 * @version 1.01
 */
public class GenerateGameData {
    //private int categoryIdNew;
    //private int categoryIdOld;
    //private static final String PERSISTENCE_UNIT_NAME = "postgresPU";
    //private static EntityManagerFactory factory;

    public void generatingData() {
        /*factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager emf = factory.createEntityManager();
        emf.getTransaction().begin();

        //"TODO(xiaominjin): change player range to 10.000 after tests"
        for (int i = 0; i < 10; i++) {
            //Generate Player by name
            //System.out.println("Generate Player");  //test
            String generatedName = RandomStringUtils.randomAlphabetic(10);
            System.out.println("Generate Player" + generatedName);
            Player player = new Player();
            player.setPlayerName(generatedName);
            emf.persist(player);

            //generate game
            //"TODO(xiaominjin): change game range to 100 after tests"
            for (int j = 0; j < 10; j++) {
                Random randomCategory = new Random();
                categoryIdNew = randomCategory.nextInt(51) + 1;
                categoryIdOld = randomCategory.nextInt(51) + 1;

                if (categoryIdNew == categoryIdOld) {
                    if (categoryIdNew > 51) {
                        categoryIdNew = categoryIdOld - 1;
                    } else  {
                        categoryIdNew = categoryIdOld + 1;
                    }
                }
            }
        }*/
    }
}
