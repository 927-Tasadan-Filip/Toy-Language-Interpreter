package Model.DataStructures;

import UserDefinedExceptions.MyException;

import java.util.HashMap;
import java.util.Scanner;

public class MyDictionary<K,V> implements MyIDictionary<K,V> {
    private HashMap<K,V> dictionary;

    @Override
    public void setContent(HashMap<K,V> new_dict) {
        dictionary = new_dict;
    }

    @Override
    public HashMap<K, V> getContent() {
        return dictionary;
    }

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
        V ret_val = dictionary.get(key);
        if(ret_val == null) {
            throw new MyException("There is no element with the given key.");
        } else {
            return ret_val;
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
        dictionary_string = "";
        for(K key: dictionary.keySet()) {
            dictionary_string += key.toString() + "->";
            dictionary_string += dictionary.get(key).toString() + "\n";
        }

        return dictionary_string;
    }

    @Override
    public String keysToString() {
        String dictionary_string = new String();
        for(K key: dictionary.keySet()) {
            dictionary_string += key.toString() + "\n";
        }
        return dictionary_string;
    }
}
