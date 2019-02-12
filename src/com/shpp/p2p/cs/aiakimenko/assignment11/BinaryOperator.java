package com.shpp.p2p.cs.aiakimenko.assignment11;

/**
 * Represents a binary operator type
 */
public enum BinaryOperator {

    // All valid types of operator and its precedence levels
    ADD('+', 1), 
    SUB('-', 1), 
    MUL('*', 2), 
    DIV('/', 2), 
    POW('^', 3);

    /**
     * Symbol of current operator
     */
    private final char symbol;
    /**
     * Precedence level of current operator
     */
    private final int precedence;

    /**
     * @param symbol operator symbol
     * @param precedence operator precedence level
     */
    BinaryOperator(char symbol, int precedence) {
        this.symbol = symbol;
        this.precedence = precedence;
    }

    /**
     * @return Precedence level of current operator
     */
    public int getPrecedence() {
        return this.precedence;
    }

    /**
     * @param leftValue left number
     * @param rightValue right number
     * @return result of binary expression
     */
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

    /**
     * Converts char to BinaryOperator object
     * @param symbol char symbol
     * @return BinaryOperator
     */
    public static BinaryOperator getFromSymbol(char symbol) {
        for (BinaryOperator operator : values()) {
            if (operator.symbol == symbol) {
                return operator;
            }
        }
        throw new IllegalArgumentException(String.valueOf(symbol));
    }

}