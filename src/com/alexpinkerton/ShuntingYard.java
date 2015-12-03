package com.alexpinkerton;

/**
 * Created by Alexp on 12/2/2015.
 */
import java.util.Stack;

public class ShuntingYard {

    public static void main(String[] args) {
        //String infix = "3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3";
        //String infix = "3 * 6 + 2 ^ 5";

        String infix = "5 + 10 * 2 - 5";

        System.out.printf("infix:   %s%n", infix);
        System.out.printf("postfix: %s%n", infixToPostfix(infix));
        System.out.println(evalRPN(infixToPostfix(infix).split("\\s")));
        //System.out.println(evalRPN(new String[] {"3","6","*","2","5","^","+"}));
    }

    static String infixToPostfix(String infix) {
        final String ops = "-+/*^";
        StringBuilder sb = new StringBuilder();
        Stack<Integer> s = new Stack<>();

        for (String token : infix.split("\\s")) {
            if (token.isEmpty())
                continue;
            char c = token.charAt(0);
            int idx = ops.indexOf(c);

            // check for operator
            if (idx != -1) {
                if (s.isEmpty())
                    s.push(idx);

                else {
                    while (!s.isEmpty()) {
                        int prec2 = s.peek() / 2;
                        int prec1 = idx / 2;
                        if (prec2 > prec1 || (prec2 == prec1 && c != '^'))
                            sb.append(ops.charAt(s.pop())).append(' ');
                        else break;
                    }
                    s.push(idx);
                }
            }
            else if (c == '(') {
                s.push(-2); // -2 stands for '('
            }
            else if (c == ')') {
                // until '(' on stack, pop operators.
                while (s.peek() != -2)
                    sb.append(ops.charAt(s.pop())).append(' ');
                s.pop();
            }
            else {
                sb.append(token).append(' ');
            }
        }
        while (!s.isEmpty())
            sb.append(ops.charAt(s.pop())).append(' ');
        return sb.toString();
    }



    public static double evalRPN(String[] exp) {
        Stack<Double> stack = new Stack<Double>();

        for (int i = 0; i < exp.length; i++) {
            if (exp[i].matches("-?[\\d]+")) {
                stack.push(Double.parseDouble(exp[i]));
            } else {
                double op2 = stack.pop();
                double op1 = stack.pop();
                double result = 0;
                String operator = exp[i];
                if (operator.equals("+")) result = op1 + op2;
                else if (operator.equals("-")) result = op1 - op2;
                else if (operator.equals("*")) result = op1 * op2;
                else if (operator.equals("/")) result = op1 / op2;
                else if (operator.equals("^")) result = Math.pow(op2,op1);
                stack.push(result);
            }
        }
        return stack.pop();
    }

}
