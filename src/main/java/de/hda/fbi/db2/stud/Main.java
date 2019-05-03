package de.hda.fbi.db2.stud;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

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
    private static ArrayList<Question> tmpQuestionL = new ArrayList<>();
    private static List<TmpQuestionCompare> csvLines = new ArrayList<>();
    //private static EntityManagerFactory factory
    public static void main(String[] args) {
        System.out.println("Hello World");
        Set<String> setCategory = new HashSet<>();
        //EntityManager em = factory.createEntityManager();
        try {
            //final Game g = new Game();
            //int countQuestion = 0;
            //Read default csv
            final List<String[]> defaultCsvLines = CsvDataReader.read();
            if (!(defaultCsvLines == null)) {
                //Question tmpQuestion = new Question();
                int categoryID = 0;

                for (String[] data : defaultCsvLines) {
                    if (categoryID > 0) {
                        TmpQuestionCompare tmpqc = new TmpQuestionCompare(
                                Integer.parseInt(data[0]), data[1], data[2], data[3],
                                data[4], data[5], Integer.parseInt(data[6]), data[7]);
                        csvLines.add(tmpqc);
                    }
                    ++categoryID;
                }
                Collections.sort(csvLines, TmpQuestionCompare.SORTBYCATEGORY); //sort by category*/
                System.out.println("Sorted List of Questions: " + csvLines.size());
                String tmpCat = " ";
                for (TmpQuestionCompare tqc : csvLines) {

                    String newCat = tqc.getCategory();
                    if (tmpCat == " ") {
                        tmpCat = newCat;
                    }
                    if (!newCat.equals(tmpCat)) {
                        Category category = new Category();
                        category.setName(tmpCat);
                        category.setQuestionList(tmpQuestionL);
                        category.printCategory();

                        tmpCat = newCat;
                        //"TODO(xiaominjin): don´t forget to persist category here"
                        tmpQuestionL.clear();
                    }
                    Question question = new Question();
                    question.setqId(tqc.getqId());
                    question.setQuestion(tqc.getQuestion());
                    question.setA1(tqc.getA1());
                    question.setA2(tqc.getA2());
                    question.setA3(tqc.getA3());
                    question.setA4(tqc.getA4());
                    question.setSolution(tqc.getCAnswer());
                    //"TODO(xiaomijin): don´t forget to persist question here"
                    tmpQuestionL.add(question);

                    setCategory.add(tqc.getCategory());  //ignore category dublicates
                }
                Category category = new Category();
                category.setName(tmpCat);
                category.setQuestionList(tmpQuestionL);
                category.printCategory();
                    /*Iterator<String> itc = setCategory.iterator();
                    while (itc.hasNext()) {
                        System.out.println("Category: " + itc.next());
                    }*/

                }

            //"TODO(xiaominjin): Delete after pushing finished Praktikum1"
            System.out.println("Number of Categories: " + setCategory.size());

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
