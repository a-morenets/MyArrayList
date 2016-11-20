import java.util.*;
import java.util.function.Consumer;

/**
 * Created by a-morenets on 20.11.2016.
 */
public class MyArrayList<E> implements List<E> {
    /** Default initial capacity */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /** Array for data */
    private E[] data;

    /** Size of this list */
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
     * Returns new array containing all elements of this list
     * @return new array containing all elements of this list
     */
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    /**
     * Copies all elements of this list to given array
     * If this list's size is greater than array's size, new array is created to fit all list elements
     * @param a      given array
     * @param <T>    given type of elements
     * @return array containing all elements of this list
     */
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(data, size, a.getClass());
        System.arraycopy(data, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;    }

    /**
     * Appends given element to the end of this list
     * @param t    element to be appended
     * @return true if element appended successfully, false otherwise
     */
    public boolean add(E t) {
        ensureCapacityInternal(size + 1);
        data[size++] = t;
        return true;
    }

    /**
     * Removes first occurrence of given element from this list
     * @param o    element to be removed
     * @return true if element removed successfully
     */
    public boolean remove(Object o) {
        if (o == null)
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    doRemove(i);
                    return true;
                }
            }
        else
            for (int i = 0; i < size; i++) {
                if (data[i].equals(o)) {
                    doRemove(i);
                    return true;
                }
            }
        return false;
    }

    /**
     * Helper method - removes element at given index
     * @param index    index of element to be removed
     */
    private void doRemove(int index) {
        int numToShift = size - index - 1;
        if (numToShift > 0) {
            System.arraycopy(data, index + 1, data, index, numToShift);
            data[--size] = null;
        }
    }

    public boolean containsAll(Collection<?> c) {
        //TODO
        return false;
    }

    /**
     * Appends all elements of given collection to this list
     * @param c    given collection
     * @return true if all elements appended, false if collection is empty
     */
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0)
                return false;

        E[] cArr = (E[]) c.toArray();
        int cArrLen = cArr.length;
        ensureCapacityInternal(size + cArrLen);
        System.arraycopy(cArr, 0, data, size, cArrLen);
        size += cArrLen;
        return true;
    }

    /**
     * Appends all elements of given collection to this list
     * @param c         given collection
     * @param index     starting index of inserted collection
     * @return true if all elements appended, false if collection is empty
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        checkRanges(index);
        if (c.size() == 0)
            return false;

        E[] cArr = (E[]) c.toArray();
        int cArrLen = cArr.length;
        ensureCapacityInternal(size + cArrLen);

        int numToShift = size - index;
        if (numToShift > 0)
            System.arraycopy(data, index, data, index + cArrLen, numToShift);

        System.arraycopy(cArr, 0, data, index, cArrLen);
        size += cArrLen;
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        //TODO
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        //TODO
        return false;
    }

    /**
     * Clears this list
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    /**
     * Returns element at given index
     * @param index
     * @return element at given index
     */
    public E get(int index) {
        checkRanges(index);

        return data[index];
    }

    /**
     * Helper method - Checks whether index is out of bounds
     * @param index    index to be checked
     */
    private void checkRanges(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index = " + index + ", size = " + size);
    }

    /**
     * Replaces the element at the specified position in this list with the specified element
     * @param index
     * @param element
     * @return the element previously at the specified position
     */
    public E set(int index, E element) {
        checkRanges(index);
        E oldElement = data[index];
        data[index] = element;

        return oldElement;
    }

    /**
     * Inserts element at given index in this list
     * @param index      position of new element
     * @param element    element to be added ti list
     */
    public void add(int index, E element) {
        checkRanges(index);
        ensureCapacityInternal(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);
    }

    /**
     * Increases the capacity of this <tt>ArrayList</tt> instance, if
     * necessary, to ensure that it can hold at least the number of elements
     * specified by the minimum capacity argument.
     *
     * @param   minCapacity   the desired minimum capacity
     */
    public void ensureCapacity(int minCapacity) {
        int minExpand = (data.length > 0)
                // any size if not default element table
                ? 0
                // larger than default for default empty table. It's already
                // supposed to be at default size.
                : DEFAULT_CAPACITY;

        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

    private void ensureCapacityInternal(int minCapacity) {
        if (data.length == 0) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        // overflow-conscious code
        if (minCapacity - data.length > 0)
            grow(minCapacity);
    }

    /**
     * Increases the capacity to ensure that it can hold at least the
     * number of elements specified by the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        data = Arrays.copyOf(data, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     * Removes element of this list at given index
     * @param index    index of element to be removed
     * @return removed element or null if no such element contains in this list
     */
    public E remove(int index) {
        checkRanges(index);

        E removedElement = data[index];
        doRemove(index);

        return removedElement;
    }

    /**
     * Returns index of first occurrence of given element in this list
     * @param o    element to be found
     * @return index of first occurrence of given element in this list or -1 if no such element was found
     */
    public int indexOf(Object o) {
        if (o == null)
            for (int i = 0; i < size; i++) {
                if (data[i] == null)
                    return i;
            }
        else
            for (int i = 0; i < size; i++) {
                if (data[i].equals(o))
                    return i;
            }
        return -1;
    }

    /**
     * Returns index of last occurrence of given element in this list
     * @param o    element to be found
     * @return index of last occurrence of given element in this list or -1 if no such element was found
     */
    public int lastIndexOf(Object o) {
        if (o == null)
            for (int i = size - 1; i >= 0; i--) {
                if (data[i] == null)
                    return i;
            }
        else
            for (int i = size - 1; i >= 0; i--) {
                if (data[i].equals(o))
                    return i;
            }
        return -1;
    }

    /**
     * Creates new list iterator over this list
     * @return list iterator over this list
     */
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    /**
     * Creates new list iterator over this list starting from given index
     * @param index
     * @return
     */
    public ListIterator<E> listIterator(int index) {
        checkRanges(index);
        return new ListItr(index);
    }

    public List<E> subList(int fromIndex, int toIndex) {
        //TODO
        return null;
    }

    /**
     * Iter class
     */
    private class Itr implements Iterator {
        int cursor; // index of next element to return

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
        private final int cursor;

        public ListItr(int index) {
            super();
            cursor = index;
        }

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
