package my_hashmap;

import java.util.*;

/**
 * HashMap implementation
 * Created by a-morenets on 28.11.2016.
 */
public class MyHashMap<K extends Comparable<K>, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 10;
    private Node<K, V>[] data;
    private int capacity;
    private int size;

    /**
     * Inner class Node
     */
    private class Node<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setValue(V newValue) {
            value = newValue;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "(" + key + "," + value + ")" + (next != null ? "->" + next.toString() : "");
        }
    }

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashMap(int capacity) {
        this.capacity = capacity;
    }

    /* Hash function */
    private int hash(K key) {
        return key.hashCode();
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
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        int index = hash((K) key) % capacity;
        if (data[index] == null) // cell is empty - the key is not stored in the map
            return null;
        // cell contains a chain of node(s)
        Node<K, V> currentNode = data[index];
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) // element found with the given key
                return currentNode.getValue();
            if (currentNode.getNext() == null)
                break;
            currentNode = currentNode.getNext();
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int index = hash(key) % capacity;
        if (data[index] == null) // cell is empty
            // create new node and place it at index'th position in the data table
            data[index] = new Node<>(key, value);
        else { // cell contains a chain of node(s)
            Node<K, V> currentNode = data[index];
            while (currentNode != null) {
                if (currentNode.getKey().equals(key)) { // element already exists with the given key
                    V originalValue = currentNode.getValue();
                    currentNode.setValue(value);
                    return originalValue;
                }
                if (currentNode.getNext() == null)
                    break;
                currentNode = currentNode.getNext();
            }
            // add new node
            currentNode.setNext(new Node<>(key, value));
        }
        size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        int index = hash((K) key) % capacity;
        if (data[index] == null) // cell is empty - the key is not stored in the map
            return null;
        // cell contains a chain of node(s)
        Node<K, V> currentNode = data[index];
        Node<K, V> prevNode = null;
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) { // element found with the given key
                V originalValue = currentNode.getValue();
                if (prevNode == null)
                    data[index] = currentNode.getNext();
                else
                    prevNode.setNext(currentNode.getNext());
                size--;
                return originalValue;
            }
            if (currentNode.getNext() == null)
                break;
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new TreeSet<K>();
        for (Node<K, V> hashMapNode : data) {
            if (hashMapNode != null) {
                Node<K, V> currentNode = hashMapNode;
                while (currentNode != null) {
                    set.add(currentNode.getKey());
                    if (currentNode.getNext() == null)
                        break;
                    currentNode = currentNode.getNext();
                }
            }
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}