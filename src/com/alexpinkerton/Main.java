package com.alexpinkerton;

public class Main {

    public static void main(String[] args) {

        //TODO Add logic for splitting based on known operators.
        //TODO fix bug with multiple character digits, Delimit based on operators.
        //String expression = "3+4*2/(1-5)^5";
        //String expression = "3 + 4 * 2 / ( 1 - 5 ) ^ 5";
        //String expression = "5+((1+2)*4)-3";
        String expression = "( ( 5 + 6 ) * 8 )^2 - 2";

        System.out.println(ExpressionParser.parseExpression(expression));

        //ExpressionParser.test("52*3+2*4-6");
        //ExpressionParser.generateRPN(expression);


    }
}
