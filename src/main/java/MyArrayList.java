import java.util.*;

/**
 * Created by a-morenets on 20.11.2016.
 */
public class MyArrayList<E> implements List<E> {
    /** Default initial capacity */
    private static final int DEFAULT_CAPACITY = 10;

    private E[] data;
    private int size;

    /**
     * Constructs this list with default initial capacity
     */
    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs this list with given initial capacity
     * @param capacity    initial capacity
     */
    public MyArrayList(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * Returns number of elements in this list
     * @return number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Checks whether this list contains no elements
     * @return true if this list contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether this list contains given element
     * @param o    element to be checked
     * @return true if this list contains given element, false otherwise
     */
    public boolean contains(Object o) {
        if (o == null)
            for (int i = 0; i < size; i++) {
                if (data[i] == null)
                    return true;
            }
        else
            for (int i = 0; i < size; i++) {
                if (data[i].equals(o))
                        return true;
            }
        return false;
    }

    /**
     * Creates an iterator over this list
     * @return iterator over this list
     */
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     *
     * @return
     */
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    public boolean add(E t) {
        return false;
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {

    }

    public E get(int index) {
        return null;
    }

    public E set(int index, E element) {
        return null;
    }

    public void add(int index, E element) {

    }

    public E remove(int index) {
        return null;
    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<E> listIterator() {
        return null;
    }

    public ListIterator<E> listIterator(int index) {
        return null;
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    /**
     * Iter class
     */
    private class Itr implements Iterator {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }

        @Override
        public void remove() {

        }
    }

    /**
     * ListIter class
     */
    private class ListItr extends Itr implements ListIterator {

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public Object previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void set(Object o) {

        }

        @Override
        public void add(Object o) {

        }
    }
}
