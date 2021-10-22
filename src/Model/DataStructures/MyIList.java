package Model.DataStructures;

import UserDefinedExceptions.MyException;

public interface MyIList<T> {
    boolean isEmpty();
    void append(T elem);
    void addAtPos(int pos, T elem) throws MyException;
    T remove(int pos) throws MyException;
    T getElem(int pos) throws MyException;
    String toString();
}
