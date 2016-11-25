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

    //* Inner class Node */
    private class Node<E> {
        private E element;
        private Node<E> left;

        private Node<E> right;
        public Node(E element) {
            this.element = element;
        }
    }

    public MyTreeSet() {
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
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                }
                else if (e.compareTo(current.element) > 0) {
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
            if (((E) o).compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            }
            else if (((E) o).compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            }
            else
                break; // Element is in the tree pointed at by current
        }

        if (current == null)
            return false; // Element is not in the tree

        // Case 1: current has no left children
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else {
                if (((E) o).compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        } else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            Node<E> parentOfRightMost = current;
            Node<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                // Special case: parentOfRightMost == current
                parentOfRightMost.left = rightMost.left;
        }

        size--;
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
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyInorderIterator();
    }

    private class MyInorderIterator implements Iterator<E> {
        // Store the elements in a list
        private List list = new ArrayList<E>();
        private int current = 0; // Point to the current element in list

        public MyInorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
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
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        public boolean hasNext() {
            return current < list.size();
        }

        @Override
        public E next() {
            return (E) list.get(current++);
        }

        @Override
        public void remove() {
            MyTreeSet.this.remove(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
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
        int result = root != null ? root.hashCode() : 0;
        result = 31 * result + size;
        return result;
    }
}
