package com.alexpinkerton;

/**
 * Created by Alexander Pinkerton on 12/6/2015.
 */
public class LinkedStack<U> implements StackADT<U>{

    StackNode top;
    int stackQuantity = 0;


    @Override
    public void push(U object) {
        if(isEmpty()){
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

        if(!isEmpty()){
            stackQuantity--;
            U oldTop = top.getObject();
            top = top.getBelow();
            return oldTop;
        }

        return null;
    }

    @Override
    public U peek() {

        if(!isEmpty()){
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


    public String toString(){
        StackNode current = top;
        StringBuffer output = new StringBuffer();
        for(int i=0;i<stackQuantity;i++){
            output.append(current.getObject()).append(", ");
            current = current.getBelow();
        }
        return output.toString();
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
