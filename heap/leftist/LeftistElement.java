package heap.leftist;

import heap.Element;

public class LeftistElement<T extends Comparable<T>> implements Element<T> {

    private T value; //Value of Element
    private LeftistHeap<T> leftistHeap; //Reference to heap
    private int npl; //NullPathLength

    private LeftistElement<T> rightChild, leftChild, parent; //reference of rightChild, leftChild and parent

    /**
     * Constructor of LeftistElement
     *
     * @param value: value of LeftistElement
     * @param heap:  reference of corresponding heap
     */
    LeftistElement(T value, LeftistHeap<T> heap) {
        this.leftistHeap = heap;
        this.value = value;
        npl = 0;
    }

    /**
     * Getter of the value of the LeftistElement
     *
     * @return value
     */
    @Override
    public T value() {
        return this.value;
    }

    /**
     * Remove the LeftistElement out the heap
     */
    @Override
    public void remove() {
        leftistHeap.deleteElement(this);

    }

    /**
     * @param value: new value of the LeftistElement, need to be a Comparable
     */
    @Override
    public void update(T value) {
        leftistHeap.deleteElement(this);
        this.value = value;
        leftistHeap.add(this);

    }


    /**
     * Alternative method for updating element, has greater complexity but performance could be better
     *
     * @param value: new value of the LeftistElement, need to be a Comparable
     */
    public void update2(T value) {
        if (this.value.compareTo(value) < 0) {
            this.value = value;
            leftistHeap.bubbleDown(this);
        } else {
            this.value = value;
            leftistHeap.bubbleUp(this, false);
        }

    }

    /**
     * Getter of leftChild
     *
     * @return reference of leftChild, can be NULL
     */
    public LeftistElement<T> getLeftChild() {
        return leftChild;
    }

    /**
     * Setter of leftChild
     *
     * @param leftChild: new reference of leftChild, can be NULL
     */
    void setLeftChild(LeftistElement<T> leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * Getter of rightChild
     *
     * @return reference of rightChild, can be NULL
     */
    public LeftistElement<T> getRightChild() {
        return rightChild;
    }

    /**
     * Setter of rightChild
     *
     * @param rightChild: new reference of rightChild, can be NULL
     */
    void setRightChild(LeftistElement<T> rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * Getter of NullPathLength
     *
     * @return npl: integer of NullPathLength
     */
    public int getNpl() {
        return npl;
    }

    /**
     * Setter of NullPathLength
     *
     * @param npl: new value of NullPathlength
     */
    void setNpl(int npl) {
        this.npl = npl;
    }

    /**
     * @return true -> has a right child
     * false -> has no right child
     */
    public Boolean hasRightChild() {
        return this.rightChild != null;
    }

    /**
     * @return boolean: true -> has a left child
     * false -> has no left child
     */
    public Boolean hasLeftChild() {
        return this.leftChild != null;
    }

    /**
     * Getter of parent
     *
     * @return reference of parent, can be NULL
     */
    public LeftistElement<T> getParent() {
        return parent;
    }

    /**
     * Setter of parent
     *
     * @param parent: new reference of parent, can be NULL
     */
    public void setParent(LeftistElement<T> parent) {
        this.parent = parent;
    }
}
