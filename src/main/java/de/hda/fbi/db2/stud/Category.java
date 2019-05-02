package de.hda.fbi.db2.stud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.persistence.*;

/**
 * Category class.
 * @author Xiaomin Jin
 * @version 0.10
 */
@Entity
@Table(name = "Category", schema = "Category")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private int categoryID;
    @Column(name = "category_name", unique = true)
    private String name;
    @OneToMany (mappedBy = "category_name", cascade = {CascadeType.ALL})
    ArrayList<Question> questionList = new ArrayList<>();   //all questions in one category
    // "TODO(xiaominjin): maybe implement equals and hash method."

    public Category() {

    }

    public Category(String name, ArrayList<Question> questionList) {
        this.name = name;
        this.questionList = questionList;
    }

    public void addQuestion(Question newQuestion) {
       questionList.add(newQuestion);
    }

    //"TODO(xiaominjin): Need to check on this later."
    public void setCategoryID(int newCategoryID) {
        this.categoryID = newCategoryID;
    }
    public int getCategoryID() {
        return categoryID;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    public void setQuestionList(ArrayList<Question> newQuestionList) {
        this.questionList = newQuestionList;
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

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
