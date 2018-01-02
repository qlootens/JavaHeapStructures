package heap.binary;

import heap.Element;
import heap.EmptyHeapException;
import heap.Heap;
import java.util.ArrayList;
import java.util.Collections;

public class BinaryHeap<T extends Comparable<T>> implements Heap<T> {

    private final ArrayList<BinaryElement<T>> binaryElements; //Array of the BinaryElements
    private int size; //Size of the ArrayList

    /**
     * Constructor BinaryHeap
     */
    public BinaryHeap() {

        binaryElements = new ArrayList<>();
        size = 0;
    }

    /**
     * @param value: The value of the new BinaryElement
     * @return BinaryElement: reference to the added BinaryElement
     */
    @Override
    public Element<T> insert(T value) {
        BinaryElement<T> binaryElement = new BinaryElement<>(value, this);
        size++;
        binaryElements.add(binaryElement);
        bubbleUp(binaryElements.indexOf(binaryElement));
        return binaryElement;

    }

    /**
     * @return BinaryElement: Reference of the root
     * @throws EmptyHeapException: will be thrown if BinaryHeap has no root
     */
    @Override
    public Element<T> findMin() throws EmptyHeapException {
        if (binaryElements.isEmpty()) {
            throw new EmptyHeapException();
        } else {
            return binaryElements.get(0); //The root of the BinaryHeap
        }
    }

    /**
     * Mutator that removes the smallest BinaryElement of the BinaryHeap
     *
     * @return BinaryElement: Reference of the deleted smallest BinaryElement
     * @throws EmptyHeapException: will be thrown if BinaryHeap has no root
     */
    @Override
    public T removeMin() throws EmptyHeapException {
        if (binaryElements.isEmpty()) {
            throw new EmptyHeapException();
        } else {
            Element<T> minEle = findMin();
            size--;
            if (size > 0) {
                swapElements(0, size); //swap the last BinaryElement with the root
                binaryElements.remove(size); //remove the last element
                bubbleDown(0); //restore the heap
            }
            if (size == 0) {
                binaryElements.remove(0);
            }
            return minEle.value();
        }

    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    /**
     * Recursive function to restore the heap when necessary. If the parent has a greater value than the BinaryElement
     * at index, swap them and call the bubbleUp again on the same BinaryElement with a new index.
     *
     * @param index: index of the BinaryElement that has to bubbleUp
     */
    private void bubbleUp(int index) {
        int parentIndex;
        if (index != 0) {
            parentIndex = getParentIndex(index);
            if (binaryElements.get(index).value().compareTo(binaryElements.get(parentIndex).value()) < 0) {
                swapElements(index, parentIndex);
                bubbleUp(parentIndex);
            }
        }
    }

    /**
     * Analogue at the bubbleUp function, only now we will evaluate the BinaryElement with his children.
     *
     * @param index: index of the BinaryElement that has to bubbleDown
     */
    private void bubbleDown(int index) {
        int leftChildIndex, rightChildIndex;
        int minIndex = -1;
        leftChildIndex = getLeftChildIndex(index);
        rightChildIndex = getRightChildIndex(index);

        // Take the smallest Child
        if (rightChildIndex >= size) {
            if (leftChildIndex < size) {
                minIndex = leftChildIndex;
            }
        } else {
            if (binaryElements.get(leftChildIndex).value().compareTo(binaryElements.get(rightChildIndex).value()) <= 0) {
                minIndex = leftChildIndex;
            } else {
                minIndex = rightChildIndex;
            }
        }

        if (minIndex == -1) {
            return; //There are no children
        }

        //Evaluate and swap if necessary
        if (binaryElements.get(minIndex).value().compareTo(binaryElements.get(index).value()) <= 0) {
            swapElements(index, minIndex);
            bubbleDown(minIndex);
        }

    }

    /**
     * Simple mutator for swapping BinaryElements.
     *
     * @param index1: Index of BinaryElement 1
     * @param index2: Index of BinaryElement 2
     *                Swap now BinaryElement 1 and 2
     */
    private void swapElements(int index1, int index2) {
        Collections.swap(binaryElements, index1, index2);
    }

    /**
     * Mutator to restore the BinaryHeap structure (heapify).
     *
     * @param element: reference of the updated BinaryElement
     * @param greater: if not greater -> bubbleUp else bubbleDown
     */
    void updateElement(BinaryElement<T> element, Boolean greater) {
        int index = binaryElements.indexOf(element);
        if (!greater) {
            bubbleUp(index);
        } else {
            bubbleDown(index);
        }
    }

    /**
     * The BinaryElement will be swapped with the last BinaryElement. Then it can be deleted.
     * The swapped BinaryElement has to bubbleDown/bubbleUp to restore the BinaryHeap.
     *
     * @param element: reference of the element that has to be deleted
     */
    void removeElement(BinaryElement<T> element) {
        int index = binaryElements.indexOf(element);
        if (index != size - 1) {
            swapElements(index, size - 1); //Swap last Element with element

        }
        size--;
        binaryElements.remove(size);

        //Restore the BinaryHeap if necessary
        if (index != size) {

            BinaryElement<T> tmp = binaryElements.get(index);
            int parentIndex = getParentIndex(index);
            if (parentIndex == 0) {
                bubbleDown(index);
            } else {

                if (tmp.value().compareTo(binaryElements.get(parentIndex).value()) < 1) {
                    bubbleUp(index);
                } else {
                    bubbleDown(index);
                }
            }
        }
    }

    public void printTree() {
        System.out.println("BEGIN");
        for (int i = 0; i < size; i++) {
            System.out.println(binaryElements.get(i).value());
        }
        System.out.println("END");
    }

    /**
     * Safe getter of the values of the BinaryHeap
     *
     * @return ArrayList<T>
     */
    public ArrayList<T> getBinaryHeapArray() {
        ArrayList<T> values = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            values.add(binaryElements.get(i).value());
        }
        return values;
    }

}
