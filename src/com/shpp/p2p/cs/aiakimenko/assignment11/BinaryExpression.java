package com.shpp.p2p.cs.aiakimenko.assignment11;

import java.lang.reflect.InvocationTargetException;

/**
 * Represents a Binary expression node (with left, right child and operator) of AST
 */
public class BinaryExpression implements Node {

    /**
     * Left node of expression
     */
    private final Node leftChild;
    /**
     * BinaryOperator of expression
     */
    private final BinaryOperator operator;
    /**
     * Right node of expression
     */
    private final Node rightChild;

    /**
     * @param leftChild left node of expression 
     * @param operator operator between nodes
     * @param rightChild right node of expression
     */
    BinaryExpression(Node leftChild, BinaryOperator operator, Node rightChild) {
        this.leftChild = leftChild;
        this.operator = operator;
        this.rightChild = rightChild;
    }

    @Override
    public double evaluate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        double leftValue = this.leftChild.evaluate();
        double rightValue = this.rightChild.evaluate();
        return operator.evaluate(leftValue, rightValue);
    }

    /**
     * @return the leftChild
     */
    Node getLeftChild() {
        return this.leftChild;
    }

    /**
     * @return the rightChild
     */
    Node getRightChild() {
        return this.rightChild;
    }

}