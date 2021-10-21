package Model.DataStructures;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T>{
    private ArrayList<T> list;

    public MyList() {
        list = new ArrayList<T>();
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
    public void addAtPos(int pos, T elem) {
        list.add(pos, elem);
    }

    @Override
    public T remove(int pos) {
        return list.remove(pos);
    }

    @Override
    public T getElem(int pos) {
        return list.get(pos);
    }
}
