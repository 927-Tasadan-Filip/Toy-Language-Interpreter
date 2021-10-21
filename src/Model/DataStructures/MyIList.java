package Model.DataStructures;

public interface MyIList<T> {
    boolean isEmpty();
    void append(T elem);
    void addAtPos(int pos, T elem);
    T remove(int pos);
    T getElem(int pos);

}
