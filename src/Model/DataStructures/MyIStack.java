package Model.DataStructures;

public interface MyIStack<T>{
    boolean isEmpty();
    void push(T v);
    T pop();
    T top();
}
