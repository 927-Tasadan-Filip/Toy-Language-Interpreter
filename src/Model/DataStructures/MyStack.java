package Model.DataStructures;

import UserDefinedExceptions.MyException;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{
    private Stack<T> stack;

    public MyStack() {
        stack = new Stack<T>();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public T pop() throws MyException {
        if(stack.isEmpty()) {
            throw new MyException("Empty stack.");
        }
        return stack.pop();

    }

    @Override
    public T top() throws MyException{
        if(stack.isEmpty()) {
            throw new MyException("Empty stack.");
        }
        return stack.peek();
    }

    @Override
    public String toString() {
        String stack_string = "";
        if(!(stack.isEmpty())) {
            try {
                stack_string = " [ " + top().toString() + " ]";
            } catch (MyException ignored) {}
        }
        else {
            stack_string = " []";
        }
        return stack_string;
    }

    @Override
    public int size() {
        return stack.size();
    }
}
