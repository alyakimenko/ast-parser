package com.shpp.p2p.cs.aiakimenko.assignment10;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * The class represents a parser of mathematical expressions and evaluator of it
 */
public class Expression {

    /**
     * Operands stack
     */
    private static Stack<Node> operands = new Stack<>();
    /**
     * Operators stack
     */
    private static Stack<Object> operators = new Stack<>();

    /**
     * Map of math variables
     */
    private static Map<String, Double> variables;

    /**
     * Gets an math expression and then returns a result of it
     * 
     * @param formula math expression string without variables
     * @return expression result
     */
    public static double evaluate(String formula) {
        return parse(formula).evaluate();
    }

    /**
     * Gets an math expression and variables values and then returns a result of
     * math expression
     * 
     * @param args array in which the first element is math expression and others -
     *             variables
     * @return expression result
     */
    public static double evaluate(String[] args) {
        setVariablesHashMap(Arrays.copyOfRange(args, 1, args.length));
        return parse(args[0]).evaluate();
    }

    private static void setVariablesHashMap(String[] copyOfRange) {
        variables = new HashMap<>();

        for (String pair : copyOfRange) {
            String[] entry = pair.split("=");
            variables.put(entry[0].trim(), Double.valueOf(entry[1].trim()));
        }
    }

    /**
     * Parses input string and returns the deepest node
     * 
     * @param input math expression string
     * @return the deepest node which will be evaluate
     */
    public static Node parse(String input) {
        int currentIndex = 0;
        boolean afterOperand = false;

        while (currentIndex < input.length()) {
            int startIndex = currentIndex;
            char currentSymbol = input.charAt(currentIndex++);

            if (Character.isWhitespace(currentSymbol))
                continue;

            if (afterOperand) {
                afterOperand = !afterOperand;
                proccesOperator(currentSymbol);
                continue;
            }

            afterOperand = !afterOperand;
            while (currentIndex < input.length()) {
                currentSymbol = input.charAt(currentIndex);
                if ((!Character.isDigit(currentSymbol) && (currentSymbol != '.'))
                        && !Character.isAlphabetic(currentSymbol)) {
                    break;
                }
                currentIndex++;
            }

            String operandContext = input.substring(startIndex, currentIndex);

            if (Character.isAlphabetic(input.charAt(startIndex)) && variables.containsKey(operandContext)) {
                operands.push(new Variable(operandContext, variables.get(operandContext)));
            } else {
                operands.push(new Number(Double.valueOf(operandContext)));
            }
        }

        return getDeepestNode();
    }

    /**
     * Checks the operator for precedence level and push it into oparetors stack
     * 
     * @param operatorSymbol input operator symbol
     */
    private static void proccesOperator(char operatorSymbol) {
        BinaryOperator operator = BinaryOperator.fromSymbol(operatorSymbol);

        while (!operators.isEmpty()
                && ((BinaryOperator) operators.peek()).getPrecedence() >= operator.getPrecedence()) {
            createNewOperand((BinaryOperator) operators.pop(), operands);
        }

        operators.push(operator);
    }

    /**
     * Gets the deepest node after parsing an input string
     * 
     * @return the deepest node from which all calculations will occur
     */
    private static Node getDeepestNode() {
        while (!operators.isEmpty()) {
            Object operator = operators.pop();
            createNewOperand((BinaryOperator) operator, operands);
        }

        Node node = operands.pop();
        if (!operands.isEmpty())
            throw new IllegalArgumentException();

        return node;
    }

    /**
     * Creates new operand with operator and two childs (operands)
     * 
     * @param operator type of operator
     * @param operands operands stack from which two values will be taken (left
     *                 child and right child of binary operator)
     */
    private static void createNewOperand(BinaryOperator operator, Stack<Node> operands) {
        Node rightOperand = operands.pop();
        Node leftOperand = operands.pop();
        operands.push(new BinaryExpression(leftOperand, operator, rightOperand));
    }

}