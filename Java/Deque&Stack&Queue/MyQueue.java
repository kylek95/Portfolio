/*
 * Name: Kyle Kim
 * Email: kyk029@ucsd.edu
 * PID: A18186449
 * Sources Used: JDK, write-up
 *
 * Implementation of a Queue with the MyDeque class.
 */

/**
 * This class implements the Queue ADT using a MyDeque instance variable called
 * theStack.
 */
public class MyQueue<E> implements QueueInterface<E> {
    MyDeque<E> theQueue;

    private static final int zero = 0;

    /**
     * Constructor to create new MyQueue that holds a MyDeque.
     *
     * @param initialCapacity The max amount of elements this data structure
     *                        can hold.
     */
    public MyQueue(int initialCapacity) {
        theQueue = new MyDeque(initialCapacity);
    }

    /**
     * Checks if the queue is empty.
     *
     * @return True if there are no elements in the queue, false otherwise.
     */
    @Override
    public boolean empty() {
        return theQueue.size() == zero;
    }

    /**
     * Adds the specified element to the tail of this MyQueue.
     *
     * @param element the element to add to the queue
     */
    @Override
    public void enqueue(E element) {
        theQueue.addFirst(element);
    }

    /**
     * Removes the element at the head of this MyQueue.
     * Returns the element removed, or null if there was no such
     * element.
     *
     * @return the element removed, or null if the size was zero.
     */
    @Override
    public E dequeue() {
        return theQueue.removeLast();
    }

    /**
     * Returns the element at the head of this MyQueue,
     * or null if there was no such element.
     *
     * @return the element at the head, or null if the size was zero.
     */
    @Override
    public E peek() {
        return theQueue.peekLast();
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in the queue
     */
    @Override
    public int size() {
        return theQueue.size();
    }
}
