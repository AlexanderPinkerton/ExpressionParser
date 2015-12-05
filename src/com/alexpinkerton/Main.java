package com.alexpinkerton;

public class Main {

    public static void main(String[] args) {

        //TODO Add logic for splitting based on known operators.

        //String expression = "f = a * c ^ k / p - q * g ^ ( h - b )";
        String expression = "3 + 4 * 2 / ( 1 - 5 ) ^ 5";

        //System.out.println(ExpressionParser.generateRPN(expression));
        System.out.println(ExpressionParser.parseExpression(expression));


    }
}
