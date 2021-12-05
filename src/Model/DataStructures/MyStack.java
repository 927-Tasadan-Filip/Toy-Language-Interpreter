package Model.DataStructures;

import UserDefinedExceptions.MyException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

public class MyStack<T> implements MyIStack<T>{
    private Stack<T> stack;

    public MyStack() {
        stack = new Stack<T>();
    }

    public synchronized Stack<T> getStack() {
        return stack;
    }

    public synchronized void setStack(Stack<T> stack) {
        this.stack = stack;
    }

    @Override
    public synchronized boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public synchronized void push(T v) {
        stack.push(v);
    }

    @Override
    public synchronized T pop() throws MyException {
        if(stack.isEmpty()) {
            throw new MyException("Empty stack.");
        }
        return stack.pop();
    }

    @Override
    public synchronized T top() throws MyException{
        if(stack.isEmpty()) {
            throw new MyException("Empty stack.");
        }
        return stack.peek();
    }

    @Override
    public synchronized MyStack<T> shallowCopy() {
        MyStack<T> copy_stack = new MyStack<>();
        Stack<T> aux_stack = new Stack<>();
        LinkedList<T> buffer_list = new LinkedList<>();

        while (!this.stack.isEmpty()) {
            T elem = this.stack.pop();
            buffer_list.addLast(elem);
        }
        Iterator<T> descending_iterator = buffer_list.descendingIterator();

        while (descending_iterator.hasNext()) {
            T elem = descending_iterator.next();
            copy_stack.push(elem);
            aux_stack.push(elem);
        }

        this.stack = aux_stack;
        return copy_stack;
    }

    @Override
    public synchronized String toString() {
        MyStack<T> my_copy_stack = this.shallowCopy();
        String stack_string = "";
        while(!(my_copy_stack.isEmpty())) {
            try {
                stack_string += my_copy_stack.pop().toString() + "\n";
            } catch (MyException ignored) {}
        }
        return stack_string;
    }

    @Override
    public synchronized int size() {
        return stack.size();
    }
}
