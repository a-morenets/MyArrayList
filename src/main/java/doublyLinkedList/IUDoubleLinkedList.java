package doublyLinkedList;

import java.util.*;

/**
 * Double linked list that implement IndexedUnsortedList.
 * This List is Iterable, Indexed, Unsorted.
 *
 * @param <T> - class of objects stored in the list
 */
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {

    private DLLNode<T> first; //first node in list
    private DLLNode<T> last;  //last node in list
    private int size;
    private int modCount; //The number of times this list has been structurally modified

    @Override
    public void addToFront(T element) {
        DLLNode<T> newNode = new DLLNode<>(element, null, first);
        if (isEmpty())
            last = newNode;
        else first.setPrev(newNode);
        first = newNode;
        size++;
        modCount++;
    }

    @Override
    public void addToRear(T element) {
        DLLNode<T> newNode = new DLLNode<>(element, last, null);
        if (isEmpty())
            first = newNode;
        else last.setNext(newNode);
        last = newNode;
        size++;
        modCount++;
    }

    @Override
    public void add(T element) {
        addToRear(element);
    }

    @Override
    public void addAfter(T element, T target) {
        if (isEmpty()) throw new NoSuchElementException();

        DLLNode<T> curNode = first;
        //find element
        while (!curNode.getElement().equals(target)) {
            if (curNode.getNext() == null) throw new NoSuchElementException();
            curNode = curNode.getNext();
        }
        add(element, curNode);
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == 0) addToFront(element);
        else if (index == size) addToRear(element);
        else {
            DLLNode<T> curNode = first;
            int pos = 1;
            //find element by index
            while (pos != index) {
                curNode = curNode.getNext();
                pos++;
            }
            add(element, curNode);
        }
    }

    /**
     * adds element after current node
     *
     * @param element the element to be inserted into the list
     * @param node    node after which an item must be inserted
     */
    private void add(T element, DLLNode<T> node) {
        DLLNode<T> newNode = new DLLNode<>(element, node, node.getNext());
        if (node.getNext() == null) {
            last = newNode;
        } else {
            node.getNext().setPrev(newNode);
        }
        node.setNext(newNode);
        size++;
        modCount++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        T removedElement = first.getElement();
        first = first.getNext();
        if (size == 1) last = null;
        else first.setPrev(null);
        size--;
        modCount++;
        return removedElement;
    }

    @Override
    public T removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        T removedElement = last.getElement();
        last = last.getPrev();
        if (size == 1) first = null;
        else last.setNext(null);
        size--;
        modCount++;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        if (isEmpty()) throw new NoSuchElementException();
        DLLNode<T> curNode = first;
        //iterate list until the item is found
        while (!curNode.getElement().equals(element)) {
            if (curNode.getNext() == null) throw new NoSuchElementException();
            curNode = curNode.getNext();
        }
        return remove(curNode);
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        DLLNode<T> curNode = first;
        int pos = 0;
        //iterate list until an item with this index has been found
        while (pos != index) {
            curNode = curNode.getNext();
            pos++;
        }
        return remove(curNode);
    }

    /**
     * Removes node from list
     *
     * @param node node that will be deleted
     * @return removed element
     */
    private T remove(DLLNode<T> node) {
        T removedElement = node.getElement();
        if (node.getPrev() == null) first = node.getNext();
        else node.getPrev().setNext(node.getNext());
        if (node.getNext() == null) last = node.getPrev();
        else node.getNext().setPrev(node.getPrev());
        size--;
        modCount++;
        return removedElement;
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        DLLNode<T> curNode = first;
        int pos = 0;
        //iterate list until an item with this index has been found
        while (pos != index) {
            curNode = curNode.getNext();
            pos++;
        }
        //set element to node with this index
        curNode.setElement(element);
        modCount++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        DLLNode<T> curNode = first;
        int pos = 0;
        //iterate list until an item with this index has been found
        while (pos != index) {
            curNode = curNode.getNext();
            pos++;
        }
        return curNode.getElement();
    }

    @Override
    public int indexOf(T element) {
        DLLNode<T> curNode = first;
        int pos = 0;
        //iterate list until the item is found
        while (pos != size) {
            if (curNode.getElement().equals(element)) return pos;
            curNode = curNode.getNext();
            pos++;
        }
        return -1;
    }

    @Override
    public T first() {
        if (isEmpty()) throw new NoSuchElementException();
        return first.getElement();
    }

    @Override
    public T last() {
        if (isEmpty()) throw new NoSuchElementException();
        return last.getElement();
    }

    @Override
    public boolean contains(T target) {
        DLLNode<T> curNode = first;
        //iterate list until the item is found
        while (curNode != null) {
            if (curNode.getElement().equals(target)) return true;
            curNode = curNode.getNext();
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new IUListIterator(0);
    }

    @Override
    public ListIterator<T> listIterator() {
        return new IUListIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        return new IUListIterator(startingIndex);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        DLLNode<T> curNode = first;
        while (curNode != null) {
            sb.append(curNode.getElement());
            if (curNode.getNext() == null) break;
            curNode = curNode.getNext();
            sb.append(", ");
        }
        for (int i = 0; i < size; i++) {
            sb.append(get(i));
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        return sb.toString() + "]";
    }

    private class IUListIterator implements ListIterator<T> {
        DLLNode<T> next; //next element to return
        DLLNode<T> lastRet; // last element returned; null if no such
        int cursor;         // index of next element to return
        int expectedModCount = modCount; // modifications counter must be equal to modCount of this list

        public IUListIterator(int startingIndex) {
            cursor = startingIndex;
            lastRet = null;
            if (startingIndex < 0) startingIndex = 0;
            if (startingIndex >= 0 && startingIndex < size) {
                int pos = 0;
                DLLNode<T> curNode = first;
                while (pos != startingIndex) {
                    curNode = curNode.getNext();
                    pos++;
                }
                next = curNode;
            } else next = null;
        }

        @Override
        public boolean hasNext() {
            checkForComodification();
            return cursor < size;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            lastRet = next;
            next = next.getNext();
            cursor++;
            return lastRet.getElement();
        }

        @Override
        public boolean hasPrevious() {
            checkForComodification();
            return cursor > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            lastRet = next = (next == null) ? last : next.getPrev();
            cursor--;
            return lastRet.getElement();
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {
            checkForComodification();
            if (lastRet == null)
                throw new IllegalStateException();
            if (next == lastRet)
                next = lastRet.getNext();
            else
                cursor--;
            IUDoubleLinkedList.this.remove(lastRet);
            lastRet = null;
            expectedModCount = modCount;
        }

        @Override
        public void set(T t) {
            if (lastRet == null)
                throw new IllegalStateException();
            checkForComodification();
            lastRet.setElement(t);
            modCount++;
            expectedModCount = modCount;
        }

        @Override
        public void add(T t) {
            checkForComodification();
            lastRet = null;
            //add to rear if next element is last
            if (next == null)
                addToRear(t);
            else {
                DLLNode<T> prev = next.getPrev();
                //add to front if next element is first
                if (prev == null) addToFront(t);
                else {
                    DLLNode<T> newNode = new DLLNode<>(t, prev, next);
                    prev.setNext(newNode);
                    newNode.setPrev(newNode);
                    modCount++;
                    size++;
                }
            }
            cursor++;
            expectedModCount++;
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
}
