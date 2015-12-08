package com.alexpinkerton;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Alexander Pinkerton on 12/4/2015.
 */
public class ExpressionParser {


    private enum Operator
    {
        EQUALS(0), LEFTPARENTHESIS(0), RIGHTPARENTHESIS(0), ADD(1), SUBTRACT(1), MULTIPLY(2), DIVIDE(2), POWER(3);
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
        put("(", Operator.LEFTPARENTHESIS);
        put(")", Operator.RIGHTPARENTHESIS);
    }};



    public static Double parseExpression(String infixExpression){
        return evaluateRPN(generateRPN(infixExpression));
    }


    public static String generateRPN(String infix) {
        StringBuilder output = new StringBuilder();
        //Stack<String> operatorStack  = new Stack<>();
        LinkedStack<String> operatorStack = new LinkedStack<>();

        StringBuilder formatted = new StringBuilder();
        infix = infix.replaceAll("\\s", "");
        System.out.println(infix);
        boolean lastOp = false;
        for (int i = 0; i < infix.length(); i++) {
            if (ops.containsKey(infix.substring(i, i + 1))) {
                //If operator in hashmap
                if (!lastOp) {
                    formatted.append(" ").append(infix.charAt(i)).append(" ");
                } else {
                    formatted.append(infix.charAt(i)).append(" ");
                }
                lastOp = true;
            } else {
                formatted.append(infix.charAt(i));
                lastOp = false;
            }
        }

        infix = formatted.toString();

        //Parse the expression.
        for (String token : infix.split("\\s")) {
            // Found Operator
            if (ops.containsKey(token)) {
                switch (token) {
                    case "(":
                        // Found Left Parenthesis
                        operatorStack.push(token);
                        break;
                    case ")":
                        // Found Right Parenthesis
                        while (!operatorStack.peek().equals("(")) {
                            //Append each operator until left parenthesis.
                            output.append(operatorStack.pop()).append(' ');
                            //Throw away left parenthesis
                        }
                        operatorStack.pop();
                        break;
                    default:
                        while (!operatorStack.isEmpty() && isHigerPrec(token, operatorStack.peek())) {
                            output.append(operatorStack.pop()).append(' ');
                        }
                        operatorStack.push(token);
                        break;
                }
            } else {
                // Found Digit or term.
                output.append(token).append(' ');
            }
        }

        while (!operatorStack.isEmpty()){
            output.append(operatorStack.pop()).append(' ');
        }

        System.out.println("Infix: " + infix);
        System.out.println("Postfix(RPN): " + output.toString());
        return output.toString().trim();
    }



    public static double evaluateRPN(String RPNString){
        //Stack<Double> operandStack = new Stack<>();
        LinkedStack<Double> operandStack = new LinkedStack<>();

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
