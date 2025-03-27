
/*
 * Name: Kyle Kim
 * Email: kyk029@ucsd.edu
 * PID: A18186449
 * Sources Used: JDK, write-up
 *
 * This is the file contains the implementation of a deque
 */

/**
 * The Deque class which implements a double ended queue
 * and implements the dequeue interface.
 */
public class MyDeque<E> implements DequeInterface<E>{
    //Instance Variables
    Object[] data;
    int size;
    int rear;
    int front;

    //Constants
    private static final int zero = 0;
    private static final int expand = 2;
    private static final int one = 1;
    private static final int DEFAULT = 10;

    /**
     * Constructor for the Deque class
     * 
     * @param initialCapacity the initial capacity of the deque
     */
    public MyDeque(int initialCapacity){
        if(initialCapacity < zero){
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity];
        size = 0;
        rear = 0;
        front = 0;
    }

    /**
     * Getter method for the number of elements in the Deque
     * 
     * @return the number of elements
     */
    public int size(){
        return this.size;
    }

    /**
     * Expands the capacity of the Deque
     */
    public void expandCapacity(){
        if(data.length == zero){
            Object[] largerData = new Object[DEFAULT];
            data = largerData;
        } else {
            int orig_size=size;
            Object[] largerData = new Object[data.length*expand];
            if(size!=zero){
                for(int i = zero;i<orig_size;i++){
                    largerData[i] = removeFirst();
                }
                size = orig_size;
                data = largerData;
                front = zero;
                rear = size-one;
            } else {
                data = largerData;
            }
        }
    }

    /**
     * Adds an element to the front of the deque
     * 
     * @param element the element to add
     */
    public void addFirst(E element){
        if(element == null){
            throw new NullPointerException();
        }
        if(size == data.length){
            expandCapacity();
        }
        if(size==zero){
            data[front] = element;
        } else if(front>rear){
            front--;
            data[front] = element;
        } else {
            if(front==zero){
                data[data.length-one] = element;
                front = data.length-one;
            } else {
                front--;
                data[front] = element;
            }
        }
        size++;
    }

    /**
     * Adds an element to the rear of the deque
     * 
     * @param element the element to add
     */
    public void addLast(E element){
        if(element == null){
            throw new NullPointerException();
        }
        if(size == data.length){
            expandCapacity();
        }
        if(size==zero){
            data[front] = element;
        }else if(rear == data.length-one){
            rear = zero;
            data[rear] = element;
        } else {
            rear++;
            data[rear] = element;
        }
        size++;
    }
    
    /**
     * Removes the first element in the deque
     * 
     * @return the removed element
     */
    public E removeFirst(){
        if(size==zero){
            return null;
        }

        Object removed = data[front];
        data[front] = null;
        size--;

        if(size != zero){
            if(front == data.length-one){
                front = zero;
            } else {
                front++;
            }
        }
        return (E)removed;
    }

    /**
     * Removes the last element in the deque
     * 
     * @return the removed element
     */
    public E removeLast(){
        if(size==zero){
            return null;
        }

        Object removed = data[rear];
        data[rear] = null;
        size--;

        if(size != zero){
            if(rear == zero){
                rear = data.length-one;
            } else {
                rear--;
            }
        }
        return (E)removed;
    }

    /**
     * Getter method for the first element
     * 
     * @return the first element
     */
    public E peekFirst(){
        if(data.length == zero){
            return null;
        }
        return (E)data[front];
    }

    /**
     * Getter method for the last element
     * 
     * @return the last element
     */
    public E peekLast(){
        if(data.length == zero){
            return null;
        }
        return (E)data[rear];
    }
}