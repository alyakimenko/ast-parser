package com.shpp.p2p.cs.aiakimenko.assignment11;

import java.lang.reflect.InvocationTargetException;

/**
 * Represents one single abstract syntax tree (AST) node
 */
public interface Node {

    /**
     * @return result of evaluating node
     */
    double evaluate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

}