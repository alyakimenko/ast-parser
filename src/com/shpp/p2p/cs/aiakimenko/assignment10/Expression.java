package com.shpp.p2p.cs.aiakimenko.assignment10;

import java.util.Stack;

public class Expression {

    private static Stack<Node> operands = new Stack<>();
    private static Stack<Object> operators = new Stack<>();

    public static void evaluate(String[] args) {
        System.out.println(parse(args[0]).evaluate());
    }

    public static Node parse(String input) {
        int currentIndex = 0;
        boolean afterOperand = false;

        while (currentIndex < input.length()) {
            int startIndex = currentIndex;
            char currentSymbol = input.charAt(currentIndex++);

            if (currentSymbol == ' ') {
                continue;
            }

            if (afterOperand) {
                afterOperand = !afterOperand;
                proccesOperator(currentSymbol);
                continue;
            }

            afterOperand = !afterOperand;
            while (currentIndex < input.length()) {
                currentSymbol = input.charAt(currentIndex);
                if (((currentSymbol < '0') || (currentSymbol > '9')) && (currentSymbol != '.'))
                    break;
                currentIndex++;
            }
            operands.push(new Number(Double.valueOf(input.substring(startIndex, currentIndex))));
        }

        return getDeepestNode();
    }

    private static void proccesOperator(char operatorSymbol) {
        BinaryOperator operator = BinaryOperator.fromSymbol(operatorSymbol);

        while (!operators.isEmpty() && ((BinaryOperator) operators.peek()).precedence >= operator.precedence) {
            createNewOperand((BinaryOperator) operators.pop(), operands);
        }

        operators.push(operator);
    }

    private static Node getDeepestNode() {
        while (!operators.isEmpty()) {
            Object operator = operators.pop();
            createNewOperand((BinaryOperator) operator, operands);
        }

        Node expression = operands.pop();
        if (!operands.isEmpty())
            throw new IllegalArgumentException();

        return expression;
    }

    private static void createNewOperand(BinaryOperator operator, Stack<Node> operands) {
        Node rightOperand = operands.pop();
        Node leftOperand = operands.pop();
        operands.push(new BinaryExpression(leftOperand, operator, rightOperand));
    }

}