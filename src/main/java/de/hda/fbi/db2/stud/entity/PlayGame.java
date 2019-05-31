package de.hda.fbi.db2.stud.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * PlayGame - Player play game here.
 *
 * @author xiaom
 * @version 0.1
 */
public class PlayGame {
    private int categoryID;
    private Player player = new Player();
    //private Game game = new Game();
    //private Category categories = new Category();
    private static final String PERSISTENCE_UNIT_NAME = "postgresPU";
    private EntityManagerFactory factory;

    public void playingGame() {
        try {
            System.out.println("test");
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emf = factory.createEntityManager();
            emf.getTransaction().begin();

            final Scanner playerName = new Scanner(System.in, "UTF-8");
            System.out.println("Enter player name");
            player.setPlayerName(playerName.next());
            emf.persist(player);

            //get a list of Categories
            List<Category> showListOfCategory = emf.createQuery("Select c " +
                    "from Category c").getResultList();

            for (Iterator i = showListOfCategory.iterator(); i.hasNext(); ) {
                Category c = (Category) i.next();
                System.out.println(c.getCategoryID() + "\t" + c.getName());
            }

            System.out.println("Choose at least 2 categories!");
            final Scanner chooseCategories = new Scanner(System.in, "UTF-8");
            int numberOfChosenCategories = 0;
            List<Integer> playerCategories = new ArrayList<Integer>();
            //player select categories
            int exit = 1;
            while (numberOfChosenCategories <= 2 || exit != 0) {
                System.out.println("type a category!");
                exit = chooseCategories.nextInt();
                if (exit != 0) {
                    playerCategories.add(exit);
                    numberOfChosenCategories++;
                }

            }
            //Test
            System.out.println("Number of Player Categories: " + playerCategories.size());
            for (int i : playerCategories) {
                System.out.println("Category Numbers: " + i);
            }
            //generate random questions
            System.out.println("Generate Questions: ");
            Category category;
            List<Integer> questionSize = new ArrayList<Integer>();
            for (int i = 0; i < playerCategories.size(); i++) {
                categoryID = playerCategories.get(i);
                category = emf.find(Category.class, categoryID);
                questionSize.add(category.getQuestionList().size());
            }
            Collections.sort(questionSize);
            int NumberOfQuestions = questionSize.get(0);

            System.out.println("number of max questions " + NumberOfQuestions);
            int counter = 0;
            int randomQuestion;
            Random randomGenerator = new Random();
            while (counter < playerCategories.size()) {
                categoryID = playerCategories.get(counter);
                category = emf.find(Category.class, categoryID);
                //number of availble questions each category
                int availbleQuestion = category.getQuestionList().size();
                for (int j = 0; j < NumberOfQuestions; j++) {
                    randomQuestion = randomGenerator.nextInt(availbleQuestion);
                    //get random question from question list
                    Question question = category.getQuestionList().get(randomQuestion);
                    System.out.println("Question: " + question.getQuestion());
                    System.out.println("1.\t" + question.getA1() + "\t2.\t" + question.getA2()
                            + "\t3.\t" + question.getA3() + "\t4.\t" + question.getA4());
                    //Player enter his answer
                    final Scanner playerAnswer = new Scanner(System.in, "UTF-8");
                    if (playerAnswer.nextInt() == question.getSolution()) {
                        System.out.println("correct");
                    } else {
                        System.out.println("false");
                    }
                }
                counter++;
            }
        } catch (InputMismatchException imex) {
            System.out.println("Exception caught: " + imex);
        }
    }
}
