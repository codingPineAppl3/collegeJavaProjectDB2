package de.hda.fbi.db2.stud.entity;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import javax.persistence.*;
import de.hda.fbi.db2.tools.CsvDataReader;


/**
 * Load from .csl file.
 * @author xiaominjin
 */
public class LoadController {

    private static List<Question> tmpQuestionL = new ArrayList<>();
    private static Set<String> setCategory = new HashSet<>();
    private static final String PU = "postgresPU";
    private EntityManagerFactory factory;
    String tmpCat = " ";

    //public void loadCsvFile(List<TmpQuestionCompare> sortTmpList) {
    public void loadCsvFile() {
        try {
            final List<String[]> defaultCsvLines = CsvDataReader.read();
            defaultCsvLines.remove(0);
            defaultCsvLines.sort((String[] s1, String[] s2)->s1[7].compareTo(s2[7]));

            factory = Persistence.createEntityManagerFactory(PU);
            EntityManager emf = factory.createEntityManager();

            System.out.println("Transaction begin");       //for tests
            emf.getTransaction().begin();
            for (String[] tqc : defaultCsvLines) {

                String newCat = tqc[7];
                if (tmpCat == " ") {
                    tmpCat = newCat;
                }
                if (!newCat.equals(tmpCat)) {
                    Category category = new Category();
                    category.setName(tmpCat);
                    category.setQuestionList(tmpQuestionL);
                    System.out.println(category.toString());
                    tmpCat = newCat;
                    emf.persist(category);
                    tmpQuestionL.clear();
                }
                Question question = new Question();
                question.setqId(Integer.parseInt(tqc[0]));
                question.setQuestion(tqc[1]);
                question.setA1(tqc[2]);
                question.setA2(tqc[3]);
                question.setA3(tqc[4]);
                question.setA4(tqc[5]);
                question.setSolution(Integer.parseInt(tqc[6]));
                emf.persist(question);
                tmpQuestionL.add(question);

                setCategory.add(tqc[7]);
            }
            Category category = new Category();
            category.setName(tmpCat);
            category.setQuestionList(tmpQuestionL);
            System.out.println(category.toString());     //for tests
            emf.persist(category);

            emf.getTransaction().commit();
            emf.close();
            factory.close();
            System.out.println("Number of Categories: " + setCategory.size());

        } catch (URISyntaxException use) {
            System.out.println(use);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

    }
    //Read (if available) additional csv-files and default csv-file
    //List<String> availableFiles = CsvDataReader.getAvailableFiles();
        /*for (String availableFile: availableFiles){
            final List<String[]> additionalCsvLines = CsvDataReader.read(availableFile);
        }*/
    public LoadController() {

    }
}
