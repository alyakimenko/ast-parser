package com.shpp.p2p.cs.aiakimenko.assignment11;

/**
 * Represents a Number node of AST
 */
public class Number implements Node {

    /**
     * Value of a number
     */
    private final double value;

    /**
     * @param value number value for object
     */
    Number(double value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return this.value;
    }

}