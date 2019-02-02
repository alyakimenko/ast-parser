package com.shpp.p2p.cs.aiakimenko.assignment10;

public enum BinaryOperator {

    ADD('+', 1), SUB('-', 1), MUL('*', 2), DIV('/', 2), POW('^', 3);

    public final char symbol;
    public final int precedence;

    BinaryOperator(char symbol, int precedence) {

        this.symbol = symbol;
        this.precedence = precedence;

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