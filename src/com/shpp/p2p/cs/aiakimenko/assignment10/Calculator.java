package com.shpp.p2p.cs.aiakimenko.assignment10;

/**
 * The calculator class takes as input math expression 
 * and variables (if any) and returns the result of it.
 * 
 * It is based on an abstract syntax tree (AST).
 * 
 * For example, we have a following mathematical expression: 2 + 2 * 2
 * 
 * The AST, in this case, will look like this:
 * 
 *      [+]
 *    .    .
 *   2     [*]
 *       .    .
 *      2      2
 */
public class Calculator {

    public static void main(String[] args) {

        try {
            if (args.length == 1) {
                System.out.println(Expression.calculate(args[0]));
            } else {
                System.out.println(Expression.calculate(args));
            }
        } catch (Exception ex) {
            System.out.println("Error! Check out your input and try again.");
        }

    }

}