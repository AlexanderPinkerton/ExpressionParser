package com.alexpinkerton;

public class Main {

    public static void main(String[] args) {

        //TODO Add logic for splitting based on known operators.

        //String expression = "f = a * c ^ k / p - q * g ^ ( h - b )";
        String expression = "3 + 4 * 2 / ( 1 - 5 ) ^ 5";
        //String expression = "5+((1+2)*4)-3";
        //String expression = "( ( 5 + 6 ) * 8 ) - 2";

        //System.out.println(ExpressionParser.generateRPN(expression));
        System.out.println(ExpressionParser.parseExpression(expression));


    }
}
