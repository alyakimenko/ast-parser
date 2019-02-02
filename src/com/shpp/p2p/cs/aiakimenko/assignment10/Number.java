package com.shpp.p2p.cs.aiakimenko.assignment10;

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