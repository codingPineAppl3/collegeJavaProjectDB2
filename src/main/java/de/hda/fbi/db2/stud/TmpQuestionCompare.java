package de.hda.fbi.db2.stud;

import java.util.Comparator;
import javax.persistence.*;

/**
 * @author xiaominjin
 */

@Entity
public class TmpQuestionCompare {
    @Id
    private int qId;
    private String question;
    private String a1;
    private String a2;
    private String a3;
    private String a4;
    private int canswer;
    private String category;


    public TmpQuestionCompare(int qId, String question, String a1, String a2,
                              String a3, String a4, int canswer, String category) {
        this.qId = qId;
        this.question = question;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.canswer = canswer;
        this.category = category;
    }

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public String getA4() {
        return a4;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }

    public int getCAnswer() {
        return canswer;
    }

    public void setCAnswer(int canswer) {
        this.canswer = canswer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static final Comparator<TmpQuestionCompare>
            SORTBYCATEGORY = new Comparator<TmpQuestionCompare>(){
      @Override
      public int compare(TmpQuestionCompare obj1, TmpQuestionCompare obj2){
          return obj1.category.compareTo(obj2.category);
      }
    };
    /*@Override
    public int compareTo(TmpQuestionCompare tmpQC){

        return this.category.compareTo(tmpQC.category);

    }*/

    /*public void printTmpQuestions() {
        System.out.println("Category: " + getCategory());
        System.out.println("Question: " + getQuestion());
        System.out.println("Answers: " + getA1() + ", " + getA2()
                + ", " + getA3() + ", " + getA4());
        System.out.println("Correct Answer: " + getCAnswer());
    }*/

}
