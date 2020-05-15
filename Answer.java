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
public class Answer {
    private char ansCode;
    private String ansDescription;
    private boolean correct;
    
    public Answer(){
    }

    public Answer(char ansCode, boolean correct, String ansDescription) {
        this.ansCode = ansCode;
        this.ansDescription = ansDescription;
        this.correct = correct;
    }
    
    public void setAnsCode(char c){
        ansCode = c;
    }
    public char getAnsCode(){
        return ansCode;
    }
   
    public void setAnsDescription(String desc){
        ansDescription = desc;
    }
    public String getAnsDescription() {
        return ansDescription;
    }

    public void setCorrect(boolean c) {
        correct = c;
    }

    public boolean getCorrect() {
        return correct;
    }

    public void print_answers(PrintWriter output) {
        //Fill in the method implementation
        output.printf("%-10c|%-40s|%-10b\r\n",ansCode,ansDescription,correct);
        
    }
}
