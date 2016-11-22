import java.util.*;
import java.util.function.Consumer;

/**
 * Created by a-morenets on 20.11.2016.
 */
public class MyArrayList<E> implements List<E> {
    /** Default initial capacity */
    private static final int DEFAULT_CAPACITY = 10;

    /** Array for data */
    private E[] data;

    /** Size of this list */
    private int size;

    /** Modifications counter */
    private int modCount;

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
                if (o.equals(data[i]))
                        return true;
            }
        return false;
    }

    /**
     * Creates an iterator over this list
     * @return iterator over this list
     */
    public Iterator<E> iterator() {
        return new MyIterator();
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
        return a;
    }

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
     * Inserts element at given index in this list
     * @param index      position of new element
     * @param element    element to be added ti list
     * @throws IndexOutOfBoundsException if index out of range
     */
    public void add(int index, E element) {
        checkRangesForAdd(index);
        ensureCapacityInternal(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
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
                if (o.equals(data[i])) {
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
        modCount++;
        int numToShift = size - index - 1;
        if (numToShift > 0)
            System.arraycopy(data, index + 1, data, index, numToShift);
        data[--size] = null;
    }

    /**
     * Removes element of this list at given index
     * @param index    index of element to be removed
     * @return removed element or null if no such element contains in this list
     */
    public E remove(int index) {
        checkRanges(index);

//        modCount++;
        E removedElement = data[index];
        doRemove(index);

        return removedElement;
    }

    /**
     * Checks whether this list contains all elements of given collection
     * @param c    given collection
     * @return true if this list contains all elements of given collection
     */
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o))
                return false;
        }
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
        checkRangesForAdd(index);
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

    /**
     * Removes from this list all elements that contains given collection
     * @param c    given collection
     * @return true if elements were removed successfully
     */
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
        modCount++;
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
     * Helper method for add() and addAll() - Checks whether index is out of bounds
     * @param index    index to be checked
     */
    private void checkRangesForAdd(int index) {
        if (index < 0 || index > size) // > instead of >=
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
     * Trims the capacity of this list to be the list's current size
     */
    public void trimToSize() {
        modCount++;
        if (size < data.length) {
            data = (size == 0)
                    ? (E[]) new Object[] {}
                    : Arrays.copyOf(data, size);
        }
    }

    /**
     * Increases the capacity of this list, if necessary,
     * to ensure that it can hold at least the number of elements
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
        modCount++;
        // overflow-conscious code
        if (minCapacity - data.length > 0)
            grow(minCapacity);
    }

    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

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
                if (o.equals(data[i]))
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
                if (o.equals(data[i]))
                    return i;
            }
        return -1;
    }

    /**
     * Creates new list iterator over this list
     * @return list iterator over this list
     */
    public ListIterator<E> listIterator() {
        return new MyListIterator(0);
    }

    /**
     * Creates new list iterator over this list starting from given index
     * @param index
     * @return
     */
    public ListIterator<E> listIterator(int index) {
        checkRanges(index);
        return new MyListIterator(index);
    }

    public List<E> subList(int fromIndex, int toIndex) {
        //TODO
        return null;
    }

    /**
     * Iter class
     */
    private class MyIterator implements Iterator<E> {
        int cursor;         // index of next element to return
        int lastRet = -1;   // index of last element returned; -1 if no such
        int expectedModCount = modCount; // modifications counter must be equal to modCount of this list

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            E[] elementData = MyArrayList.this.data;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return elementData[lastRet = i];
        }

        @Override
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                MyArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    /**
     * ListIter class
     */
    private class MyListIterator extends MyIterator implements ListIterator<E> {
        public MyListIterator(int index) {
            super();
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public E previous() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            E[] elementData = MyArrayList.this.data;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return elementData[lastRet = i];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        public void set(E o) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                MyArrayList.this.set(lastRet, o);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(E o) {
            checkForComodification();
            try {
                int i = cursor;
                MyArrayList.this.add(i, o);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void forEachRemaining(Consumer action) {

        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof List))
            return false;

        List<E> that = (List<E>) o;
        if (size != that.size())
            return false;

        for (int i = 0; i < size; i++) {
            if (!(data[i] == null ? that.get(i) == null : data[i].equals(that.get(i))))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (E e : this)
            hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
        return hashCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(get(i));
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
