/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Raghad
 */
public class QuestionBank {

    //////////////////////---------- MAIN METHOD --------///////////////////////
    public static void main(String[] args) throws Exception{
        File inputFile = new File("QuestionBank.in");
        // check if file is exists
        if(!inputFile.exists()){
            System.out.println("The file \"QuestionBank\" is not exists");
            System.exit(0);
        }
        // create output file
        File outputFile = new File("QuestionBank.out");
        
        // create Scanner object to read from file input
        Scanner input = new Scanner(inputFile);
        // create PrintWriter object to write into output file
        PrintWriter output = new PrintWriter(outputFile);
        
        //Prepare a Bank for the course CPCS 204
        CourseQBank bank = new CourseQBank("CPCS204");
        Quiz[] quizzes = null; 
        
        // to read number of command and number of quizzes
        int numOfCommand = input.nextInt();
        int numOfQuize = input.nextInt();
        
        quizzes = new Quiz[numOfQuize];
        
        int IncNumOfCommand = 0;

        while(IncNumOfCommand != numOfCommand){
            String command = input.next();
            if(command.equalsIgnoreCase("ADD")){
                add_question(input, bank, output);
            }
            else if(command.equalsIgnoreCase("BUILDQUIZ")){               
                build_quiz(input,quizzes,bank,output);                
            }            
            else if(command.equalsIgnoreCase("DELETE")){               
                delete_question(input,bank,quizzes,output);
            }
            else if(command.equalsIgnoreCase("PRINTQUIZ")){
                print_quiz(input,quizzes,output);
            }
            else if (command.equalsIgnoreCase("PRINTBANKQUESTIONS")){
                print_bank_questions(input,bank,output);
            }
            IncNumOfCommand++; 
        }
        input.close();
        output.close(); 
    }
    //
    ////////////////----------- ADD QUESTION METHOD -------------///////////////
    private static void add_question(Scanner input,CourseQBank bank,PrintWriter output){          
                //Fill in the method implementation
        int ID = input.nextInt();
        int topicNO = input.nextInt();
        int difficultyLevel = input.nextInt();
        int marks = input.nextInt();
        int numOfAnswer = input.nextInt();
        String description = input.nextLine();

        Answer[] choices = new Answer[numOfAnswer];
        for (int i = 0; i < choices.length; i++) {
            choices[i] = new Answer(input.next().charAt(0), input.nextBoolean(), input.nextLine());
        }
        if (bank.find_question_by_id(ID) == null) {
            bank.insert(description, choices, ID, topicNO, difficultyLevel, marks);
            output.println("ADD Command:Question ID: " + ID + " has been added to CPCS204 bank successfully\r\n");
        }
        else {
            output.println("ADD Command:Question ID: 20, [ " + description + " ]  - is already part of the question bank.\r\n");
        }
    }

    //
    ///////////////---------- DELETE QUESTION METHOD ------------///////////////
    private static void delete_question(Scanner input,CourseQBank bank,Quiz[] quizzes, PrintWriter output){        
        //Fill in the method implementation
        int ID = input.nextInt();
        if (bank.find_question_by_id(ID) != null) { 
            for (int i = 0; i < Quiz.getNoOfQuizzes(); i++) {
                if(quizzes[i].find_question(ID).getQuestion().getQID()==ID){
                    quizzes[i].delete_question_id(ID);
                    break;
                }
            }
            bank.delete(bank.find_question_by_id(ID));           
            output.println("DELETE Command:	" + ID + " has been removed from course question bank.");
        } 
        else {
            output.println("DELETE Command:	Cannot Perform DELETE Command: " + ID + " was not found in course question bank.");
        }
        output.print("\r\n");

    }
    //
    //////////////////----------- ADD QUIZ METHOD ------------//////////////////
    private static void build_quiz(Scanner input,Quiz[] quizzes,CourseQBank bank,PrintWriter output){
                //Fill in the method implementation
        output.println("BUILDQUIZ Command:");
        Quiz q = new Quiz();
        int IDQ = input.nextInt();
        
        q.setQuizID(IDQ);
        int numOfQuestion = input.nextInt();
        for (int i = 0; i < numOfQuestion; i++) {
            int IDquestion = input.nextInt();
            QLLNode Q = new QLLNode(bank.find_question_by_id(IDquestion).getQuestion());
            q.addNode(Q);
            output.println("\tquestion id " + IDquestion + " is added to quiz");
        } 
        quizzes[Quiz.getNoOfQuizzes()-1]=q;
        output.println();
    }
    //
    //////////////////------------ PRINT QUIZZES --------------/////////////////
    private static void print_quiz(Scanner input,Quiz[] quizzes,PrintWriter output){
                //Fill in the method implementation
        output.println("PRINTQUIZ Command:");
        int IDQ = input.nextInt();
        output.println("Quiz is: "+IDQ);
        Table(output);
        String s ="";
        output.printf("Ques. ID%-7s|Topic To.%-1s|Diff. Level%-4s|Question%-32s|A.Code%-4s|Answers%-33s|Correct%-10s\r\n",s,s,s,s,s,s,s);
        
        Table(output);
        for (int i = 0; i < Quiz.getNoOfQuizzes(); i++) {
            if(quizzes[i].getQuizID()==IDQ){
                quizzes[i].print_all(output);
                break;
            }
        }
        Table(output);
        output.println();
        output.println();
        
    }
    //
    /////////////////------------- PRINT QUESTIONS -------------////////////////
    private static void print_bank_questions(Scanner input, CourseQBank bank, PrintWriter output) {
                //Fill in the method implementation
        output.println("PRINTBANKQUESTIONS Command:");
        Table(output);
        String s ="";
        output.printf("Ques. ID%-7s|Topic To.%-1s|Diff. Level%-4s|Question%-32s|A.Code%-4s|Answers%-33s|Correct%-10s\r\n",s,s,s,s,s,s,s);

        Table(output);
        String course = input.next();
        int mod1 = input.nextInt();
        int topicNo = input.nextInt();
        bank.print_questions(mod1, topicNo, output);        
        Table(output);
        output.println();
    }
    //
    ////////////////------------ PRINT SCHEDULE -----------------///////////////
    public static void Table(PrintWriter output){
        for (int i = 0; i < 70; i++) {
            output.print("-");
            
        }
        for (int i = 0; i < 80; i++) {
            output.print("-");
            
        }
        output.println();
    }
}
