/*
 * Name: Kyle Kim
 * Email: kylekim043006@gmail.com
 * This file implements a Arraylist class using
 * Java generics to replicate the functionality of the 
 * Java ArrayList class.
 */

/**
 * This class has two instance variables: data, size.
 * The classes uses these variables to create a dynamnic
 * array that can insert, remove, append, prepend, get,
 * and find elements within the array.
 */
public class MyArrayList<E> implements MyList<E>{
    private static final int default_size = 5;
    private static final int zero = 0;
    private static final int one = 1;
    private static final int doubler = 2;

    Object[] data;
    int size;
    /**
     * Default constructor for the class
     * 
     */
    public MyArrayList(){
        //initializes data to the length of 5
        data = new Object[default_size];
        size = zero;
    }

    /**
     * Constructor with capacity
     * 
     * @param initialCapacity the capacity at creation
     */
    public MyArrayList(int initialCapacity){
        //checks to see if the initialCapacity is valid
        if(initialCapacity < zero){
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity];
        size = zero;
    }

    /**
     * Constructor with an initial array.
     * If the initial array is null, the constructor
     * will initialize the data array with the size of 5
     * 
     * @param arr the initial array
     */
    public MyArrayList(E[] arr){
        //checks to see if the arr is null
        if(arr == null){
            //initializes data with a length of 5.
            data = new Object[default_size];
            size = zero;
        } else {
            //initializes data to be the same length as arr
            data = new Object[arr.length];
            //copies the contents of arr into data
            System.arraycopy(((Object[])arr),0,data,0,arr.length);
            //sets the size to be the amount of elements in data.
            size = data.length;
        }
    }
    
    /**
     * Increase the capacity of underlying array if needed
     * If the required capacity for the method is smaller than the 
     * current capacity the method will throw an IllegalArgumentException
     * 
     * @param requiredCapacity minimum capacity after expanding
     */
    @Override
    public void expandCapacity(int requiredCapacity){
        if(requiredCapacity < data.length){
            throw new IllegalArgumentException();
        }
        //checks for an array with the length of 0.
        if(data.length == zero){
            //checks to see if the required capacity is greater
            //than the default capacity.
            if(requiredCapacity <= default_size){
                //initializes data with the length of 5.
                data = new Object[default_size];
            } else {
                //initializes data with the length
                //of the required capacity.
                data = new Object[requiredCapacity];
            }
        } else {
            Object[] temp;
            if(doubler*data.length<requiredCapacity){
                temp = new Object[requiredCapacity];
            } else {
                temp = new Object[doubler*data.length];
            }
            for(int i = zero; i < data.length; i++){
                temp[i] = data[i];
            }
            data = temp;
        }
    }

    /**
     * Get the amount of elements array can hold
     *
     * @return number of elements that can be held
     */
    @Override
    public int getCapacity(){
        return data.length;
    }

    /**
     * Add an element at the specified index
     * If the index is less than zero or greater than
     * the size of the array, it will throw an 
     * IndexOutOfBoundsException.
     * 
     * @param index   position to insert the element
     * @param element the element to insert
     */
    @Override
    public void insert(int index, E element){
        //checks to see if the array needs to be expanded
        if(size == data.length){
            expandCapacity(size+one);
        }
        //checks if the index is within the bounds of the array
        if(index<zero || index > size){
            throw new IndexOutOfBoundsException();
        }
        Object current = data[index];
        Object next;
        //set the element at index to be the desired element
        data[index] = ((Object)element);
        //moves each element after the index one element down
        for(int i = index; i < size;i++){
            next = data[i+one];
            data[i+one] = current;
            current = next;
        }
        //increases the size by one element
        size++;
    }

    /**
     * Add an element to the end of the list
     *
     * @param element the element to append
     */
    @Override
    public void append(E element){
        //increases the size to allow insert to work
        insert(size, element);
    }

    /**
     * Add an element to the beginning of the list 
     *
     * @param element the element to prepend
     */
    @Override
    public void prepend(E element){
        //increases the size to allow insert to work
        insert(zero, element);
    }

    /**
     * Get the element at the given index
     *
     * @param index the index at which to return the element
     * @return the element at the index
     */
    @Override
    @SuppressWarnings("unchecked")
    public E get(int index){
        if(index < zero || index >= size){
            throw new IndexOutOfBoundsException();
        }
        return (E)data[index];
    }

    /**
     * Replaces an element at the specified index with a new element and return
     * the original element
     *
     * @param index   the index at which to replace
     * @param element the element with which to replace
     * @return the original element
     */
    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E element){
        if(index < zero || index >= size){
            throw new IndexOutOfBoundsException();
        }
        E output = (E)data[index];
        data[index] = element;
        return output;
    }

    /**
     * Remove the element at the specified index and return the removed element
     *
     * @param index the index at which to remove the element
     * @return the removed element
     */
    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index){
        if(index < zero || index >= size){
            throw new IndexOutOfBoundsException();
        }
        E output = (E)data[index];
        Object next;
        //moves each element after the index one element up
        for(int i = index; i < size-one;i++){
            next = data[i+one];
            data[i] = next;
        }
        data[size-one] = null;
        size--;
        return output;
    }

    /**
     * Get the number of elements in the list
     *
     * @return number of elements in the list
     */
    @Override
    public int size(){
        return size;
    }

    /**
     * Rotate the list by numPositions number of positions to the right
     * 
     * @param numPositions the number of positions to rotate by
     */
    @Override
    @SuppressWarnings("unchecked")
    public void rotate(int numPositions){
        //checking for a valid number of rotations
        if(numPositions < zero || numPositions >= size){
            throw new IndexOutOfBoundsException();
        }
        for(int i = zero; i < numPositions; i++){
            //stores the last object every rotation
            Object last = data[size-one];
            //moves every alement except the last up one
            for(int a = size-one; a > zero; a--){
                data[a] = data[a-one];
            }
            //assigns the last element to the first index
            data[zero] = last;
        }
    }

    /**
     * Find the element in the list and return its index
     * 
     * @param element the element to find
     * @return the index of the last occurrence of element (-1 if not found)
     */
    @Override
    public int find(E element){
        int index = -1;
        for(int i = zero; i < size; i++){
            if(data[i].equals(element)){
                index = i;
            }
        }
        return index;
    }

}
