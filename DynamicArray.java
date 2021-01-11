package data_structures;

/**
 * @author https://examples.javacodegeeks.com/dynamic-array-java-example/
 * iterator implemented by Omer Toledo
 */

import java.util.Arrays;
import java.util.Iterator;

public class DynamicArray<T> implements Iterable<T>{
    private T array[];
    // holds the current size of array
    private int size;
    // holds the total capacity of array
    private int capacity;

    // default constructor to initialize the array and values
    public DynamicArray(){
        array = (T[])new Object[2];
        size=0;
        capacity=2;
    }

    public DynamicArray(int len){
        array = (T[])new Object[len];
        size=0;
        capacity=len;
    }


    // to add an element at the end
    public void addElement(T element){
        // double the capacity if all the allocated space is utilized
        if (size == capacity-1){
            ensureCapacity(2);
        }
        array[size] = element;
        size++;
    }

    // to add an element at a particular index
    public void addElement(int index, T element){
        // double the capacity if all the allocated space is utilized
        if (size == capacity-1){
            ensureCapacity(2);
        }
        // insert the element at the specified index
        array[index] = element;
        size++;
    }

    // to get an element at an index
    public T getElement(int index){
        return array[index];
    }

    // to remove an element at a particular index
    public void remove(int index){
        if(index>=size || index<0){
            System.out.println("No element at this index");
        }else{
            for(int i=index;i<size-1;i++){
                array[i] = array[i+1];
            }
            array[size-1]=null;
            size--;
        }
    }
    public void ensureCapacity(int minCapacity){
        T[] temp = (T[])new Object[capacity*minCapacity];
        for (int i=0; i < capacity; i++){
            temp[i] = array[i];
        }
        array = temp;
        capacity = capacity * minCapacity;
    }

    //this function search fo element, update it accoridng to lanbda and return the new updated T


    @Override
    public String toString() {
        return "DynamicArray{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor = 1;

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IllegalArgumentException("No more elements available.");
                }
                T value = (T)array[cursor];
                if((T)array[cursor+2] == null) cursor = size ; //no printing null elements
                cursor++;
                return value;
            }

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public void remove() {
                rangeCheck(--cursor);
                DynamicArray.this.remove(cursor);
            }
        };
    }
    private void rangeCheck(int index) {
        if (index < 1 || index >= size) {
            throw new IndexOutOfBoundsException("Index : " + index);
        }
    }
}
