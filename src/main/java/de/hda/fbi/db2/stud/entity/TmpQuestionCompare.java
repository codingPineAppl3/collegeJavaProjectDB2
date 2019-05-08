package de.hda.fbi.db2.stud.entity;

import java.util.Comparator;
import javax.persistence.*;

/**
 * tmporary Class for sort by Category.
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

    public String getQuestion() {
        return question;
    }

    public String getA1() {
        return a1;
    }

    public String getA2() {
        return a2;
    }

    public String getA3() {
        return a3;
    }

    public String getA4() {
        return a4;
    }

    public int getCAnswer() {
        return canswer;
    }

    public String getCategory() {
        return category;
    }

    public static final Comparator<TmpQuestionCompare>
            SORTBYCATEGORY = new Comparator<TmpQuestionCompare>(){
      @Override
      public int compare(TmpQuestionCompare obj1, TmpQuestionCompare obj2){
          return obj1.category.compareTo(obj2.category);
      }
    };

}
