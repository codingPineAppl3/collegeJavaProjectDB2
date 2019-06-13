package de.hda.fbi.db2.stud;
import java.sql.Timestamp;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import de.hda.fbi.db2.stud.entity.*;

/**
 * PlayGame - Player play game here.
 *
 * @author xiaom
 * @version 0.1
 */
public class PlayGame {
    private int categoryID;
    //private int questionID;
    //private Category categories = new Category();
    private static final String PERSISTENCE_UNIT_NAME = "postgresPU";
    private EntityManagerFactory factory;
    private Timestamp ts;
    private Calendar cal = (Calendar) Calendar.getInstance();

    public void playingGame() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager emf = factory.createEntityManager();
        emf.getTransaction().begin();
        try {
            System.out.println("test");


            final Scanner playerName = new Scanner(System.in, "UTF-8");
            System.out.println("Enter player name");
            String playerNm = playerName.next();
            Player player = new Player();
            List<Player> playerList =
                    emf.createQuery("select p from Player p where p.playerName = :playerNm")
                            .setParameter("playerNm", playerNm)
                            .getResultList();

            if (playerList.size() == 0) {
                player.setPlayerName(playerNm);
                emf.persist(player);
            } else {
                player = playerList.get(0);
            }
            //get a list of Categories
            List<Category> showListOfCategory = emf.createQuery("Select c " +
                    "from Category c").getResultList();


            cal.add(Calendar.SECOND, 30);
            ts = new Timestamp(cal.getTime().getTime());
            Game game = new Game();
            game.setGameStartTime(ts);
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
            while (numberOfChosenCategories < 2 || exit != 0) {
                System.out.println("type a category!");
                exit = chooseCategories.nextInt();
                if (exit != 0) {
                    if (playerCategories.contains(exit)) {
                        System.out.println(exit + " has been selected ");
                    } else {
                        playerCategories.add(exit);
                        numberOfChosenCategories++;
                    }
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
            Collections.sort(questionSize); //find minium of availble questions
            int numberOfQuestions = questionSize.get(0);

            System.out.println("number of max questions " + numberOfQuestions);
            System.out.println("Please select number of questions ");
            final Scanner selectNumberofQuestion = new Scanner(System.in, "UTF-8");

            if (selectNumberofQuestion.hasNextInt()) {
                int selectNumber = selectNumberofQuestion.nextInt();
                //check if question number chosen by player is exceed availble questions
                if (selectNumber > numberOfQuestions) {
                    System.out.println("You have selected too many questions, " +
                            numberOfQuestions + " will be accepted ");
                } else {
                    numberOfQuestions = selectNumber;
                }
            }
            int counter = 0;
            int randomQuestion;
            Random randomGenerator = new Random();
            //List<Integer> randomQuestionList = new ArrayList<>();

            while (counter < playerCategories.size()) {
                categoryID = playerCategories.get(counter);
                category = emf.find(Category.class, categoryID);
                //number of availble questions each category
                int availbleQuestion = category.getQuestionList().size();
                boolean doit = true;
                List<Integer> selectedquestion = new ArrayList<Integer>();
                for (int j = 0; j < numberOfQuestions; j++) {
                    randomQuestion = randomGenerator.nextInt(availbleQuestion);
                    // if random generate same number
                    while (doit) {
                        if (selectedquestion.contains(randomQuestion)) {
                            if (randomQuestion == 0) {
                                randomQuestion = randomQuestion + 1;
                            } else {
                                randomQuestion = randomQuestion - 1;
                            }
                        } else {
                            doit = false;
                        }
                    }
                    selectedquestion.add(randomQuestion);
                    doit = true;


                    //get random question from question list
                    Question question = category.getQuestionList().get(randomQuestion);
                    //find question by id in database
                    //question = emf.find(Question.class, randomQuestion);
                    if (question != null) {

                        System.out.println("Question: " + question.getQuestion());
                        System.out.println("1.\t" + question.getA1() + "\t2.\t" + question.getA2()
                                + "\t3.\t" + question.getA3() + "\t4.\t" + question.getA4());
                        //Player enter his answer
                        int answer = 0;
                        final Scanner playerAnswer = new Scanner(System.in, "UTF-8");
                        if (playerAnswer.hasNextInt()) {
                            answer = playerAnswer.nextInt();

                            if (answer == question.getSolution()) {
                                System.out.println("correct");
                            } else {
                                System.out.println("false");
                            }
                        }

                        //game.addQuestions(category.getQuestionFromList(randomQuestion));
                        //game.addAnswerMap(category.getQuestionFromList(randomQuestion).getqId(),
                        // answer);
                        game.addQuestions(question);
                        game.addAnswerMap(question.getqId(), answer);
                    }
                }
                selectedquestion.clear();
                ++counter;
            }
            game.setPlayer(player);
            game.setGameEndTime(ts);
            emf.persist(game);
        } catch (InputMismatchException imex) {
            System.out.println("Exception caught: " + imex);
        }
        emf.getTransaction().commit();
        emf.close();
        factory.close();
    }
}
