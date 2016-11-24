import java.util.*;

/**
 * Created by a-morenets on 22.11.2016.
 */
public class MyLinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public MyLinkedList() {
        head = new Node<>();
        tail = new Node<>();

        head.setNext(tail);
        tail.setPrev(head);

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
        Node<E> current = head.getNext();
        if (o == null)
            while (current.getNext() != null) {
                if (current.getElement() == null)
                    return true;
                current = current.getNext();
            }
        else
            while (current.getNext() != null) {
                if (o.equals(current.getElement()))
                    return true;
                current = current.getNext();
            }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>();
    }

    @Override
    public Object[] toArray() {
        Object[] a = new Object[size];
        Node<E> current = head.getNext();
        for (int i = 0; i < size; i++) {
            a[i] = current.getElement();
            current = current.getNext();
        }
        return a;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) new Object[size];
        }

        Node<E> current = head.getNext();
        for (int i = 0; i < size; i++) {
            a[i] = (T) current.getElement();
            current = current.getNext();
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
        newNode.setPrev(node.getPrev());
        node.getPrev().setNext(newNode);
        node.setPrev(newNode);
        newNode.setNext(node);

        size++;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Objects.requireNonNull(c);
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkRangesForAdd(index);
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return false;
    }

    @Override
    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);

        size = 0;
    }

    @Override
    public E get(int index) {
        return getNodeAt(index).getElement();
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
        Node<E> current = head.getNext();
        while(current.getNext() != null) {
            if (counter++ == index)
                return current;
            current = current.getNext();
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = getNodeAt(index);
        E prevElement = node.getElement();
        node.setElement(element);

        return prevElement;
    }

    @Override
    public void add(int index, E element) {
        checkRangesForAdd(index);
        insertNodeBefore(getNodeAt(index), element);
    }

    @Override
    public boolean remove(Object o) {
        Node<E> current = head.getNext();
        while (current.getNext() != null) {
            if (o == null && current.getElement() == null
                    || o != null && o.equals(current.getElement())) {
                removeNode(current);
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    @Override
    public E remove(int index) {
        return removeNodeAt(index).getElement();
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
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        size--;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        Node<E> current = head.getNext();
        while(current.getNext() != null) {
            if (o == null) {
                if (current.getElement() == null)
                    return index;
            } else if (o.equals(current.getElement()))
                return index;
            index++;
            current = current.getNext();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = 0;
        Node<E> current = tail.getPrev();
        while (current.getPrev() != null) {
            if (o == null) {
                if (current.getElement() == null)
                    return (size - 1) - index;
            } else if (o.equals(current.getElement()))
                return (size - 1) - index;
            index++;
            current = current.getPrev();
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyListIterator<>(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyListIterator<>(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    /**
     * Inner class MyIterator
     * @param <E>
     */
    private class MyIterator<E> implements Iterator<E> {
        int cursor;
        int lastRet = -1;

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
//                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * Inner class MyListIterator
     */
    private class MyListIterator<E> extends MyIterator<E> implements ListIterator<E> {
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
            return false;
        }

        @Override
        public E next() {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public E previous() {
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
        public void remove() {

        }

        @Override
        public void set(E e) {

        }

        @Override
        public void add(E e) {

        }
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

        // Getters & Setters

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(this.getClass().equals(o.getClass())))
            return false;

        Iterator<E> e1 = iterator();
        Iterator<?> e2 = ((List<?>) o).iterator();
        while (e1.hasNext() && e2.hasNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1==null ? o2==null : o1.equals(o2)))
                return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }

    @Override
    public int hashCode() {
        int result = head != null ? head.hashCode() : 0;
        result = 31 * result + (tail != null ? tail.hashCode() : 0);
        result = 31 * result + size;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head.getNext();
        while (current.getNext() != null) {
            sb.append(current.getElement());
            if (current.getNext().getNext() != null)
                sb.append(", ");
            current = current.getNext();
        }

        return sb.toString() + "]";
    }
}
