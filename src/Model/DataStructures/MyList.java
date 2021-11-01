package Model.DataStructures;

import UserDefinedExceptions.MyException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyList<T> implements MyIList<T>{
    private LinkedList<T> list;

    public MyList() {
        list = new LinkedList<T>();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void append(T elem) {
        list.add(elem);
    }

    @Override
    public void addAtPos(int pos, T elem) throws MyException {
        if(pos < 0 || pos >= list.size()) {
            throw new MyException("Position out of bounds.");
        }
        list.add(pos, elem);
    }

    @Override
    public T remove(int pos) throws MyException{
        if(pos < 0 || pos >= list.size()) {
            throw new MyException("Position out of bounds.");
        }
        return list.remove(pos);

    }

    @Override
    public T getElem(int pos) throws MyException{
        if(pos < 0 || pos >= list.size()) {
            throw new MyException("Position out of bounds.");
        }
        return list.get(pos);
    }

    @Override
    public String toString() {
        String list_string = new String();
        list_string = "[ ";
        int i = 0;
        for (T t : list) {
            list_string += t;

            if (i < list.size() - 1) {
                list_string += ", ";
            }
             i++;
        }
        list_string += " ]";
        return list_string;
    }
}
