/*
 * Name: Kyle Kim
 * Email: kyk029@ucsd.edu
 * PID: A18186449
 * Sources Used: JDK, write-up
 *
 * File for the implementation of a min heap 
 */
import java.util.ArrayList;
import java.util.Collection;

/**
 * The class MyMinHeap for the implementation of a min heap
 */
public class MyMinHeap<E extends Comparable<E>>implements MinHeapInterface<E>{
    //Instance variable
    protected ArrayList<E> data;
    
    //Constants
    private static final int zero = 0;
    private static final int one = 1;
    private static final int two = 2;
    

    /**
     * Default constructor for the MyMinHeap class
     */
    public MyMinHeap(){
        this.data = new ArrayList<E>();
    }

    /**
     * Constructor for the MyMinHeap class
     * 
     * @param collection the collection with all the data elements
     */
    public MyMinHeap(Collection<? extends E> collection){
        if(collection == null){
            throw new NullPointerException();
        }
        data = new ArrayList<>(collection);
        for(int i = data.size()-one; i >=zero; i--){
            percolateDown(i);
        }
    }

    //Helper Methods

    /**
     * This method swaps two values in the heap.
     * 
     * @param from the first index to swap
     * @param to the second index to swap
     */
    protected void swap(int from, int to){
        // Object first = data.get(from);
        // Object second = data.get(to);
        // data.set(from,(E)second);
        // data.set(to,(E)first);
        data.set(to,data.set(from,data.get(to)));
    }

    /**
     * This is a getter method for the parent index
     * 
     * @param index the index of the child
     * @return the index of the parent value
     */
    protected static int getParentIdx(int index){
        if(index%two==zero){
            index--;
        }
        return index/two;
    }

    /**
     * This is a getter method for the left child
     * of a value
     * 
     * @param index the index of the parent
     * @return the index of the left child
     */
    protected static int getLeftChildIdx(int index){
        if(index == zero){
            return one;
        }
        return index*two+one;
    }

    /**
     * This is a getter method for the right child
     * of a value
     * 
     * @param index the index of the parent
     * @return the index of the right child
     */
    protected static int getRightChildIdx(int index){
        if(index == zero){
            return two;
        }
        return index*two+two;
    }

    /**
     * This method returns the smaller child
     * of a parent at the specfied index
     * @param index the parent value index
     * @return the index of the child with the smaller value
     */
    protected int getMinChildIdx(int index){
        int left_index = getLeftChildIdx(index);
        int right_index = getRightChildIdx(index);
        if(left_index>=data.size()&&right_index>=data.size()){
            return -one;
        } else if(right_index>=data.size()){
            return left_index;
        }
        if(data.get(left_index).compareTo(data.get(right_index))==zero){
            return left_index;
        }else if(data.get(left_index).compareTo(data.get(right_index))>zero){
            return right_index;
        } else{
            return left_index;
        }
    }

    /**
     * This method percolates values up until the
     * heap does not violate the minheap rules
     * 
     * @param index the value to be percolated up
     */
    protected void percolateUp(int index){
        int parent_index = getParentIdx(index);
        while(data.get(parent_index).compareTo(data.get(index))>zero){
            //swaps the parent and the current
            swap(parent_index,index);

            //gets the child index
            int child_index = getMinChildIdx(index);

            //checks to see if there is a child
            if(child_index != -one){
                //chekcing to see if the child is smaller than the 
                //previous parent index of the current index
                if(data.get(child_index).compareTo(
                    data.get(parent_index))<zero){
                    //percolates the child up
                    percolateUp(child_index);
                }
            }
            //update sthe index to the index of the parent
            index = parent_index;

            //updates parent_index to the new parent index
            parent_index = getParentIdx(index);

            //checks to see if the current value is the root
            if(index==zero){
                //stops the percolating values up
                break;
            }
        }
    }

    /**
     * This method percolates a value down the min heap
     * until the heap does not violate the min heap rules
     * 
     * @param index the index of the value to be percolated down
     */
    protected void percolateDown(int index){
        int child_index = getMinChildIdx(index);
        if(child_index!=-one){
            while(data.get(index).compareTo(data.get(child_index))>zero){
                swap(child_index,index);
                index = child_index;
                child_index = getMinChildIdx(index);
                if(child_index==-one){
                    break;
                }
            }
        }
    }

    /**
     * This method removes a value from the min heap
     * without violating the min heap rules
     * 
     * @param index the index to delete
     * @return the value at the deleted index
     */
    protected E deleteIndex(int index){
        Object output = data.get(index);
        if(index == data.size()-one){
            data.remove(index);
        }else{
            int child_index = getMinChildIdx(index);
            swap(index,data.size()-one);
            data.remove(data.size()-one);
            if(child_index != -one){
                int parent_index = getParentIdx(index);
                if(data.get(parent_index).compareTo(data.get(index))>zero){
                    percolateUp(index);
                } else if(data.get(child_index).compareTo(
                    data.get(index))<zero){
                    percolateDown(index);
                }
            }
        }
        return (E)output;
    }

    //Core Methods
    /**
     * This method inserts an element into the min heap
     * 
     * @param element the element to be inserted to the heap
     */
    public void insert(E element){
        if(element == null){
            throw new NullPointerException();
        }
        data.add(element);
        percolateUp(data.size()-one);
    }

    /**
     * This method gets the min of the heap
     * 
     * @return the root value of the min heap
     */
    public E getMin(){
        if(data.size()==zero){
            return null;
        }
        return data.get(zero);
    }

    /**
     * This method removes the root value of the min heap
     * 
     * @return the root value of the min heap
     */
    public E remove(){
        if(data.size()==zero){
            return null;
        }
        return deleteIndex(zero);
    }

    /**
     * This is a getter method for the size of the min heap
     * 
     * @return the number of element in the heap
     */
    public int size(){
        return data.size();
    }

    /**
     * This method clears the heap of all elements
     * 
     */
    public void clear(){
        data.clear();
    }
}
