package Model.DataStructures;

import UserDefinedExceptions.MyException;

import java.util.HashMap;
import java.util.Scanner;

public class MyDictionary<K,V> implements MyIDictionary<K,V> {
    private HashMap<K,V> dictionary;

    public MyDictionary() {
        dictionary = new HashMap<K,V>();
    }

    @Override
    public boolean isEmpty() {
        return dictionary.isEmpty();
    }

    @Override
    public boolean isDefined(K key) {
        if(dictionary.get(key) != null)
            return true;
        return false;

    }

    @Override
    public V lookup(K key) throws MyException {
        try{
            return dictionary.get(key);
        }
        catch (Exception exc) {
            throw new MyException("variable x is not defined");
        }
    }

    @Override
    public void update(K key, V elem) {
        dictionary.put(key, elem);
    }

    @Override
    public V remove(K key) {
        return dictionary.remove(key);
    }

    @Override
    public String toString() {
        String dictionary_string = new String();
        dictionary_string = "[ ";
        for(K key: dictionary.keySet()) {
            dictionary_string += key.toString() + "->";
            dictionary_string += dictionary.get(key).toString() + " ";
        }
        dictionary_string = "]";
        return dictionary_string;
    }
}
