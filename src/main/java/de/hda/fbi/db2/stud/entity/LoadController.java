package de.hda.fbi.db2.stud.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

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
    public void loadCsvFile(List<TmpQuestionCompare> sortTmpList) {

        factory = Persistence.createEntityManagerFactory(PU);
        EntityManager emf = factory.createEntityManager();

        System.out.println("Transaction begin");
        emf.getTransaction().begin();
        for (TmpQuestionCompare tqc : sortTmpList) {

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
                emf.persist(category);
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
            emf.persist(question);
            tmpQuestionL.add(question);

            setCategory.add(tqc.getCategory());  //ignore category dublicates
        }
        Category category = new Category();
        category.setName(tmpCat);
        category.setQuestionList(tmpQuestionL);
        category.printCategory();     //for tests
        emf.persist(category);

        emf.getTransaction().commit();
        emf.close();
        factory.close();
        System.out.println("Number of Categories: " + setCategory.size());
    }

    public LoadController() {

    }
}
