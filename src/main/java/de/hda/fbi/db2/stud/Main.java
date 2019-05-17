package de.hda.fbi.db2.stud;

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
    //private static List<TmpQuestionCompare> sortTmpList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello World");
        LoadController lc = new LoadController();
        lc.loadCsvFile();
        //Read (if available) additional csv-files and default csv-file
        //List<String> availableFiles = CsvDataReader.getAvailableFiles();
        /*for (String availableFile: availableFiles){
            final List<String[]> additionalCsvLines = CsvDataReader.read(availableFile);
        }*/
    }

    public String getGreeting() {
        return "app should have a greeting";
    }
}
