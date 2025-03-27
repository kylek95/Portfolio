/*
 * Name: Kyle Kim
 * Email: kyk029@ucsd.edu
 * PID: A18186449
 * Sources Used: JDK, write-up
 *
 * This MyHashSet class is an implementation of the MyHashMap class.
 * The hash set consists of one generic type that corresponds to
 * the type for the key in the hashmap.
 */

/**
 * This is the hashset class to implement the HashMap class
 */
public class MyHashSet<E> {
    public static final Object DEFAULT_OBJECT = new Object();
    private static final int INITIAL_SIZE = 0;
    MyHashMap<E, Object> hashMap;

    /**
     * Default constructor for the hash set.
     */
    public MyHashSet() {
        /* Add your implementation here */
        hashMap = new MyHashMap<E, Object>();
    }

    /**
     * Constructor with an initial capacity
     * @param initialCapacity the initial capacity of the hashmap
     */
    public MyHashSet(int initialCapacity) {
        /* Add your implementation here */
        if(initialCapacity <= INITIAL_SIZE){
            throw new IllegalArgumentException();
        }
        hashMap = new MyHashMap<E, Object>(initialCapacity);
    }

    /**
     * This method adds an element to the hashset
     * @param element the element to be added to the set
     * @return Whether or not the element already existed in the hashset
     */
    public boolean add(E element) {
        /* Add your implementation here */
        if(element == null){
            throw new NullPointerException();
        }
        if(hashMap.put(element,DEFAULT_OBJECT)==null){
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method removes the desired element from the hashset 
     * and returns whether or not the element was in the set
     * @param element the element to be removed
     * @return whether or not the element was in the set
     */
    public boolean remove(E element) {
        /* Add your implementation here */
        if(element == null){
            throw new NullPointerException();
        }
        Object oldValue = hashMap.remove(element);
        if(oldValue == null){
            return false;
        } else {
            return true;
        }
    }

    /**
     * This is a getter method for the size of the hashmap
     * @return the size of the hashmap
     */
    public int size() {
        /* Add your implementation here */
        return hashMap.size(); 
    }

    /**
     * This method clears the set
     */
    public void clear() {
        /* Add your implementation here */
        hashMap.clear();
    }

    /**
     * This method checks if the set is empty
     * @return whether or not he set is empty
     */
    public boolean isEmpty() {
        /* Add your implementation here */
        return hashMap.isEmpty();
    }
}