package doublyLinkedList;

/**
 * LinearNode represents a node in a double linked list.
 *
 */
public class DLLNode<E> {
    private DLLNode<E> prev;
    private DLLNode<E> next;
    private E element;

    /**
     * reates a node storing the specified element, next and previous nodes
     * @param element the element to be stored within the new node
     * @param prev previous node
     * @param next next node
     */
    public DLLNode(E element, DLLNode<E> prev, DLLNode<E> next) {
        this.prev = prev;
        this.next = next;
        this.element = element;
    }

    /**
     * Returns the node that follows this one.
     *
     * @return the node that follows the current one
     */
    public DLLNode<E> getNext() {
        return next;
    }

    /**
     * Sets the node that follows this one.
     *
     * @param node the node to be set to follow the current one
     */
    public void setNext(DLLNode<E> node) {
        next = node;
    }

    /**
     * Returns the node that is before this one.
     *
     * @return the node that is before this one
     */
    public DLLNode<E> getPrev() {
        return prev;
    }

    /**
     * Sets the node that is before this one.
     *
     * @param node the node to be set before the current one
     */
    public void setPrev(DLLNode<E> node) {
        this.prev = node;
    }

    /**
     * Returns the element stored in this node.
     *
     * @return the element stored in this node
     */
    public E getElement() {
        return element;
    }

    /**
     * Sets the element stored in this node.
     *
     * @param elem the element to be stored in this node
     */
    public void setElement(E elem) {
        element = elem;
    }

    @Override
    public String toString() {
        return "Element: " + element.toString() + " Has previous: " + (prev != null) + " Has next: " + (next != null);
    }
}
