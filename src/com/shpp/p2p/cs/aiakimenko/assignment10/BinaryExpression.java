package com.shpp.p2p.cs.aiakimenko.assignment10;

/**
 * Represents a Binary expression node (with left, right childs and operator) of AST
 */
public class BinaryExpression implements Node {

    public final Node leftChild;
    public final BinaryOperator operator;
    public final Node rightChild;

    public BinaryExpression(Node leftChild, BinaryOperator operator, Node rightChild) {
        this.leftChild = leftChild;
        this.operator = operator;
        this.rightChild = rightChild;
    }

    @Override
    public double evaluate() {
        double leftValue = this.leftChild.evaluate();
        double rightValue = this.rightChild.evaluate();
        return operator.evaluate(leftValue, rightValue);
    }

}