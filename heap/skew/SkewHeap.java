package heap.skew;

import heap.Element;
import heap.EmptyHeapException;
import heap.Heap;

public class SkewHeap<T extends Comparable<T>> implements Heap<T> {


    private SkewElement<T> root; //reference of root

    /**
     * Constructor SkewHeap
     */
    public SkewHeap(){

    }

    /**
     * Mutator for inserting a SkewElement in the SkewHeap
     *
     * @param value: value of the SkewElement
     * @return reference of the added SkewElement
     */
    @Override
    public Element<T> insert(T value) {
        SkewElement<T> newElement = new SkewElement<>(value, this);
        this.root = merge(this.root, newElement); //simply merge the new element with the root
        return newElement;
    }

    /**
     * Getter of the smallest SkewElement of the SkewHeap -> root
     *
     * @return reference of the smallest SkewElement
     * @throws EmptyHeapException: will be thrown if root is NULL
     */
    @Override
    public Element<T> findMin() throws EmptyHeapException {
        if (this.root == null){
            throw new EmptyHeapException();
        }
        return this.root;
    }

    /**
     * Mutator for deleting the smallest SkewElement
     *
     * @return value of the smallest SkewElement before deletion
     * @throws EmptyHeapException: will be thrown if root is NULL
     */
    @Override
    public T removeMin() throws EmptyHeapException {
        if (this.root == null){
            throw new EmptyHeapException();
        }

        T v = this.root.value();
        this.root = merge(root.leftChild, root.rightChild);
        return v;
    }

    /**
     * Mutator for merging two roots of two SkewHeaps
     *
     * @param root1: reference of SkewElement
     * @param root2: reference of SkewElement
     * @return reference of the resulting root of the SkewHeap
     */
    private SkewElement<T> merge(SkewElement<T> root1, SkewElement<T> root2) {
        SkewElement<T> firstRoot = root1;
        SkewElement<T> secondRoot = root2;
        if(firstRoot == null){
            return secondRoot;
        } else if(secondRoot == null){
            return firstRoot;
        }


        if (firstRoot.value().compareTo(secondRoot.value()) <= 0){
            SkewElement<T> temp = firstRoot.rightChild;
            firstRoot.rightChild = firstRoot.leftChild;

            firstRoot.leftChild = merge(temp, secondRoot);
            firstRoot.leftChild.parent = firstRoot;

            return firstRoot;
        }
        else {
            SkewElement<T> tempp = secondRoot.leftChild;
            secondRoot.leftChild = secondRoot.rightChild;

            secondRoot.rightChild = merge(tempp, firstRoot);
            secondRoot.rightChild.parent = secondRoot;

            return secondRoot;
        }

    }

    void removeElement(SkewElement<T> element){
        SkewElement<T> rightChild = element.rightChild;
        SkewElement<T> leftChild = element.leftChild;
        element.rightChild = null;
        element.leftChild = null;

        if (element.parent != null) {
            if (element.parent.leftChild == element) {
                element.parent.leftChild = null;
            } else {
                element.parent.rightChild = null;
            }
        }
        if (element == this.root){
            this.root = merge(rightChild, leftChild);
        } else {


            SkewElement<T> newheap = merge(this.root, leftChild);
            this.root = merge(newheap, rightChild);
        }

    }

    public void addElement(SkewElement<T> element){
        this.root = merge(element, this.root);
    }

    public SkewElement<T> getRoot() {
        return root;
    }

}
