package de.hda.fbi.db2.stud;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import javax.persistence.*;

import de.hda.fbi.db2.stud.entity.*;
import de.hda.fbi.db2.tools.CsvDataReader;

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
    private static List<TmpQuestionCompare> sortTmpList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello World");
        LoadController lc = new LoadController();
        try {
            //final Game g = new Game();
            //int countQuestion = 0;
            //Read default csv
            final List<String[]> defaultCsvLines = CsvDataReader.read();
            if (!(defaultCsvLines == null)) {
                //Question tmpQuestion = new Question();
                int categoryID = 0;

                //for better performance in P2 (laoding into the database)
                //the data from .csv file will be sorted by category
                for (String[] data : defaultCsvLines) {
                    if (categoryID > 0) {
                        //To be able to sort, I use the TmpQuestionCompare class as Interface
                        TmpQuestionCompare tmpqc = new TmpQuestionCompare(
                                Integer.parseInt(data[0]), data[1], data[2], data[3],
                                data[4], data[5], Integer.parseInt(data[6]), data[7]);
                        sortTmpList.add(tmpqc);
                    }
                    ++categoryID;
                }
                Collections.sort(sortTmpList, TmpQuestionCompare.SORTBYCATEGORY); //sort by category
                //System.out.println("Sorted List of Questions: " + sortTmpList.size());
                }
                lc.loadCsvFile(sortTmpList);
            //"TODO(xiaominjin): Delete after pushing finished Praktikum1"
            System.out.println("Number of Questions: " + sortTmpList.size());

            //Read (if available) additional csv-files and default csv-file
            //List<String> availableFiles = CsvDataReader.getAvailableFiles();
            /*for (String availableFile: availableFiles){
                final List<String[]> additionalCsvLines = CsvDataReader.read(availableFile);
            }*/
        } catch (URISyntaxException use) {
            System.out.println(use);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public String getGreeting() {
        return "app should have a greeting";
    }
}
