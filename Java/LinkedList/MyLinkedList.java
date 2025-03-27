/*
 * Name: Kyle Kim
 * Email: kyk029@ucsd.edu
 * PID: A18186449
 * Sources Used: write-up, JDK
 * This file implements a LinkedList class using
 * Java generics with a nested node class.
 */

import java.util.AbstractList;

/**
 * This class has two instance variables: head, tail, size.
 * The classes uses these variables to create a linked list
 * that has sentinel nodes head and tail to encapsulate all
 * non sentinel nodes that hold data.
 */
public class MyLinkedList<E> extends AbstractList<E> {
    private static final int empty = 0;
    private static final int index_checker = 0;
    private static final int not_found = -1;
    private static final int increment = 1;
    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {  
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;		
        }

        /** 
         * Set the parameter next as the next node
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }

    //  Implementation of the MyLinkedList Class
    /**
     * Default constructor for the MyLinkedList class
     */
    public MyLinkedList() {
        /* Add your implementation here */
        //Initializes all the variables
        this.size = empty;
        this.head = new Node(null);
        this.tail = new Node(null);
        //set the nest and prev for the head and tail
        head.setNext(tail);
        tail.setPrev(head);
    }

    /**
     * Getter method for the size of the linked list
     * 
     * @return the size of the linked list
     */
    @Override
    public int size() {
        /* Add your implementation here */
        return this.size;
    }

    /**
     * The getter method for the data within 
     * the node at the given index.
     * 
     * @param index the specified node's index
     * @return the data from the node with the generic type E
     */
    @Override
    public E get(int index) {
        /* Add your implementation here */
        //checks to see if the index is in bounds
        if(index < index_checker || index >= size){
            throw new IndexOutOfBoundsException();
        }
        
        return (E)getNth(index).getElement();
    }

    /**
     * This method adds a new node to the linked list
     * at the specified index
     * 
     * @param index the specified index of where to add the new node
     * @param data the data that the node contains
     */
    @Override
    public void add(int index, E data) {
        /* Add your implementation here */
        
        //checks to see if the index is in bounds
        if(index < index_checker || index > size){
            throw new IndexOutOfBoundsException();
        }
        //checks to see if the data isn't null
        if(data == null){
            throw new NullPointerException();
        }
        
        Node newNode = new Node(data);
        if(size > empty){
            if(index == size){
                newNode.setNext(tail);
                newNode.setPrev(tail.getPrev());
                tail.prev.setNext(newNode);
                tail.setPrev(newNode);
                size++;
            } else if(index == index_checker){
                newNode.setNext(head.getNext());
                newNode.setPrev(head);
                head.next.setPrev(newNode);
                head.setNext(newNode);
                size++;
            } else {
                //inserts the node at a certain index
                Node after = getNth(index);
                newNode.setNext(after);
                newNode.setPrev(after.getPrev());
                after.prev.setNext(newNode);
                after.setPrev(newNode);
                //size increment
                size++;
            }
            
        } else {
            //inserts the node as the first element
            newNode.setNext(tail);
            newNode.setPrev(head);
            head.setNext(newNode);
            tail.setPrev(newNode);
            //size increment
            size++;
        }
    }

    /**
     * This method adds a new node at the end of the linked list
     * 
     * @param data the data within the node to add
     * @return This method will always return true
     * unless an exception is thrown
     */
    @Override
    public boolean add(E data) {
        /* Add your implementation here */
        //checks to see if the data is null
        if(data == null){
            throw new NullPointerException();
        }
        //adds the node to the end of the list
        Node newNode = new Node(data);
        newNode.setPrev(tail.getPrev());
        newNode.setNext(tail);
        tail.prev.setNext(newNode);
        tail.setPrev(newNode);
        //increments size
        size++;
        return true;
    }

    /**
     * This method sets the data in the node at the 
     * specified index to the given data
     * 
     * @param index the desired node
     * @param data the new data to be stored in the node
     * 
     * @return the old data that was replaced by the 
     * inputed data value
     */
    @Override
    public E set(int index, E data) {
        /* Add your implementation here */
        //checks to see if the data is null
        if(data == null){
            throw new NullPointerException();
        }
        //checks to see if the index is in bounds
        if(index < index_checker || index >= size){
            throw new IndexOutOfBoundsException();
        }
        //replaces the data in the Nth node
        Node current  = getNth(index);
        E output = current.data;
        current.setElement(data);
        //returns the old data in the node
        return output;
    }

    /**
     * This method removes the node at the indicated index
     * and returns the data form the removed node
     * 
     * @param index the desired node index to be removed
     * 
     * @return the data from the removed node
     */
    @Override
    public E remove(int index) {
        /* Add your implementation here */
        //checks to see if the index is in bounds
        if(index < index_checker || index >= size){
            throw new IndexOutOfBoundsException();
        }
        //finds the node to be removed
        Node removed = getNth(index);
        //removes the node from the chain
        removed.prev.setNext(removed.next);
        removed.next.setPrev(removed.prev);
        //decrements the size
        size--;
        //returns the removed node
        E data = removed.getElement();
        return data;
    }

    /**
     * This clears all of the nodes from the linked list
     */
    @Override
    public void clear() {
        /* Add your implementation here */
        //sets the node back to default/empty
        tail.setPrev(head);
        head.setNext(tail);
        size = empty;
    }

    /**
     * This method tests to see if the linkedlist is empty
     * 
     * @return the method returns the boolean
     * value of being empty of not
     */
    @Override
    public boolean isEmpty() {
        /* Add your implementation here */
        //checks if the head's next is the tail
        if(head.next!=tail || size >empty){
            return false;
        }
        return true;
    }

    /**
     * This method takes in an index and 
     * returns the node at that index.
     * @param index the desired index node
     * 
     * @return The node at the speified index
     */
    protected Node getNth(int index) {
        /* Add your implementation here */
        //removes the node from the chain
        if(index < index_checker || index >= size){
            throw new IndexOutOfBoundsException();
        }
        //iterates through to return the Nth node
        Node current = head.next;
        for(int i = index_checker; i < index; i++){
            current = current.next;
        }
        return current;
    }

    /**
     * THis method checks to see if the linked list 
     * has a node with the desired data within a set of indeces
     * 
     * @param data the data they are looking for
     * @param start the first index to check
     * @param end the last index to check
     * @return the boolean to indicated if the data is in the linked 
     * list between the specified indeces
     */
    public boolean contains(E data, int start, int end) {
        /*Add your implementation here */
        //removes the node from the chain
        if(start < index_checker || start >= size
            || end < index_checker || end > size){
            throw new IndexOutOfBoundsException();
        }
        //iterates through two bounds to see if
        //the data is in the range of nodes
        Node current = getNth(start);
        for(int i = start; i < end; i++){
            if(current.getElement().equals(data)){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * This method takes in data and returns the index 
     * of the node in the linkedlist with that data
     * @param data the data to be found in the linked list
     * @return the index of the node with the data
     */
    public int indexOfElement(E data) {
        /*Add your implementation here */
        //checks to see if the data is null
        if(data == null){
            throw new NullPointerException();
        }
        //iterates through to find the index of the node with the data
        Node current = head.getNext();
        for(int i = index_checker; i < size; i++){
            if(current.getElement().equals(data)){
                return i;
            }
            current = current.getNext();
        }
        //returns -1 if not found
        return not_found;
    }
    
}
