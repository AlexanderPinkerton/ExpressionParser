package com.alexpinkerton;

/**
 * Created by Alex Pinkerton on 11/30/2015.
 */
public class Node{

    public enum NodeType {EVALUATE, CONSTANT }

    NodeType type;
    Node leftChild;
    Node rightChild;

    String operator;
    double value;

    //=============================================================

    public Node(double value){
        type = NodeType.CONSTANT;
        this.value = value;
    }

    public Node(String operator, Node left, Node right){
        type = NodeType.EVALUATE;
        this.operator = operator;
        this.leftChild = left;
        this.rightChild = right;
    }

    //=============================================================


    public double getValue() {

        double value = 666;

        switch (type){
            case CONSTANT:
                value = getValue();
                break;
            case EVALUATE:
                value = evaluateExpression();
                break;
        }

        return value;
    }



    private double evaluateExpression(){

        double value = 666;

        switch (operator){
            case "+":
                value = leftChild.getValue() + rightChild.getValue();
                break;
            case "-":
                //TODO Handle left to right only.
                value = leftChild.getValue() - rightChild.getValue();
                break;
            case "/":
                //TODO Handle left to right only.
                value = leftChild.getValue() / rightChild.getValue();
                break;
            case "*":
                value = leftChild.getValue() * rightChild.getValue();
                break;
            case "^":
                //TODO Handle left to right only.
                value = Math.pow(leftChild.getValue(),rightChild.getValue());
                break;
            case "f(x)":
                break;

        }

        return value;
    }


    //=============================================================

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
