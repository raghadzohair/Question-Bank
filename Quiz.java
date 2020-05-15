/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionbank;

import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author Raghad
 */
public class Quiz {
    private int QuizID;
    private QLLNode head;
    private int totalMark;
    private Date date;
    private boolean isIncomplete;
    private static int noOfQuizzes = 0;

    public Quiz() {
        noOfQuizzes++;
        QuizID = noOfQuizzes;

    }

    public static int getNoOfQuizzes() {
        return noOfQuizzes;
    }

    public void setQuizID(int id) {
        QuizID = id;
    }

    public int getQuizID() {
        return QuizID;
    }

    public void setHead(QLLNode node) {
        head = node;
    }

    public QLLNode getHead() {
        return head;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public QLLNode addNode(QLLNode node) {
        if (head == null || head.getQuestion().getQID() > node.getQuestion().getQID()) {
            head = new QLLNode(node.getQuestion(), head);
        } else {
            QLLNode helpPtr = head;
            while (helpPtr.getNext() != null) {
                if (helpPtr.getNext().getQuestion().getQID() > node.getQuestion().getQID()) {
                    break;
                }
                helpPtr = helpPtr.getNext();
            }

            QLLNode new_question = new QLLNode(node.getQuestion(), helpPtr.getNext());
            helpPtr.setNext(new_question);
        }        
        return head;
    }

    public QLLNode find_question(int ID) {
        QLLNode helpPtr = head;
        while (helpPtr != null) {
            if (helpPtr.getQuestion().getQID() == ID) {
                break;
            }
            helpPtr = helpPtr.getNext();
        }
        return helpPtr;
    }

    public void delete_question_id(int qid) {
        if (!isEmpty()) {
            if (head.getQuestion().getQID() == qid) {
                head = head.getNext();
            } else {
                QLLNode helpPtr = head;

                while (helpPtr.getNext() != null) {
                    if (helpPtr.getNext().getQuestion().getQID() == qid) {
                        helpPtr.setNext(helpPtr.getNext().getNext());
                        break;
                    }
                    helpPtr = helpPtr.getNext();
                }
            }
        }
    }

    public void print_all(PrintWriter output) {
        //Fill in the method implementation  
        QLLNode helpPtr = head;
        while(helpPtr != null){
            helpPtr.getQuestion().print_question(output);
            //output.printf("%-15d|%-10d|%-15d|%-40s|",helpPtr.getQuestion().getQID(),helpPtr.getQuestion().getTopicNo()
            //,helpPtr.getQuestion().getDifficultyLevel(),helpPtr.getQuestion().getDescription());
            String s = "";
            for (int i = 0; i < helpPtr.getQuestion().getChoices().length; i++) {
                helpPtr.getQuestion().getChoices(i).print_answers(output);
                //output.printf("%-10c|%-40s|%-10b\r\n", helpPtr.getQuestion().getChoices(i).getAnsCode(),
                //helpPtr.getQuestion().getChoices(i).getAnsDescription(), helpPtr.getQuestion().getChoices(i).getCorrect());

                output.printf("%-15s|%-10s|%-15s|%-40s|", s, s, s, s);
            }
            output.printf("%-10s|%-40s|%-10s\r\n", s, s, s);
            helpPtr = helpPtr.getNext();
        }
        
    }
    

    public void setTotalMark(int total) {
        totalMark = total;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public void setDate(Date d) {
        date = d;
    }

    public Date getDate() {
        return date;
    }

    public void setIsIncomplete(boolean b) {
        isIncomplete = b;
    }

    public boolean getIsIncomplete() {
        return isIncomplete;
    }
}
