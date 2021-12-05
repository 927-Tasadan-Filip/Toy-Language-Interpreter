package Model.DataStructures;

import UserDefinedExceptions.MyException;

import java.util.HashMap;

public interface MyIDictionary<K, V> {
    void setContent(HashMap<K,V> new_dict);
    HashMap<K, V> getContent();
    boolean isEmpty();
    boolean isDefined(K key);
    V lookup(K key) throws MyException;
    void update(K key, V elem);
    V remove(K key);
    String toString();
    String keysToString();

}
