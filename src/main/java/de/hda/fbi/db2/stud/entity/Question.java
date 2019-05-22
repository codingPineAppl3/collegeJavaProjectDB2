package de.hda.fbi.db2.stud.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;


/**
 * Question class.
 * @author Xiaomin Jin
 * @version 0.10
 */
@Entity
@Table(name = "Question", schema = "wissensdatenbank")
public class Question implements Serializable {

    @Id
    @Column(name = "Question_ID")
    private int qId;
    private String question;

    @Column(name = "Answer1")
    private String a1;
    @Column(name = "Answer2")
    private String a2;
    @Column(name = "Answer3")
    private String a3;
    @Column(name = "Answer4")
    private String a4;

    @Column(name = "Correct_Answer")
    private int solution;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    public Question() {

    }

    /*public Question(int qId, String q, String a1, String a2, String a3,
                    String a4, int s) {
        this.qId = qId;
        this.question = q;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.solution = s;
    }*/

    public int getqId() {
        return qId;
    }

    public void setqId(int newQuestionID) {
        this.qId = newQuestionID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String newQuestion) {
        this.question = newQuestion;
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

    public int getSolution() {
        return solution;
    }

    public void setSolution(int solution) {
        this.solution = solution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Question)) {
            return false;
        }
        Question question = (Question) o;
        return getqId() == question.getqId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getqId());
    }

   public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Question{" +
                "qId=" + getqId() +
                ", question='" + getQuestion() + '\'' +
                ", a1='" + getA1() + '\'' +
                ", a2='" + getA2() + '\'' +
                ", a3='" + getA3() + '\'' +
                ", a4='" + getA4() + '\'' +
                ", solution=" + getSolution() +
                ", category=" + getCategory().getName() +
                '}';
    }
}
