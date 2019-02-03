package com.shpp.p2p.cs.aiakimenko.assignment10;

/**
 * Represents a Variable node of AST
 */
class Variable implements Node {

    /**
     * Variable name
     */
    private final String name;
    /**
     * Variable value
     */
    private double value;

    /**
     * @param name The name of variable
     * @param value The value of variable
     */
    public Variable(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public double evaluate() {
        return this.value;
    }

    /**
     * @return variable name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return variable value
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Sets a variable value to value of parameter
     * @param value number value
     */
    public void setValue(double value) {
        this.value = value;
    }

}