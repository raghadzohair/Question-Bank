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
public class QLLNode {
    private Question question;
    private QLLNode next;

    public QLLNode(Question q){
        question = q;
        next = null;
    }
     public QLLNode(Question q, QLLNode n){
        question = q;
        next = n;
    }
    public void setQuestion(Question q){
        question = q;
    }
    public Question getQuestion(){
        return question;
    }
    public void setNext(QLLNode node){
        next = node;
    }
    public QLLNode getNext(){
        return next;
    }
}
