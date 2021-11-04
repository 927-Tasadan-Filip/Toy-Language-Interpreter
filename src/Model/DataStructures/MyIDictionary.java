package Model.DataStructures;

import UserDefinedExceptions.MyException;

import java.util.HashMap;

public interface MyIDictionary<K, V> {
    boolean isEmpty();
    boolean isDefined(K key);
    V lookup(K key) throws MyException;
    void update(K key, V elem);
    V remove(K key);
    String toString();
    String keysToString();
}
