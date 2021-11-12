package Model.DataStructures;

import UserDefinedExceptions.MyException;

import java.util.HashMap;
import java.util.Map;

public interface MyIHeap<V> {
    void setContent(Map<Integer,V> new_dict);
    HashMap<Integer, V> getContent();
    boolean isEmpty();
    boolean isDefined(Integer key);
    V lookup(Integer key) throws MyException;
    void update(Integer key, V elem);
    Integer update(V elem);
    V remove(Integer key);
    String toString();
    String keysToString();
}
