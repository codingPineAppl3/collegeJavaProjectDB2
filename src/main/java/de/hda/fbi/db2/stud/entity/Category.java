package de.hda.fbi.db2.stud.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.CheckReturnValue;
import javax.persistence.*;

/**
 * Category class.
 * @author Xiaomin Jin
 * @version 0.10
 */
@Entity
@Table(name = "Category", schema = "wissensdatenbank")
public class Category implements Serializable {

    @Id
 //   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int categoryID;
    @Column(name = "category_name", unique = true)
    private String name;
    @OneToMany (mappedBy = "category", cascade = {CascadeType.ALL})
    //@OneToMany(targetEntity = Question.class)
    @JoinColumn(name = "category_ID")
    private List<Question> questionList = new ArrayList<>();   //all questions in one category

    public Category() {

    }

    public Category(String name, List<Question> questionList) {
        this.name = name;
        this.questionList = questionList;
    }

    public void addQuestion(Question newQuestion) {
       questionList.add(newQuestion);
    }


    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    public void setQuestionList(List<Question> newQuestionList) {
        this.questionList = newQuestionList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public int getCategoryID() {
        return categoryID;
    }

    /*public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        Category category = (Category) o;
        return getCategoryID() == category.getCategoryID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryID());
    }

    @Override
    //@CheckReturnValue
    public String toString() {

        return "Category{" +
                "categoryID=" + getCategoryID() +
                ", name='" + getName() + "\\'" +
                ", questionList=" + getQuestionList().size() +
                "}";
    }
}
