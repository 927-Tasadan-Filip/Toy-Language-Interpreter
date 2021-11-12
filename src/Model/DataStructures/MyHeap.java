package Model.DataStructures;

import UserDefinedExceptions.MyException;

import java.util.HashMap;
import java.util.Scanner;

public class MyHeap<V> implements MyIHeap<V> {
    private HashMap<Integer,V> dictionary;
    private Integer first_free_key;
    public MyHeap() {
        dictionary = new HashMap<Integer,V>();
        first_free_key = 1;
    }

    @Override
    public boolean isEmpty() {
        return dictionary.isEmpty();
    }

    @Override
    public boolean isDefined(Integer key) {
        if(dictionary.get(key) != null)
            return true;
        return false;

    }

    @Override
    public V lookup(Integer key) throws MyException {
        V ret_val = dictionary.get(key);
        if(ret_val == null) {
            throw new MyException("There is no element with the given key.");
        } else {
            return ret_val;
        }
    }

    @Override
    public void update(Integer key, V elem) {
        dictionary.put(key, elem);
    }

    @Override
    public Integer update(V elem) {
        Integer aux_key;
        aux_key = first_free_key;
        dictionary.put(first_free_key, elem);
        first_free_key = first_free_key + 1;
        return aux_key;
    }

    @Override
    public V remove(Integer key) {
        return dictionary.remove(key);
    }

    @Override
    public String toString() {
        String dictionary_string = new String();
        dictionary_string = "";
        for(Integer key: dictionary.keySet()) {
            dictionary_string += key.toString() + "->";
            dictionary_string += dictionary.get(key).toString() + "\n";
        }

        return dictionary_string;
    }

    @Override
    public String keysToString() {
        String dictionary_string = new String();
        for(Integer key: dictionary.keySet()) {
            dictionary_string += key.toString() + "\n";
        }
        return dictionary_string;
    }
}



