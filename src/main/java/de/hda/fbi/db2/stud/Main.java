package de.hda.fbi.db2.stud;

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
        //final Scanner nameInput = new Scanner(System.in);
        //Game game = new Game();
        //Player player = new Player();
        //Category category = new Category();
        //GenerateGameData generateGameData = new GenerateGameData();
    //    PlayGame playGame = new PlayGame();
    //    GenerateGameData generate = new GenerateGameData();
    //    generate.generatingData();
        int playerChoice = 0;

        while (playerChoice != 6) {
            showMenu();

            //check if input is an integer
            Boolean scanLoop = true;
            while (scanLoop) {
                try {
                    playerChoice = menuChoice.nextInt();
                    scanLoop = false;
                } catch (InputMismatchException nex) {
                    System.out.println("Please enter a Number: " + nex);
                    menuChoice.nextInt();
                }
            }

            switch (playerChoice) {
                case 1:
                    //System.out.println("Enter Player name: ");
                    //player.setPlayerName(nameInput.next());
                    //System.out.println("Player: " + player.getPlayerName());
                    break;
                case 2:
                    PlayGame playGame = new PlayGame();
                    playGame.playingGame();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    LoadController lc = new LoadController();
                    lc.loadCsvFile();
                    break;
                case 6:
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
        System.out.println("\t1. New Game");
        System.out.println("\t2. Play Game");
        System.out.println("\t3. Generate Statistics");
        System.out.println("\t4. Show Statistics");
        System.out.println("\t5. Load CSV-files");
        System.out.println("\t6. Quit");
    }

    public String getGreeting() {
        return "app should have a greeting";
    }
}
