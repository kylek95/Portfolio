/*
 * Name: Kyle Kim
 * Email: kyk029@ucsd.edu
 * PID: A18186449
 * Sources Used: JDK, write-up
 *
 * File for the implementation of a Priority Queue
 */

import java.util.Collection;

/**
  * The implementation of a priority queue class
  * with a min heap as its underlying data structure.
  */
public class MyPriorityQueue<E extends Comparable<E>>{
    protected MyMinHeap<E> heap;


    //Constructors

    /**
     * Default Constructor
     */
    public MyPriorityQueue(){
        heap = new MyMinHeap<>();
    }

    /**
     * Constructor for the PriorityQueue
     * 
     * @param collection the collection with all the data elements
     */
    public MyPriorityQueue(Collection<? extends E> collection){
        heap = new MyMinHeap<>(collection);
    }

    //Core Methods

    /**
     * This method pushes an element into the priority queue
     * 
     * @param element the element to be added to the priority queue
     */
    public void push(E element){
        if(element == null){
            throw new NullPointerException();
        }
        heap.insert(element);
    }

    /**
     * This returns the first value in the priority queue
     * @return the first element
     */
    public E peek(){
        return heap.getMin();
    }

    /**
     * This returns the first element of the priority queue
     * 
     * @return the first element
     */
    public E pop(){
        return heap.remove();
    }

    /**
     * This returns the length of the 
     * priority queue/the number of elements
     * 
     * @return the length of the queue
     */
    public int getLength(){
        return heap.size();
    }

    /**
     * This method clears the queue and makes it empty
     */
    public void clear(){
        heap.clear();
    }
}
