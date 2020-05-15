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
public class CourseQBank {
    private int bankID;
    private static int noOfBanks;
    private String courseCode;
    private int noOfTopics;
    private QBSTNode root;
    

    public CourseQBank(String ccode) {
        noOfBanks++;
        bankID = noOfBanks;
        courseCode = ccode;
    }

    public void setCourseCode(String code) {
        courseCode = code;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setNoOfTopics(int tno) {
        noOfTopics = tno;
    }

    public int getNoOfTopics() {
        return noOfTopics;
    }

    public void setRoot(QBSTNode node) {
        root = node;
    }

    public QBSTNode getRoot() {
        return root;
    }

    public QBSTNode find_question_by_id(int id) {
        //Fill in the method implementation 
        return find_question_by_id(root, id);
        
    }
    private QBSTNode find_question_by_id(QBSTNode r, int id){
        if (r == null) { // the tree is empty
            return null;
        } 
        else {
            
            if (id == r.getQuestion().getQID()) {
                return r;
            } 
            else if (id < r.getQuestion().getQID()) {                               
                return find_question_by_id(r.getLeft(), id);
            } 
            else {               
                return find_question_by_id(r.getRight(),id);
            }
        }
    }

    public QBSTNode insert(String description, Answer[] choices, int id, int topic, int diff, int marks) {
        Question q = new Question(description, choices, id, topic, diff, marks);
        QBSTNode newNode = new QBSTNode(q);
        root = insert(root, newNode);
        if (root != null) {
            System.out.println("	" + newNode.getQuestion().getDescription() + " " + newNode.getQuestion().getQID());           
        }
        return root;
    }

    private QBSTNode insert(QBSTNode p, QBSTNode newNode) {
        // IF there is no tree, newNode will be the root, so just return it
        if (p == null) {
            return newNode;
        } // ELSE, we have a tree. Insert to the right or the left
        else {

            // Insert to the RIGHT of root
            if (newNode.getQuestion().getQID() > p.getQuestion().getQID()) {
                // Recursively insert into the right subtree
                // The result of insertion is an updated root of the right subtree
                QBSTNode temp = insert(p.getRight(), newNode);
                // Save this newly updated root of right subtree into p.right
                p.setRight(temp);
            } // Insert to the LEFT of root
            else {
                // Recursively insert into the left subtree
                // The result of insertion is an updated root of the left subtree
                QBSTNode temp = insert(p.getLeft(), newNode);
                // Save this newly updated root of left subtree into p.left
                p.setLeft(temp);
            }
        }

        // Return root of updated tree
        return p;

    }

    public void delete(QBSTNode node) {
        root = delete(root, node);
    }

    private QBSTNode delete(QBSTNode p, QBSTNode node2delete) {
        QBSTNode newnode2delete, node2save, parent;
        int id = node2delete.getQuestion().getQID();
        Question saveQuestion;

        // Step 1: check if node exists - done in QuestionBank.delete_question()
        // Step 2: Find the parent of the node we want to delete
        parent = parent(p, node2delete);

        // Step 3: Perform Deletion based on three possibilities
        // **1** :  node2delete is a leaf node
        if (isLeaf(node2delete)) {
            // if parent is null, this means that node2delete is the ONLY node in the tree
            if (parent == null) {
                return null; // we return null as the updated root of the tree
            }
            // Delete node if it is a left child
            if (id < parent.getQuestion().getQID()) {
                parent.setLeft(null);
            } // Delete node if it is a right child
            else {
                parent.setRight(null);
            }

            // Finally, return the root of the tree (in case the root got updated)
            return p;
        }

        // **2** : node2delete has only a left child
        if (hasOnlyLeftChild(node2delete)) {
            // if node2delete is the root
            if (parent == null) {
                return node2delete.getLeft();
            }

            // If node2delete is not the root,
            // it must the left OR the right child of some node
            // IF it is the left child of some node
            if (id < parent.getQuestion().getQID()) {
                parent.setLeft(parent.getLeft().getLeft());
            } // ELSE it is the right child of some node
            else {
                parent.setRight(parent.getRight().getLeft());
            }

            // Finally, return the root of the tree (in case the root got updated)
            return p;
        }

        // **3** :  node2delete has only a right child
        if (hasOnlyRightChild(node2delete)) {
            // if node2delete is the root
            if (parent == null) {
                return node2delete.getRight();
            }

            // If node2delete is not the root,
            // it must the left OR the right child of some node
            // IF it is the left child of some node
            if (id < parent.getQuestion().getQID()) {
                parent.setLeft(parent.getLeft().getRight());
            } // ELSE it is the right child of some node
            else {
                parent.setRight(parent.getRight().getRight());
            }

            // Finally, return the root of the tree (in case the root got updated)
            return p;
        }

        // **4** :  node2delete has TWO children.
        // Remember, we have two choices: the minVal from the right subtree (of the deleted node)
        // or the maxVal from the left subtree (of the deleted node)
        // We choose to find the minVal from the right subtree.
        newnode2delete = minNode(node2delete.getRight());
        // Now we need to temporarily save the id value(s) from this node
        saveQuestion = newnode2delete.getQuestion();

        // Now, we recursively call our delete method to actually delete this node that we just copied the id from
        p = delete(p, newnode2delete);

        // Now, put the saved id (in saveValue) into the correct place (the original node we wanted to delete)
        node2delete.setQuestion(saveQuestion);

        // Finally, return the root of the tree (in case the root got updated)
        return p;
    }
    // 
    //

    public QBSTNode parent(QBSTNode p) {
        return parent(root, p);
    }
    //
    // 
    //

    private QBSTNode parent(QBSTNode root, QBSTNode p) {
        // Take care of NULL cases
        if (root == null || root == p) {
            return null; // because there is on parent
        }
        // If root is the actual parent of node p
        if (root.getLeft() == p || root.getRight() == p) {
            return root; // because root is the parent of p
        }
        // Look for p's parent in the left side of root
        if (p.getQuestion().getQID() < root.getQuestion().getQID()) {
            return parent(root.getLeft(), p);
        } // Look for p's parent in the right side of root
        else if (p.getQuestion().getQID() > root.getQuestion().getQID()) {
            return parent(root.getRight(), p);
        } // Take care of any other cases
        else {
            return null;
        }
    }

    //
    // 
    //
    public boolean isLeaf(QBSTNode p) {
        return (p.getLeft() == null && p.getRight() == null);
    }

    //
    // 
    //
    public boolean hasOnlyLeftChild(QBSTNode p) {
        return (p.getLeft() != null && p.getRight() == null);
    }

    //
    // 
    //
    public boolean hasOnlyRightChild(QBSTNode p) {
        return (p.getLeft() == null && p.getRight() != null);
    }
    // 
    //

    public QBSTNode minNode(QBSTNode root) {
        if (root == null) {
            return null;
        } else {
            if (root.getLeft() == null) {
                return root;
            } else {
                return minNode(root.getLeft());
            }
        }
    }

    //
    // 
    //
    public QBSTNode maxNode(QBSTNode root) {
        if (root == null) {
            return null;
        } else {
            if (root.getRight() == null) {
                return root;
            } else {
                return maxNode(root.getRight());
            }
        }
    }
    //
    //
    //
///////////////////////////////////------ PRINT ALL QUESTIONS ------///////////////////////////////////    
    private  void print_all_questions(QBSTNode root, PrintWriter output) { 
        ///////////////////------------ Fill in the method implementation ------------/////////////// 
        if (root != null) {
            root.getQuestion().print_question(output);
             String s = "";
            for (int i = 0; i < root.getQuestion().getChoices().length; i++) {
                root.getQuestion().getChoices(i).print_answers(output);
                
                output.printf("%-15s|%-10s|%-15s|%-40s|", s, s, s, s);
            }
            output.printf("%-10s|%-40s|%-10s\r\n", s, s, s);

            print_all_questions(root.getLeft(),  output);
            print_all_questions(root.getRight(),  output);
        }
    }
    //
    //
    //
/////////////////////////////////-------- PRINT BY TOPIC NO. --------///////////////////////////////////    
    private void print_questions_topic_mode(QBSTNode root, int topicno, PrintWriter output){
        ///////////////////------------ Fill in the method implementation ------------/////////////// 
        if (root != null) {
            if (root.getQuestion().getTopicNo() == topicno) { 
                root.getQuestion().print_question(output);
                
                String s = "";
                for (int i = 0; i < root.getQuestion().getChoices().length; i++) {
                    root.getQuestion().getChoices(i).print_answers(output);
                   
                    output.printf("%-15s|%-10s|%-15s|%-40s|",s,s,s,s);
                }
                output.printf("%-10s|%-40s|%-10s\r\n",s,s,s);
                
            }
            print_questions_topic_mode(root.getLeft(),topicno, output);
            print_questions_topic_mode(root.getRight(),topicno, output);
        }
    }
    //
    //
    //
////////////////////////////////---------- TO CHECK MODE 0 OR 1 ---------///////////////////////////////    
    public void print_questions(int mode, int criterionvalue, PrintWriter output) {        
        ///////////////////------------ Fill in the method implementation ------------///////////////   
        if(mode == 0){
            print_all_questions(root, output) ;
        }
        else
            print_questions_topic_mode( root,criterionvalue,output);  
    }
}
