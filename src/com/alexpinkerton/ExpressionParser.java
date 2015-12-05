package com.alexpinkerton;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Ace on 12/4/2015.
 */
public class ExpressionParser {

    private enum Operator
    {
        EQUALS(0), ADD(1), SUBTRACT(1), MULTIPLY(2), DIVIDE(2), POWER(3);
        final int precedence;
        Operator(int p) { precedence = p; }
    }

    private static Map<String, Operator> ops = new HashMap<String, Operator>() {{
        put("=", Operator.EQUALS);
        put("+", Operator.ADD);
        put("-", Operator.SUBTRACT);
        put("*", Operator.MULTIPLY);
        put("/", Operator.DIVIDE);
        put("^", Operator.POWER);
    }};



    public static Double parseExpression(String infixExpression){
        return evaluateRPN(generateRPN(infixExpression));
    }



    public static String generateRPN(String infix)
    {
        StringBuilder output = new StringBuilder();
        Stack<String> operatorStack  = new Stack<>();

        for (String token : infix.split("\\s")) {
            // Found Operator
            if (ops.containsKey(token)) {
                while ( ! operatorStack.isEmpty() && isHigerPrec(token, operatorStack.peek()))
                    output.append(operatorStack.pop()).append(' ');
                operatorStack.push(token);

                // Found Left Parenthesis
            } else if (token.equals("(")) {
                operatorStack.push(token);

                // Found Right Parenthesis
            } else if (token.equals(")")) {
                while (!operatorStack.peek().equals("("))
                    output.append(operatorStack.pop()).append(' ');
                operatorStack.pop();

                // Found Digit or term.
            } else {
                output.append(token).append(' ');
            }
        }

        while ( ! operatorStack.isEmpty())
            output.append(operatorStack.pop()).append(' ');
        return output.toString();
    }



    public static double evaluateRPN(String RPNString){

        Stack<Double> operandStack = new Stack<>();
        double result = 0;

        for(String token : RPNString.split("\\s")){
            if (ops.containsKey(token)) {
                // Found Operator
                Double intermediate = -666.9876543211234567890d;
                Double rightOperand = operandStack.pop();
                Double leftOperand = operandStack.pop();
                switch (token){
                    case "=":
                        //??
                        break;
                    case "+":
                        intermediate = leftOperand + rightOperand;
                        break;
                    case "-":
                        intermediate = leftOperand - rightOperand;
                        break;
                    case "*":
                        intermediate = leftOperand * rightOperand;
                        break;
                    case "/":
                        intermediate = leftOperand / rightOperand;
                        break;
                    case "^":
                        intermediate = Math.pow(leftOperand,rightOperand);
                        break;
                }
                operandStack.push(intermediate);

            } else {
                // Found Digit or term.
                //TODO Allow variables to allow for more complex terms.
                operandStack.push(Double.parseDouble(token));
            }
        }

        return operandStack.pop();
    }




    private static boolean isHigerPrec(String op, String sub)
    {
        return (ops.containsKey(sub) && ops.get(sub).precedence >= ops.get(op).precedence);
    }

}