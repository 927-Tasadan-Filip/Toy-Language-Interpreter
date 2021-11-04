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

    public Stack<T> getStack() {
        return stack;
    }

    public void setStack(Stack<T> stack) {
        this.stack = stack;
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
    public MyStack<T> shallowCopy() {
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
    public String toString() {
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
    public int size() {
        return stack.size();
    }
}
