package doublyLinkedList;

import java.util.Iterator;
import java.util.ListIterator;

public interface IndexedUnsortedList<T> {
    void addToFront(T element);

    void addToRear(T element);

    void add(T element);

    void addAfter(T element, T target);

    void add(int index, T element);

    T removeFirst();

    T removeLast();

    T remove(T element);

    T remove(int index);

    void set(int index, T element);

    T get(int index);

    int indexOf(T element);

    T first();

    T last();

    boolean contains(T target);

    boolean isEmpty();

    int size();

    Iterator<T> iterator();

    ListIterator<T> listIterator();

    ListIterator<T> listIterator(int startingIndex);

    @Override
    String toString();
}
