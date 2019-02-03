package com.shpp.p2p.cs.aiakimenko.assignment10;

public class Main {

    public static void main(String[] args) {

        try {
            System.out.println(Expression.evaluate(args));
        } catch(Exception ex) {
            System.out.println("Error! Try again, and check out your input.");
        }

    }

}