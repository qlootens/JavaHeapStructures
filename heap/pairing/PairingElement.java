package heap.pairing;

import heap.Element;

public class PairingElement<T extends Comparable<T>> implements Element<T> {

    public PairingElement<T> child; //reference to the leftmost child
    PairingElement<T> sibL; //reference of the left sibling in sibling list
    PairingElement<T> sibR; //reference of the right sibling in the sibling list
    PairingElement<T> next; //reference of the next node in the iteration list
    PairingElement<T> prev; //reference of the previous node in the iteration list
    private PairingHeap<T> heap; //reference of the corresponding heap
    private T value; //value of the PairingElement


    /**
     * Constructor PairingElement
     *
     * @param value: value of the PairingElement
     * @param heap:  reference of the corresponding heap
     */
    PairingElement(T value, PairingHeap<T> heap) {
        this.heap = heap;
        this.value = value;
    }

    /**
     * Getter of the value of the PairingElement
     *
     * @return value
     */
    @Override
    public T value() {
        return this.value;
    }

    /**
     * Remove the PairingElement out the heap
     */
    @Override
    public void remove() {
        heap.deleteElement(this);
    }

    /**
     * @param value: new value of the PairingElement, need to be a Comparable
     */
    @Override
    public void update(T value) {
        if (value.compareTo(this.value) < 0) {
            this.value = value;
            heap.increasePriority(this);
        } else if (value.compareTo(this.value) > 0) {
            this.value = value;
            heap.decreasePriority(this);
        }


    }

    /**
     * Setter of the left sibling
     *
     * @param element: reference of the new left sibling
     */
    private void setLeft(PairingElement<T> element) {
        this.sibL = element;
        element.sibR = this;
    }

    /**
     * Setter of the next node
     *
     * @param element: reference of the new next node in the iteration list
     */
    void setNext(PairingElement<T> element) {
        this.next = element;
        element.prev = this;
    }

    /**
     * @return true -> this PairingElement is the most left child of his parent
     */
    private boolean isLeftMostChild() {
        return sibL.child == this;
    }

    /**
     * @return true -> this PairingElement is the most right child of his parent
     */
    private boolean isRightmostChild() {
        return sibR == null;
    }

    /**
     * Mutator for setting this PairingElement as deleted
     */
    void setDeleted() {
        this.sibL = null;
    }


    /**
     * Mutator for adding a new child at this PairingElement
     *
     * @param newChild: reference of a new child
     */
    void addChild(PairingElement<T> newChild) {
        if (this.child == null) {
            // There are no children
            this.child = newChild;
            newChild.sibL = this;
        } else if (this.child.isRightmostChild()) {
            // Has one child
            newChild.setLeft(this.child);
        } else {
            // Has at least 2 children
            this.child.sibR.setLeft(newChild); // add as a second child in chain
            newChild.setLeft(this.child);
        }
    }

    /**
     * Mutator for removing this PairingElement out of the chain by resetting it references
     */
    void removeFromChain() {
        // preserve Child
        if (this.isLeftMostChild()) {
            this.sibL.child = this.sibR;
        } else {
            this.sibL.sibR = this.sibR;
        }
        // preserve Parent
        if (!this.isRightmostChild()) {
            sibR.sibL = this.sibL;
        }
    }

    /**
     * Mutator for setting this PairingElement as root
     */
    void makeRoot() {
        this.sibL = this; // Satisfy Parent
        this.sibR = null; // Satisfy SiblingChain
    }


}
