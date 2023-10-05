/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tree;

/* Name: Le Cong Hung
Student Code: SE161248
Purpose: Problem Tree.
 */
import java.util.Stack;
import java.util.*;

public class Tree {

//    public static void main(String[] args) {
//        // Create different arithmetic expressions in prefix notation
//        String expression1 = "+ 2 3";
//        String expression2 = "* + 2 3 - 5 1";
//        String expression3 = "/ * + 2 3 - 5 1 8";
//
//        Node tree1 = buildTreeFromPrefix(expression1.split(" "));
//        Node tree2 = buildTreeFromPrefix(expression2.split(" "));
//        Node tree3 = buildTreeFromPrefix(expression3.split(" "));
//
//        System.out.println("Expression 1:");
//        showTree(0, tree1);
//        System.out.print("Prefix: ");
//        prefix(tree1);
//        System.out.print("\nPostfix: ");
//        postfix(tree1);
//        System.out.print("\nInfix: ");
//        infix(tree1);
//        System.out.println("\nValue: " + eval(tree1));
//
//        System.out.println("\nExpression 2:");
//        showTree(0, tree2);
//        System.out.print("Prefix: ");
//        prefix(tree2);
//        System.out.print("\nPostfix: ");
//        postfix(tree2);
//        System.out.print("\nInfix: ");
//        infix(tree2);
//        System.out.println("\nValue: " + eval(tree2));
//
//        System.out.println("\nExpression 3:");
//        showTree(0, tree3);
//        System.out.print("Prefix: ");
//        prefix(tree3);
//        System.out.print("\nPostfix: ");
//        postfix(tree3);
//        System.out.print("\nInfix: ");
//        infix(tree3);
//        System.out.println("\nValue: " + eval(tree3));
//    } 
//    
//    THIS CODE HAVE 3 EMXAPLE , U JUST RUN    
// -----------------------------------------------------------
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------");
        System.out.println("               Must Have Space For Ur Input");
        System.out.println("-----------------------------------------------------------");
        System.out.print("Enter an arithmetic expression in prefix notation: ");
        String expression = scanner.nextLine();
        scanner.close();

        Node tree = buildTreeFromPrefix(expression.split(" "));

        System.out.println("Expression:");
        showTree(0, tree);
        System.out.print("Prefix: ");
        prefix(tree);
        System.out.print("\nPostfix: ");
        postfix(tree);
        System.out.print("\nInfix: ");
        infix(tree);
        System.out.println("\nValue: " + eval(tree));
    }
//    
//    THIS CODE DON'T HAVE EXAMPLE, U MUST IN PUT EXPRESSION U WANT 
// --------------------------------------------------------------------------------------------------

    public static Node node(char op, Node l, Node r) {
        Node a = new Node();
        a.operation = op;
        a.leftChild = l;
        a.rightChild = r;
        return a;
    }
// -------------------------------------------------------------

    public static Node node(int val) {
        Node a = new Node();
        a.value = val;
        return a;
    }

// -------------------------------------------------------------      
    public static void prefix(Node t) {
        if (t.leftChild == null && t.rightChild == null) {
            System.out.print(t.value + " ");
        } else {
            System.out.print(t.operation + " ");
            prefix(t.leftChild);
            prefix(t.rightChild);
        }
    }
// -------------------------------------------------------------      

    public static void postfix(Node t) {
        if (t.leftChild == null && t.rightChild == null) {
            System.out.print(t.value + " ");
        } else {
            postfix(t.leftChild);
            postfix(t.rightChild);
            System.out.print(t.operation + " ");
        }
    }
// -------------------------------------------------------------      

    public static void infix(Node t) {
        if (t.leftChild == null && t.rightChild == null) {
            System.out.print(t.value);
        } else {
            System.out.print("(");
            infix(t.leftChild);
            System.out.print(t.operation);
            infix(t.rightChild);
            System.out.print(")");
        }
    }
// -------------------------------------------------------------      

    public static double eval(Node t) {
        double val = 0;
        if (t.leftChild == null && t.rightChild == null) {
            val = t.value;
        } else {
            switch (t.operation) {
                case '+':
                    val = eval(t.leftChild) + eval(t.rightChild);
                    break;
                case '-':
                    val = eval(t.leftChild) - eval(t.rightChild);
                    break;
                case '*':
                    val = eval(t.leftChild) * eval(t.rightChild);
                    break;
                case '/':
                    val = eval(t.leftChild) / eval(t.rightChild);
            }
        }
        return val;
    }
// -------------------------------------------------------------      

    public static void showTree(int n, Node t) {
        tab(n);
        if (t.leftChild == null && t.rightChild == null) {
            System.out.println(t.value);
        } else {
            System.out.println(t.operation);
            showTree(n + 2, t.leftChild);
            showTree(n + 2, t.rightChild);
        }
    }
// -------------------------------------------------------------      

    public static void tab(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }
    }

    public static Node buildTreeFromPrefix(String[] tokens) {
        Stack<Node> stack = new Stack<>();

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];

            if (isOperator(token)) {
                char op = token.charAt(0);
                Node left = stack.pop();
                Node right = stack.pop();
                stack.push(node(op, left, right));
            } else {
                int value = Integer.parseInt(token);
                stack.push(node(value));
            }
        }

        return stack.pop();
    }

    public static boolean isOperator(String token) {
        return token.length() == 1 && "+-*/".contains(token);
    }
}
