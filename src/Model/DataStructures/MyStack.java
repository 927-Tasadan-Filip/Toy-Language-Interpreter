package Model.DataStructures;

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
    public T pop() {
        return stack.pop();
    }

    @Override
    public T top() {
        return stack.peek();
    }

    @Override
    public String toString() {
        String stack_string;
        if(!(stack.isEmpty())) {
            stack_string = " [ " + top().toString() + " ]";
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
