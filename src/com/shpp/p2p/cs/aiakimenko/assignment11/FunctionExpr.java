package com.shpp.p2p.cs.aiakimenko.assignment11;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Represents a Function node of AST
 */
class FunctionExpr implements Node {

    private Node arg;
    private String name;

    /**
     * @param name The name of function
     * @param arg Its arguments (parameters)
     */
    FunctionExpr(String name, Node arg) {
        this.name = name;
        this.arg = arg;
    }

    /**
     * @return the arg
     */
    Node getArg() {
        return arg;
    }

    /**
     * @param arg the arg to set
     */
    void setArg(Node arg) {
        this.arg = arg;
    }

    @Override
    public double evaluate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (this.name.equals("log2")) {
            return Math.log(arg.evaluate())/Math.log(2);
        }
        Method method = Math.class.getDeclaredMethod(this.name, double.class);
        return (Double) method.invoke(null, arg.evaluate());
    }

}