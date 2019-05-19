package de.hda.fbi.db2.stud;

import java.nio.charset.Charset;
import java.util.InputMismatchException;
import java.util.Scanner;
import de.hda.fbi.db2.stud.entity.*;

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
        int playerChoice = 0;

        while (playerChoice != 5) {
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
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    LoadController lc = new LoadController();
                    lc.loadCsvFile();
                    break;
                case 5:
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
        System.out.println("\t2. Generate Statistics");
        System.out.println("\t3. Show Statistics");
        System.out.println("\t4. Load CSV-files");
        System.out.println("\t5. Quit");
    }

    public String getGreeting() {
        return "app should have a greeting";
    }
}
