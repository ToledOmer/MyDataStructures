import java.util.Iterator;

/**
 * @param <T>
 * @author Omer Toledo - inspired byt GeeksforGeeks
 * @
 */
// Java implementation of Min Heap
public class MinHeap<T extends Comparable<T>> implements Iterable<T> {

    private DynamicArray<T> Heap = new DynamicArray<T>();
    private int size;
    private static final int DEFAULT = 3;
    private static final int FRONT = 1;

    public MinHeap(int capacity) {
        this.size = 0;
        Heap = new DynamicArray<T>(capacity);
    }

    public MinHeap() {
        new MinHeap(DEFAULT);
    }

    // Function to return the position of
    // the parent for the node currently
    // at pos
    private int parent(int pos) {
        return pos / 2;
    }

    // Function to return the position of the
    // left child for the node currently at pos
    private int leftChild(int pos) {
        return (2 * pos);
    }

    // Function to return the position of
    // the right child for the node currently
    // at pos
    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    // Function that returns true if the passed
    // node is a leaf node
    private boolean isLeaf(int pos) {
        if (pos >= (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }

    // Function to swap two nodes of the heap
    private void swap(int fpos, int spos) {
        T tmp;

        tmp = Heap.getElement(fpos);
        Heap.addElement(fpos, Heap.getElement(spos));
        Heap.addElement(spos, tmp);
    }

    // Function to heapify the node at pos
    private void minHeapify(int pos) {

        // If the node is a non-leaf node and greater
        // than any of its child
        if (!isLeaf(pos)) {
            if ((Heap.getElement(rightChild(pos)) != null && Heap.getElement(leftChild(pos)) != null)
                    &&
                    (Heap.getElement(pos).compareTo(Heap.getElement(leftChild(pos))) > 0
                            || Heap.getElement(pos).compareTo(Heap.getElement(rightChild(pos))) > 0)) {

                // Swap with the left child and heapify
                // the left child
                if (Heap.getElement(leftChild(pos)).compareTo(Heap.getElement(rightChild(pos))) < 0) {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                }

                // Swap with the right child and heapify
                // the right child
                else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }


        }
    }

    // Function to insert a node into the heap
    public void insert(T element) {

        Heap.addElement(size++, element);
        while ((Heap.getElement(parent(size)) != null && Heap.getElement(size) != null)
                && Heap.getElement(size).compareTo(Heap.getElement(parent(size))) < 0) {
            swap(size, parent(size));
            size = parent(size);
        }
    }

    public void insertAt(int newPosition, T element) {

        Heap.addElement(newPosition, element);

        while ((Heap.getElement(parent(newPosition)) != null && Heap.getElement(newPosition) != null)
                && Heap.getElement(newPosition).compareTo(Heap.getElement(parent(newPosition))) < 0) {
            swap(newPosition, parent(newPosition));
            newPosition = parent(newPosition);
        }
    }

    // Function to print the contents of the heap
    public void print() {
        if (size == 1) {
            System.out.println("ROOT/PARENT : " + (Heap.getElement(0) != null ? Heap.getElement(0).toString() : "null"));
        } else {
            for (int i = 0; i < size / 2; i++) {
                System.out.print(" (PARENT : " + (Heap.getElement(i) != null ? Heap.getElement(i).toString() : "null")
                        + " LEFT CHILD : " + (Heap.getElement((2 * i) + 1) != null ? Heap.getElement((2 * i) + 1).toString() : "null")
                        + " RIGHT CHILD :" + (Heap.getElement((2 * i) + 2) != null ? Heap.getElement((2 * i) + 2).toString() : "null") + ")");
                System.out.println();
            }
        }
    }

    // Function to build the min heap using
    // the minHeapify
    public void minHeap() {
        for (int pos = (size / 2); pos >= 1; pos--) {
            minHeapify(pos);
        }
    }

    // Function to remove and return the minimum
    // element from the heap
    public T remove() {
        T popped = Heap.getElement(FRONT);
        Heap.addElement(FRONT, Heap.getElement(size--));
        minHeapify(FRONT);
        return popped;
    }

    @Override
    public Iterator<T> iterator() {
        return Heap.iterator();
    }


    public interface upadteGeneric<T> {
        T operate(T toUpdate);
    }
    //this function search fo element, update it accoridng to lanbda and return the new updated T

    public T getAndUpdate(T obj, upadteGeneric op) {
        for (int i = 1; i <= size; i++) {
            if (Heap.getElement(i) != null) {
                if (Heap.getElement(i).equals(obj)) {
                    T ret = (T) op.operate(Heap.getElement(i));
                    insertAt(i, ret);
                    return ret;
                }
            }
        }
        //didnt find!
        return null;
    }

    @Override
    public String toString() {
        return "MinHeap{" +
                "Heap=" + Heap.toString() +
                ", size=" + size +
                '}';
    }
}
