package com.alexpinkerton;

/**
 * Created by Alexander Pinkerton on 12/6/2015.
 */
public class LinkedStack<U> implements StackADT<U>{

    StackNode top;
    int stackQuantity = 0;


    @Override
    public void push(U object) {
        if(stackQuantity == 0){
            //The stack is empty
            top = new StackNode(object);
        }else{
            //The stack is not empty, Move the current top below the new node.
            StackNode newTop = new StackNode(object);
            newTop.setBelow(top);
            top = newTop;
        }
        stackQuantity++;
    }

    @Override
    public U pop() {

        if(stackQuantity != 0){
            stackQuantity--;
            U oldTop = top.getObject();
            top = top.getBelow();
            return oldTop;
        }

        return null;
    }

    @Override
    public U peek() {

        if(stackQuantity != 0){
            return top.getObject();
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return (stackQuantity == 0);
    }

    @Override
    public int size() {
        return 0;
    }



    private class StackNode{

        StackNode below;
        U object;

        public StackNode(U object){
            this.object = object;
        }

        public StackNode getBelow() {
            return below;
        }

        public void setBelow(StackNode below) {
            this.below = below;
        }

        public U getObject() {
            return object;
        }

        public void setObject(U object) {
            this.object = object;
        }
    }

}
