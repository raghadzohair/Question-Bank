/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionbank;

import java.io.PrintWriter;

/**
 *
 * @author Raghad
 */
public class Question {

    private int QID;
    private String description;
    private int topicNo;
    private int difficultyLevel;
    private int assignedMarks;
    private Answer[] choices;
    private int noAnswers;
    private static int noOfQuestions = 0;

    public Question(String description, Answer[] choices, int id, int topic, int diff, int marks) {
        this.description = description;
        QID = id;
        topicNo = topic;
        difficultyLevel = diff;
        assignedMarks = marks;
        this.choices = new Answer[choices.length];
        for (int i = 0; i < choices.length; i++) {
            this.choices[i] = choices[i];
        }
    }

    public Question() {
        choices = new Answer[5];
        noOfQuestions++;
        QID = noOfQuestions;
    }

    /*public void setQID(int id){
        QID = id;
    }*/
    public int getQID() {
        return QID;
    }

    public void setQID(int id) {
        QID = id;
    }

    public void setDescription(String desc) {
        description = desc;
    }

    public String getDescription() {
        return description;
    }

    public void setTopicNo(int tno) {
        topicNo = tno;
    }

    public int getTopicNo() {
        return topicNo;
    }

    public void setDifficultyLevel(int diflevel) {
        difficultyLevel = diflevel;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setAssignedMarks(int marks) {
        assignedMarks = marks;
    }

    public int getAssignedMarks() {
        return assignedMarks;
    }

    public void addAnswer(Answer ans) {
        choices[noAnswers++ - 1] = ans;
    }

    public Answer[] getChoices() {
        return choices;
    }
    public Answer getChoices(int i) {
        return choices[i];
    }

    public void print_question(PrintWriter output) {
        //Fill in the method implementation
        output.printf("%-15d|%-10d|%-15s|%-40s|",getQID(),getTopicNo(),getDifficultyLevel(),getDescription());
        
    }
}
