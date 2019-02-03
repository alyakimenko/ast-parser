package com.shpp.p2p.cs.aiakimenko.assignment10;

/**
 * Represents a Number node of AST
 */
public class Number implements Node {

    private final double value;

    public Number(double value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return this.value;
    }

}