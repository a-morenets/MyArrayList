package my_linkedlist;

import java.util.*;

/**
 * LinkedList implementation
 * Created by a-morenets on 22.11.2016.
 * 
 * To avoid the special case of inserting and removing from the empty list this doubly linked list
 * uses a dummy header and trailer nodes. These nodes are created when the list is created and are NOT
 * parts of the list. An empty list is one which only contains the dummy header and trailer nodes connected.
 */
public class MyLinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private int modCount;

    public MyLinkedList() {
        head = new Node<>();
        tail = new Node<>();

        head.next = tail;
        tail.prev = head;

        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<E> current = head.next;
        if (o == null)
            while (current.next != null) {
                if (current.element == null)
                    return true;
                current = current.next;
            }
        else
            while (current.next != null) {
                if (o.equals(current.element))
                    return true;
                current = current.next;
            }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] a = new Object[size];
        Node<E> current = head.next;
        for (int i = 0; i < size; i++) {
            a[i] = current.element;
            current = current.next;
        }
        return a;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) new Object[size];
        }

        Node<E> current = head.next;
        for (int i = 0; i < size; i++) {
            a[i] = (T) current.element;
            current = current.next;
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        return insertNodeBefore(tail, e);
    }

    /**
     * Helper method - inserts node before specified node
     * @param node
     * @param element
     * @return
     */
    private boolean insertNodeBefore(Node<E> node, E element) {
        Node<E> newNode = new Node<>(element);
        newNode.prev = node.prev;
        node.prev.next = newNode;
        node.prev = newNode;
        newNode.next = node;

        size++;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);
        // TODO
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Objects.requireNonNull(c);
        for (Object o : c) {
            add((E) o);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkRangesForAdd(index);
        Objects.requireNonNull(c);
        // TODO
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        // TODO
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        // TODO
        return false;
    }

    @Override
    public void clear() {
        head.next = tail;
        tail.prev = head;

        size = 0;
    }

    @Override
    public E get(int index) {
        return getNodeAt(index).element;
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
     * Helper method
     * @param index    index of Node to be returned
     * @return Node at index or null
     */
    private Node<E> getNodeAt(int index) {
        checkRanges(index);
        int counter = 0;
        Node<E> current = head.next;
        while(current.next != null) {
            if (counter++ == index)
                return current;
            current = current.next;
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        checkRanges(index);
        Node<E> node = getNodeAt(index);
        E prevElement = node.element;
        node.element = element;

        return prevElement;
    }

    @Override
    public void add(int index, E element) {
        checkRangesForAdd(index);
        insertNodeBefore(getNodeAt(index), element);
    }

    @Override
    public boolean remove(Object o) {
        Node<E> current = head.next;
        while (current.next != null) {
            if (o == null && current.element == null
                    || o != null && o.equals(current.element)) {
                removeNode(current);
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public E remove(int index) {
        return removeNodeAt(index).element;
    }

    /**
     * Helper method - removes Node at specified index and returns removed Node
     * @param index
     * @return
     */
    private Node<E> removeNodeAt(int index) {
        checkRanges(index);
        Node<E> removedNode = getNodeAt(index);
        removeNode(removedNode);

        return removedNode;
    }

    /**
     * Helper method - removes specified Node
     * @param node
     */
    private void removeNode(Node<E> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        Node<E> current = head.next;
        while(current.next != null) {
            if (o == null) {
                if (current.element == null)
                    return index;
            } else if (o.equals(current.element))
                return index;
            index++;
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = 0;
        Node<E> current = tail.prev;
        while (current.prev != null) {
            if (o == null) {
                if (current.element == null)
                    return (size - 1) - index;
            } else if (o.equals(current.element))
                return (size - 1) - index;
            index++;
            current = current.prev;
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyListIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyListIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    /**
     * Inner class MyIterator
     */
    private class MyIterator implements Iterator<E> {
        protected int cursor;
        private int lastRet = -1;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();

            cursor = i + 1;
            return (E) MyLinkedList.this.get(lastRet = i);
        }

        @Override
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();

            try {
                MyLinkedList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * Inner class MyListIterator
     */
    private class MyListIterator extends MyIterator implements ListIterator<E> {
        /**
         * Constructor
         * @param index
         */
        public MyListIterator(int index) {
            super();
            cursor = index;
        }

        @Override
        public boolean hasNext() {
            // TODO
            return false;
        }

        @Override
        public E next() {
            // TODO
            return null;
        }

        @Override
        public boolean hasPrevious() {
            // TODO
            return false;
        }

        @Override
        public E previous() {
            // TODO
            return null;
        }

        @Override
        public int nextIndex() {
            // TODO
            return 0;
        }

        @Override
        public int previousIndex() {
            // TODO
            return 0;
        }

        @Override
        public void remove() {
            // TODO
        }

        @Override
        public void set(E e) {
            // TODO
        }

        @Override
        public void add(E e) {
            // TODO
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof List))
            return false;

        List<?> that = (List<?>) o;
        if (size != that.size())
            return false;

        Node<E> current = head.next;
        for (int i = 0; i < size; i++) {
            if (!(current.element == null ? that.get(i) == null : current.element.equals(that.get(i))))
                return false;
            current = current.next;
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
        Node<E> current = head.next;
        while (current.next != null) {
            sb.append(current.element);
            if (current.next.next != null)
                sb.append(", ");
            current = current.next;
        }

        return sb.toString() + "]";
    }

    /**
     * Inner class represents one node
     * @param <E>
     */
    private class Node<E> {
        /** Previous Node object */
        private Node<E> prev;
        /** Next Node object */
        private Node<E> next;
        /** Data element */
        private E element;

        /**
         * Default constructor
         */
        public Node() {
            // empty
        }

        /**
         * Constructor
         * @param element    data element
         */
        public Node(E element) {
            this.element = element;
        }

        /**
         * Constructor
         * @param prev
         * @param next
         * @param element
         */
        public Node(Node<E> prev, Node<E> next, E element) {
            this.prev = prev;
            this.next = next;
            this.element = element;
        }
    }
}
