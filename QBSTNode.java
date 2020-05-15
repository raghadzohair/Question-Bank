/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionbank;

/**
 *
 * @author Raghad
 */
public class QBSTNode {
    private Question question;
    private QBSTNode left;
    private QBSTNode right;   

    ///////////////////////-------------------------////////////////////////
                              // constructors
    public QBSTNode() {
        this.question = null;
        left = right = null;
    }
    
    public QBSTNode(Question question) {
        this.question = question;
        left = right =null;
    }
    
    public QBSTNode(Question question, QBSTNode left, QBSTNode right) {
        this.question = question;
        this.left = left;
        this.right = right;
    }
    
    
///////////////////////-------------------------////////////////////////    
                        // get and set methods
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public QBSTNode getLeft() {
        return left;
    }

    public void setLeft(QBSTNode left) {
        this.left = left;
    }

    public QBSTNode getRight() {
        return right;
    }

    public void setRight(QBSTNode right) {
        this.right = right;
    }   
}
