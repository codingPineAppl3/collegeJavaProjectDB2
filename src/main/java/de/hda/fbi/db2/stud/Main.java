package de.hda.fbi.db2.stud;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * bin groupe 7
 * Main Class.
 * @version 0.1.1
 * @since 0.1.0
 * @author A. Hofmann
 * @author B.-A. Mokroß
 */
public class Main {
    /**
     * Main Method and Entry-Point.
     * @param args Command-Line Arguments.
     */

    public static void main(String[] args) {
        final Scanner menuChoice = new Scanner(System.in, "UTF-8");
        ViewStatistics vStatistics = new ViewStatistics();
        //final Scanner nameInput = new Scanner(System.in);
        //Game game = new Game();
        //Player player = new Player();
        //Category category = new Category();
        //GenerateGameData generateGameData = new GenerateGameData();
    //    PlayGame playGame = new PlayGame();
    //    GenerateGameData generate = new GenerateGameData();
    //    generate.generatingData();
        int playerChoice = 0;

        while (playerChoice != 8) {
            showMenu();

            //check if input is an integer
            Boolean scanLoop = true;
            while (scanLoop) {
                try {
                    playerChoice = menuChoice.nextInt();
                    scanLoop = false;
                } catch (InputMismatchException nex) {
                    System.out.println("InputMismatchException caught: " + nex);
                    System.out.println("Please Enter a Number");
                    menuChoice.nextInt();
                }
            }

            switch (playerChoice) {
                case 1:
                //    GenerateData generateData = new GenerateData();
                //    Calendar newCalendar = (Calendar) Calendar.getInstance();
                //    newCalendar.set(Calendar.MONTH, 5);
                //    newCalendar.set(Calendar.YEAR, 2019);
                //    newCalendar.set(Calendar.DATE, 1);
                //    generateData.gendata(newCalendar);
                    RandomDate randomdate = new RandomDate();
                    randomdate.setCalendar();
                    break;
                case 2:
                    PlayGame playGame = new PlayGame();
                    playGame.playingGame();
                    break;
                case 3:

                    //vStatistics.showPlayer();
                    break;
                case 4:
                    vStatistics.showNumberOfGame();
                    break;
                case 5:
                    vStatistics.selectedCategories();
                    break;
                case 6:
                    //vStatistics.showGame();
                    break;
                case 7:
                    LoadController lc = new LoadController();
                    lc.loadCsvFile();
                    break;
                case 8:
                    System.out.println("Quit Game");
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }

        }
    }

    public static void showMenu() {
        System.out.println("\t\tWissenstest\t\t");
        System.out.println("\t1. Generate Players playing game");
        System.out.println("\t2. Play Game");
        System.out.println("\t3. Show Player Statistics");
        System.out.println("\t4. Show Number of Games");
        System.out.println("\t5. Show Selected Categories");
        System.out.println("\t6. Show Played Games");
        System.out.println("\t7. Load CSV-files");
        System.out.println("\t8. Quit");
    }

    public String getGreeting() {
        return "app should have a greeting";
    }
}
