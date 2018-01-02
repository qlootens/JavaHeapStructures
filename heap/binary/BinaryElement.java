package heap.binary;

import heap.Element;

public class BinaryElement<T extends Comparable<T>> implements Element<T> {
    private BinaryHeap<T> heap; //Reference to heap
    private T value; //Value of Element


    /**
     * Constructor of BinaryElement
     *
     * @param value: of the Element, needs to be a Comparable
     * @param heap:  reference to the heap
     */
    BinaryElement(T value, BinaryHeap<T> heap) {
        this.value = value;
        this.heap = heap;
    }

    /**
     * Getter of the value of the BinaryElement
     *
     * @return value
     */
    @Override
    public T value() {
        return value;
    }

    /**
     * Remove the BinaryElement out the heap
     */
    @Override
    public void remove() {
        heap.removeElement(this);
    }

    /**
     * @param value: new value of the BinaryElement, need to be a Comparable
     */
    @Override
    public void update(T value) {

        if (this.value.compareTo(value) < 0) {

            this.value = value;
            heap.updateElement(this, true); //bubbleDown
        } else {

            this.value = value;
            heap.updateElement(this, false); //bubbleUp
        }

    }

}
