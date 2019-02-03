package com.shpp.p2p.cs.aiakimenko.assignment10;

/**
 * Represents a Variable node of AST
 */
class Variable implements Node {

    private final String name;
    private double value;

    public Variable(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public double evaluate() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}