package com.shpp.p2p.cs.aiakimenko.assignment10;

import java.util.Stack;

public class Controller {

    public static void execute(String[] args) {
        System.out.println(parse(args[0]).evaluate());
    }

    public static Node parse(String input) {
        int curIndex = 0;
        boolean afterOperand = false;

        Stack<Node> operands = new Stack<>();
        Stack<Object> operators = new Stack<>();

        while (curIndex < input.length()) {
            int startIndex = curIndex;
            char c = input.charAt(curIndex++);
 
            if (Character.isWhitespace(c))
                continue;
 
            if (afterOperand) {
                afterOperand = false;
                BinaryOperator operator = BinaryOperator.fromSymbol(c);
                while (!operators.isEmpty() && (((BinaryOperator) operators.peek()).precedence >= operator.precedence))
                    createNewOperand((BinaryOperator) operators.pop(), operands);
                operators.push(operator);
                continue;
            }
 
            afterOperand = true;
            while (curIndex < input.length()) {
                c = input.charAt(curIndex);
                if (((c < '0') || (c > '9')) && (c != '.'))
                    break;
                curIndex++;
            }
            operands.push(new Number(Double.valueOf(input.substring(startIndex, curIndex))));
        }
 
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