package Model.DataStructures;

import UserDefinedExceptions.MyException;

public interface MyIStack<T>{
    boolean isEmpty();
    void push(T v);
    T pop() throws MyException;
    T top() throws MyException;
    int size();
    MyIStack<T> shallowCopy();
    String toString();

}
