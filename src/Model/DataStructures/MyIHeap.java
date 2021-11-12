package Model.DataStructures;

import UserDefinedExceptions.MyException;

public interface MyIHeap<V> {
    boolean isEmpty();
    boolean isDefined(Integer key);
    V lookup(Integer key) throws MyException;
    void update(Integer key, V elem);
    Integer update(V elem);
    V remove(Integer key);
    String toString();
    String keysToString();
}
