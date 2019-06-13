package de.hda.fbi.db2.stud;

import java.sql.Timestamp;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.hda.fbi.db2.stud.entity.Category;
import de.hda.fbi.db2.stud.entity.Game;
import de.hda.fbi.db2.stud.entity.Player;

/**
 * Generate Data for Game.
 *
 * @author xiaominjin
 * @version 1.01
 */
public class GenerateData {
    private int categoryID;
    private int categoryID2;
    private int numberofQuestion;
    private int whichQuestion;
    private int whichQuestion2;
    private int answer;
    private Timestamp ts;
    private Game game;
    private Random randomGenerator;
    String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";
    private static final String PERSISTENCE_UNIT_NAME = "postgresPU";
    private static EntityManagerFactory factory;

    public void gendata(Calendar cal) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager emf = factory.createEntityManager();
        emf.getTransaction().begin();
        // load player
        for (int i = 0; i < 1000; ++i) {      //0

            StringBuilder generatedString = new StringBuilder(10);
            for (int j = 0; j < 10; j++) {
                int index = (int) (alphaNumericString.length() * Math.random());
                generatedString.append(alphaNumericString.charAt(index));
            }
            Player player = new Player();
            player.setPlayerName(generatedString.toString());
            emf.persist(player);

            // load game
            for (int j = 0; j < 100; ++j) {            //1
                //Random randomGenerator = new Random();
                randomGenerator = new Random();
                categoryID = randomGenerator.nextInt(51) + 1;
                categoryID2 = randomGenerator.nextInt(51) + 1;
                if (categoryID == categoryID2) {          //2
                    if (categoryID > 1) {
                        categoryID2 = categoryID2 - 1;
                    } else {
                        categoryID2 = categoryID2 + 1;
                    }
                }                                        //2
                //Game game = new Game();
                game = new Game();
                ts = new Timestamp(cal.getTime().getTime());
                game.setGameStartTime(ts);
                Category category = emf.find(Category.class, categoryID);
                generateQuestion(category);
                //game.addCategorytoList(category);} //3
                Category category2 = emf.find(Category.class, categoryID2);
                generateQuestion(category2);
                //game.addCategorytoList(category2);}  //4
                cal.add(Calendar.SECOND, 3);
                ts = new Timestamp(cal.getTime().getTime());
                game.setGameEndTime(ts);
                game.setPlayer(player);
                emf.persist(game);

            } //1
        }    //0
        emf.getTransaction().commit();
        emf.close();
        factory.close();

    }

    public void generateQuestion(Category category) {
        if (category != null) {                               //3
            numberofQuestion = category.getQuestionList().size();
            if (numberofQuestion < 2) {
                whichQuestion = 0;
                answer = randomGenerator.nextInt(4) + 1;
                game.addQuestions(category.getQuestionFromList(whichQuestion));
                game.addAnswerMap(category.getQuestionFromList(whichQuestion).getqId(), answer);
            } else {
                //System.out.println("number of question " + numberofQuestion);
                whichQuestion = randomGenerator.nextInt(numberofQuestion);
                answer = randomGenerator.nextInt(4) + 1;
                game.addQuestions(category.getQuestionFromList(whichQuestion));
                game.addAnswerMap(category.getQuestionFromList(whichQuestion).getqId(), answer);
                whichQuestion2 = randomGenerator.nextInt(numberofQuestion);
                if (whichQuestion == whichQuestion2) {
                    if (whichQuestion != 0) {
                        whichQuestion2 = whichQuestion2 - 1;
                    } else {
                        whichQuestion2 = whichQuestion2 + 1;
                    }
                }
                answer = randomGenerator.nextInt(4) + 1;
                game.addQuestions(category.getQuestionFromList(whichQuestion2));
                game.addAnswerMap(category.getQuestionFromList(whichQuestion2).getqId(), answer);
            }
        }
    }
}
