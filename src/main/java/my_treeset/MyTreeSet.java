package my_treeset;

import java.util.*;

/**
 * TreeSet implementation
 * Created by a-morenets on 25.11.2016.
 */
public class MyTreeSet<E extends Comparable<E>> implements Set<E> {
    private Node<E> root;
    private int size = 0;
    private Comparator<E> comp;
    private int modCount;

    /** Inner class Node */
    private class Node<E> {
        private E element;
        private Node<E> left;
        private Node<E> right;

        public Node(E element) {
            this.element = element;
        }
    }

    public MyTreeSet() {
        comp = (o1, o2) -> o1.compareTo(o2);
    }

    public MyTreeSet(Comparator<E> comp) {
        this.comp = comp;
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
    public boolean add(E e) {
        Objects.requireNonNull(e);

        if (root == null)
            root = createNewNode(e); // Create a new root
        else {
            // Locate the parent node
            Node<E> parent = null;
            Node<E> current = root;
            while (current != null)
                if (comp.compare(e, current.element) < 0) {
                    parent = current;
                    current = current.left;
                }
                else if (comp.compare(e, current.element) > 0) {
                    parent = current;
                    current = current.right;
                }
                else
                    return false; // Duplicate node not inserted

            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }

        size++;
        modCount++;
        return true; // Element inserted
    }

    /**
     * Helper method - creates new Node
     * @param e data to be placed in Node
     * @return created Node
     */
    private Node<E> createNewNode(E e) {
        return new Node<>(e);
    }

    @Override
    public boolean remove(Object o) {
        Objects.requireNonNull(o);

        // Locate the node to be deleted and also locate its parent node
        Node<E> parent = null;
        Node<E> current = root;
        while (current != null) {
            if (comp.compare(((E) o), current.element) < 0) {
                parent = current;
                current = current.left;
            }
            else if (comp.compare(((E) o), current.element) > 0) {
                parent = current;
                current = current.right;
            }
            else
                break; // Element is in the tree pointed at by cursor
        }

        if (current == null)
            return false; // Element is not in the tree

        // Case 1: cursor has no left children
        if (current.left == null) {
            // Connect the parent with the right child of the cursor node
            if (parent == null) {
                root = current.right;
            } else {
                if (comp.compare(((E) o), parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        } else {
            // Case 2: The cursor node has a left child
            // Locate the rightmost node in the left subtree of
            // the cursor node and also its parent
            Node<E> parentOfRightMost = current;
            Node<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in cursor by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                // Special case: parentOfRightMost == cursor
                parentOfRightMost.left = rightMost.left;
        }

        size--;
        modCount++;
        return true; // Element deleted
    }

    @Override
    public boolean contains(Object o) {
        Objects.requireNonNull(o);

        for (E e : this) {
            if (o.equals(e))
                return true;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] a = new Object[size];
        int count = 0;

        for (E e : this) {
            a[count++] = e;
        }

        return a;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Objects.requireNonNull(a);

        if (a.length < size) {
            a = (T[]) new Object[size];
        }

        int count = 0;
        for (E e : this) {
            a[count++] = (T) e;
        }

        return a;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Objects.requireNonNull(c);

        for (E e: c) {
            if (!add(e))
                return false;
        }
        modCount++;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);

        for (Object o: c) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);

        boolean isChanged = false;
        for (Object o: c) {
            if (o != null && remove(o))
                isChanged = true;
        }

        if (isChanged)
            modCount++;

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);

        boolean isChanged = false;
        for (Iterator<E> itr = iterator(); itr. hasNext();) {
            E e = itr.next();
            if (!c.contains(e)) {
                itr.remove();
                isChanged = true;
            }
        }

        if (isChanged)
            modCount++;

        return isChanged;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
        modCount++;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyInorderIterator();
    }

    private class MyInorderIterator implements Iterator<E> {
        // Store the elements in a inorderList
        private List inorderList = new ArrayList<E>();

        private int cursor = 0; // Point to the cursor element in inorderList
        private int lastRet = -1;
        private int expectedModCount = modCount;

        public MyInorderIterator() {
            inorder(); // Traverse binary tree and store elements in inorderList
        }

        /** Inorder traversal from the root*/
        private void inorder() {
            inorder(root);
        }

        /** Inorder traversal from a subtree */
        private void inorder(Node<E> root) {
            if (root == null)
                return;
            inorder(root.left);
            inorderList.add(root.element);
            inorder(root.right);
        }

        @Override
        public boolean hasNext() {
            return cursor < inorderList.size();
        }

        @Override
        public E next() {
            checkForComodification();

            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();

            cursor = i + 1;
            return (E) inorderList.get(lastRet = i);
        }

        @Override
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();

            checkForComodification();

            try {
                MyTreeSet.this.remove(inorderList.get(lastRet)); // remove the cursor element
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;

                inorderList.clear(); // Clear the inorderList
                inorder(); // Rebuild the inorderList
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Iterator<E> e1 = iterator();
        Iterator<?> e2 = ((Set<?>) o).iterator();
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
        int hashCode = 1;
        for (E e : this)
            hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
        return hashCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Iterator<E> itr = iterator(); itr.hasNext();) {
            sb.append(itr.next());
            if (itr.hasNext())
                sb.append(", ");
        }
        return sb.toString() + "]";
    }
}
