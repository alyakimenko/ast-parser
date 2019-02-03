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
     * Tail node
     */
    private static Node tailNode;

    /**
     * Input math expression
     */
    private static String expression;

    /**
     * Gets an math expression and then returns a result of it
     * 
     * @param formula math expression string without variables
     * @return expression result
     */
    public static double calculate(String formula) {
        return parse(formula).evaluate();
    }

    /**
     * Gets an math expression and variables values and then returns a result of it
     * 
     * @param args array in which the first element is math expression and others -
     *             variables
     * @return expression result
     */
    public static double calculate(String[] args) {
        if (args == null) {
            throw new IllegalArgumentException();
        }
        
        if (tailNode == null || !expression.equals(args[0])) {
            setVariablesHashMap(Arrays.copyOfRange(args, 1, args.length));
            expression = args[0];
            return parse(expression).evaluate();
        } else {
            setVariablesHashMap(Arrays.copyOfRange(args, 1, args.length));
            resetVariablesValues(tailNode);
            return tailNode.evaluate();
        }
    }

    /**
     * Resets variables values
     * 
     * @param node Tail node
     */
    private static void resetVariablesValues(Node node) {
        if (node instanceof Variable) {
            Variable newVariable = (Variable) node;
            newVariable.setValue(variables.get(newVariable.getName()));
        }

        if (node instanceof BinaryExpression) {
            BinaryExpression tempExpr = (BinaryExpression) node;
            resetVariablesValues(tempExpr.getLeftChild());
            resetVariablesValues(tempExpr.getRightChild());
        }
    }

    /**
     * Sets a variables hashmap
     * 
     * @param vars array of all expression variables
     */
    private static void setVariablesHashMap(String[] vars) {
        variables = new HashMap<>();

        for (String var : vars) {
            String[] entry = var.split("=");
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

            if (Character.isWhitespace(currentSymbol)) {
                continue;
            }

            // if it's operator (+, -, /, *, ^)
            if (afterOperand) {
                if (currentSymbol == ')') {
                    proccesParenthesesOperator();
                    continue;
                }
                afterOperand = !afterOperand;
                proccesOperator(currentSymbol);
                continue;
            }

            if (currentSymbol == '(') {
                operators.push(Parentheses.LEFT);
                continue;
            }

            // if it's variable or number
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

        return getTailNode();
    }

    private static void proccesParenthesesOperator() {
        Object operator;
        while (!operators.isEmpty() && ((operator = operators.pop()) != Parentheses.LEFT)) {
            createNewOperand((BinaryOperator) operator, operands);
        }
    }

    /**
     * Checks the operator for precedence level and push it into oparetors stack
     * 
     * @param operatorSymbol input operator symbol
     */
    private static void proccesOperator(char operatorSymbol) {
        BinaryOperator operator = BinaryOperator.fromSymbol(operatorSymbol);

        while (!operators.isEmpty()
                && (operators.peek() != Parentheses.LEFT) 
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
    private static Node getTailNode() {
        while (!operators.isEmpty()) {
            Object operator = operators.pop();
            if (operator == Parentheses.LEFT)
                throw new IllegalArgumentException();
            createNewOperand((BinaryOperator) operator, operands);
        }

        tailNode = operands.pop();
        if (!operands.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return tailNode;
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