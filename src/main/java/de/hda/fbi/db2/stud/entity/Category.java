package de.hda.fbi.db2.stud.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Category class.
 * @author Xiaomin Jin
 * @version 0.10
 */
@Entity
@Table(name = "Category")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    //@SequenceGenerator(name = "seq", initialValue = 1, allocationSize = 100)
    @Column(updatable = false, nullable = false)
    private int categoryID;
    @Column(name = "category_name", unique = true)
    private String name;
    @OneToMany (mappedBy = "category", cascade = {CascadeType.ALL})
    //@OneToMany(targetEntity = Question.class)
    @JoinColumn(name = "category_ID")
    private List<Question> questionList = new ArrayList<>();   //all questions in one category
    // "TODO(xiaominjin): maybe implement equals and hash method."

    public Category() {

    }

    /*public Category(String name, List<Question> questionList) {
        this.name = name;
        this.questionList = questionList;
    }

    public void addQuestion(Question newQuestion) {
       questionList.add(newQuestion);
    }*/


    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    public void setQuestionList(List<Question> newQuestionList) {
        this.questionList = newQuestionList;
    }

    /*public List<Question> getQuestionList() {
        return questionList;
    }*/

    public void printCategory() {
        System.out.println("Category: " + getName());
        for (Question q : questionList) {
            System.out.println("Question: " + q.getqId() + ", " + q.getQuestion());
            System.out.println("Answer1: " + q.getA1() + "\tAnswer2: " + q.getA2()
                    + "\tAnswer3: " + q.getA3() + "\tAnswer4: " + q.getA4());
            System.out.println("Solution: " + q.getSolution());
        }
    }

}
