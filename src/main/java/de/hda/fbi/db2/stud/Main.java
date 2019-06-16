package de.hda.fbi.db2.stud;

import de.hda.fbi.db2.stud.entity.Game;
import de.hda.fbi.db2.stud.entity.Player;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
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
        //ViewStatistics vStatistics = new ViewStatistics();
        String testStart = "2019.01.01.10.10.00";
        String testEnd = "2019.01.01.23.10.00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime testfrom = LocalDateTime.parse(testStart, formatter);
        LocalDateTime testto = LocalDateTime.parse(testEnd, formatter);
        Timestamp timefrom = Timestamp.valueOf(testfrom);
        Timestamp timeto = Timestamp.valueOf(testto);
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
                    RandomDate randomdate = new RandomDate();
                    randomdate.setCalendar();
                    break;
                case 2:
                    PlayGame playGame = new PlayGame();
                    playGame.playingGame();
                    break;
                case 3:
                    showTimestamp();    //test
                    ViewStatistics viewPlayers = new ViewStatistics();
                    viewPlayers.showPlayer(timefrom, timeto);
                    showTimestamp();    //test
                    break;
                case 4:
                    showTimestamp();    //test
                    ViewStatistics viewNumberOfGames = new ViewStatistics();
                    viewNumberOfGames.showNumberOfGame();
                    showTimestamp();    //test
                    break;
                case 5:
                    showTimestamp();    //test
                    ViewStatistics viewSelectedCategories = new ViewStatistics();
                    viewSelectedCategories.selectedCategories();
                    showTimestamp();    //test
                    break;
                case 6:
                    showTimestamp();    //test
                    ViewStatistics viewGame = new ViewStatistics();
                    viewGame.showGame(20);
                    showTimestamp();    //test
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
        System.out.println("\t3. Show Player");
        System.out.println("\t4. Show Number of Games played");
        System.out.println("\t5. Show Selected Categories");
        System.out.println("\t6. Show Games");
        System.out.println("\t7. Load CSV-files");
        System.out.println("\t8. Quit");
    }

    //for test purpose
    public static void showTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
    }

    public String getGreeting() {
        return "app should have a greeting";
    }
}
