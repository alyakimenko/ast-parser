package com.shpp.p2p.cs.aiakimenko.assignment10;

/**
 * Represents a binary operator type
 */
public enum BinaryOperator {

    // All valid types of operator and its precedence levels
    ADD('+', 1), SUB('-', 1), MUL('*', 2), DIV('/', 2), POW('^', 3);

    private final char symbol;
    private final int precedence;

    BinaryOperator(char symbol, int precedence) {
        this.symbol = symbol;
        this.precedence = precedence;
    }

    public int getPrecedence() {
        return this.precedence;
    }

    public double evaluate(double leftValue, double rightValue) {
        switch (this) {
            case ADD:
                return leftValue + rightValue;
            case SUB:
                return leftValue - rightValue;
            case MUL:
                return leftValue * rightValue;
            case DIV:
                return leftValue / rightValue;
            case POW:
                return Math.pow(leftValue, rightValue);
        }
        throw new IllegalStateException();
    }

    public static BinaryOperator fromSymbol(char symbol) {
        for (BinaryOperator operator : values()) {
            if (operator.symbol == symbol) {
                return operator;
            }
        }
        throw new IllegalArgumentException(String.valueOf(symbol));
    }

}