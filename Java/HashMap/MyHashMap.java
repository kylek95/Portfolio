/*
 * Name: Kyle Kim
 * Email: kyk029@ucsd.edu
 * PID: A18186449
 * Sources Used: JDK, write-up
 *
 * This class is a the implementation of a hashmap with 
 * two generic types for the value and index of the hashmap.
 */

/**
 * This is the hashmap class to implement the functionality of a hashmap
 */
public class MyHashMap<K,V> {
    private static final int DEFAULT_CAPACITY = 5;
    private static final double LOAD_FACTOR = 0.8;
    private static final int EXPAND_CAPACITY_RATIO = 2;
    private static final int HASH_CODE_MASK = 0x7fffffff;
    private static final int INITIAL_SIZE = 0;
    Node[] hashTable;
    int size;

    /**
     * The default constructor for the MyHashMap class
     */
    public MyHashMap() {
        /* Add your implementation here */
        hashTable = new Node[DEFAULT_CAPACITY];
        size = INITIAL_SIZE;
    }

    /**
     * Constructor with the initial capacity as the parameter.
     * 
     * @param initialCapacity this is the initial capacity of the map
     */
    public MyHashMap(int initialCapacity) {
        /* Add your implementation here */
        if(initialCapacity <= INITIAL_SIZE){
            throw new IllegalArgumentException();
        }
        hashTable = new Node[initialCapacity];
        size = INITIAL_SIZE;
    }

    /**
     * This method gets the value for the desired key
     * @param key the key for the hashmap
     * @return the value at the specified key
     */
    public V get(K key) {
        /* Add your implementation here */
        if(key == null){
            throw new NullPointerException();
        }
        int index = getHash(key, hashTable.length);
        for(Node currentNode = hashTable[index];
                currentNode != null; 
                currentNode = currentNode.getNext()){
            if(currentNode.getKey().equals(key)){
                return (V)currentNode.getValue();
            }
        }
        return null;
    }

    /**
     * This method updates the value of a node
     * with the specfied key. The method then
     * returns the old value of the node
     * @param key the key of the node
     * @param value the new value for the node
     * @return the old value from the node
     */
    public V put(K key, V value) {
        /* Add your implementation here */
        if(key == null || value == null){
            throw new NullPointerException();
        }
        if(size >= hashTable.length*LOAD_FACTOR){
            expandCapacity();
        }
        Object oldValue = null;
        int index = getHash(key,hashTable.length);
        if(hashTable[index] == null){
            hashTable[index] = new Node(key,value);
            size++;
            return null;
        } else {
            for(Node currentNode = hashTable[index];
                currentNode != null; 
                currentNode = currentNode.getNext()){
                if(currentNode.getKey().equals(key)){
                    oldValue = currentNode.getValue();
                    currentNode.setValue(value);
                    return (V)oldValue;
                }
                if(currentNode.getNext() == null){
                    currentNode.setNext(new Node(key, value));
                    size++;
                    return (V)oldValue;
                }
            }
        }
        return (V)oldValue;
    }

    /**
     * This methos removes the node with the 
     * specified key in the hashmap
     * @param key the key of the node
     * @return the value of the removed node
     */
    public V remove(K key) {
        /* Add your implementation here */
        if(key == null){
            throw new NullPointerException();
        }
        Object oldValue = null;
        int index = getHash(key,hashTable.length);
        if(hashTable[index] == null){
            return null;
        }
        if(hashTable[index].getKey().equals(key)){
            oldValue = hashTable[index].getValue();
            hashTable[index] = hashTable[index].getNext();
            size--;
        } else {
            for(Node currentNode = hashTable[index];
                currentNode.getNext() != null; 
                currentNode = currentNode.getNext()){
                if(currentNode.getNext().getKey().equals(key)){
                    oldValue = currentNode.getNext().getValue();
                    currentNode.setNext(currentNode.getNext().getNext());
                    size--;
                }
            }
        }
        return (V)oldValue;
    }

    /**
     * This is a getter method for the size of the hashmap
     * @return the size of the hashmap
     */
    public int size() {
        /* Add your implementation here */
        return size;
    }

    /**
     * This is a getter method for the capacity of the hashmap
     * @return the capacity of the hashmap
     */
    public int getCapacity() {
        /* Add your implementation here */
        return hashTable.length;
    }

    /**
     * This method clears the hash map of all nodes
     */
    public void clear() {
        for(int i = INITIAL_SIZE;i<hashTable.length;i++){
            hashTable[i] = null;
        }
        size = INITIAL_SIZE;
    }

    /**
     * This method checks to see if the hashmap is empty
     * 
     * @return whether it is empty or not
     */
    public boolean isEmpty() {
        /* Add your implementation here */
        for(int i = INITIAL_SIZE;i<hashTable.length;i++){
            if(hashTable[i] != null){
                return false;
            }
        }
        return true;
    }

    /**
     * This method expands the capacity of the hashmap.
     */
    public void expandCapacity() {
        /* Add your implementation here */
        // TODO
        Node[] newTable = new Node[hashTable.length*EXPAND_CAPACITY_RATIO];
        for(int i = INITIAL_SIZE; i < hashTable.length;i++){
            for(Node currentNode = hashTable[i]; 
                currentNode!= null; currentNode = currentNode.getNext()){
                if(newTable[getHash((K)currentNode.getKey(),
                    newTable.length)] == null){
                    newTable[getHash((K)currentNode.getKey(),
                        newTable.length)] = currentNode;
                } else {
                    Node collisionNode = 
                        newTable[getHash((K)currentNode.getKey()
                        ,newTable.length)];
                    while(collisionNode.getNext()!=null){
                        collisionNode = collisionNode.getNext();
                    }
                    collisionNode.setNext(currentNode);
                }
            }
        }
        hashTable = newTable;
    }

    /**
     * This method returns the index for the hashtable.
     * @param key the key to be inputted in the hashtable
     * @param capacity the capacity of the hashmap
     * @return the index for the hashtable
     */
    public int getHash(K key, int capacity) {
        /* Add your implementation here */
        if(key == null){
            throw new NullPointerException();
        }
        if(capacity <= INITIAL_SIZE){
            throw new IllegalArgumentException();
        }
        // Hashing function is given here. DO NOT MODIFY THIS
        return (key.hashCode() & HASH_CODE_MASK) % capacity;
    }

    /**
     * A Node class that holds (key, value) pairs and
     *  references to the next node in the linked list
     */
    protected class Node<K,V> {
        K key;
        V value;
        Node next;

        /**
         * Constructor to create a single node
         * @param key key to store in this node
         * @param value value to store in this node
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        /**
         * Accessor to get the next node in the list
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the next node in the list
         * @param n the new next node
         */
        public void setNext(Node n) {
            next = n;
        }

        /**
         * Accessor to get the node's key
         * @return this node's key
         */
        public K getKey() {
            return this.key;
        }

        /**
         * Set the node's key
         * @param key the new key
         */
        public void setKey(K key) {
            this.key = key;
        }

        /**
         * Accessor to get the node's value
         * @return this node's value
         */
        public V getValue() {
            return this.value;
        }

        /**
         * Set the node's value
         * @param value the new value
         */
        public void setValue(V value) {
            this.value = value;
        }

        /**
         * Check if this node is equal to another node
         * @param other the other node to check equality with
         * @return whether or not this node is equal to the other node
         */
        public boolean equals(Node<K, V> other) {
            return this.key.equals(other.key) && 
                this.value.equals(other.value);
        }
    }
}
