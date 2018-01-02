package heap.pairing;

import heap.Element;
import heap.EmptyHeapException;
import heap.Heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class PairingHeap<T extends Comparable<T>> implements Heap<T> {

    public PairingElement<T> root; //reference of root
    private Queue<PairingElement<T>> pqueue; //priorityqueue

    /**
     * Constructor of PairingHeap
     */
    public PairingHeap() {
        pqueue = new PriorityQueue<>(new ComparatorQueue<T>());
    }


    /**
     * Mutator for inserting a PairingElement in the PairingHeap
     *
     * @param value: value of the PairingElement
     * @return reference of the added PairingElement
     */
    @Override
    public Element<T> insert(T value) {
        PairingElement<T> newNode = new PairingElement<>(value, this);
        mergeWithRoot(newNode);
        return newNode;
    }

    /**
     * Getter of the smallest PairingElement of the PairingHeap -> root
     *
     * @return reference of the smallest PairingElement
     * @throws EmptyHeapException: thrown when root is NULL
     */
    @Override
    public Element<T> findMin() throws EmptyHeapException {
        if (root == null) {
            throw new EmptyHeapException();
        }
        return this.root;
    }

    /**
     * Mutator for deleting the smallest PairingElement
     *
     * @return value of the smallest PairingElement before deletion
     * @throws EmptyHeapException: thrown when root is NULL
     */
    @Override
    public T removeMin() throws EmptyHeapException {
        if (this.root == null) {
            throw new EmptyHeapException();
        }
        return deleteElement(this.root).value();

    }


    /**
     * Mutator for merging two PairingTrees. The greatest root will be added as child of the other root.
     *
     * @param rootA: reference of root A
     * @param rootB: reference of root B
     * @return reference of the resulting root
     */
    private PairingElement<T> merge(PairingElement<T> rootA, PairingElement<T> rootB) {
        if (rootA.value().compareTo(rootB.value()) < 0) {
            rootA.addChild(rootB);
            return rootA;
        } else {
            rootB.addChild(rootA);
            return rootB;
        }
    }

    /**
     * Specific Mutator for merging a root with the this.root
     *
     * @param element: reference of the root of it's tree
     */
    private void mergeWithRoot(PairingElement<T> element) {
        if (this.root == null) {
            this.root = element;
        } else {
            this.root = merge(this.root, element);
        }
    }

    /**
     * Method for adding the targets children into a priorityQueue. Used for restoring the PairingHeap.
     *
     * @param element: reference of the PairingElement
     * @return reference of the queue
     */
    private Queue<PairingElement<T>> moveChildrenToQueue(PairingElement<T> element) {
        PairingElement<T> child = element.child;
        element.child = null; // Detach children from node
        while (child != null) { // Traverse through sibling chain
            PairingElement<T> right = child.sibR; //remember the right sibling
            child.makeRoot();
            pqueue.add(child);
            child = right;
        }
        return pqueue;
    }

    /**
     * @return reference to the root of the pairing heap
     * that is the union of all pairing heaps in the queue of
     * the pqueue heaps
     */
    private PairingElement<T> mergeQueue() {
        while (pqueue.size() > 1) {
            pqueue.add(merge(pqueue.remove(), pqueue.remove()));
        }
        return pqueue.remove();
    }

    /**
     * Mutator called when the value of the PairingElement increases
     *
     * @param element: reference of the PairingElement that will be decreased
     */
    void decreasePriority(PairingElement<T> element) {
        if (element.child != null) {
            moveChildrenToQueue(element);
            PairingElement<T> melded = mergeQueue();
            this.root = merge(this.root, melded);
            this.root.makeRoot();
        }
    }

    /**
     * Mutator called when the value of the PairingElement decreases
     *
     * @param element: reference of the PairingElement that will be increased
     */
    void increasePriority(PairingElement<T> element) {
        if (element != this.root) {
            //references to NULL
            element.removeFromChain();
            element.sibL = null;
            element.sibR = null;

            //merge now with the root
            this.root = merge(this.root, element);
            this.root.makeRoot(); //setting references
        }
    }

    /**
     * Mutator for deleting a PairingElement out of the PairingHeap
     *
     * @param element: reference of the PairingElement that will be deleted
     * @return reference of the deleted PairingElement
     */
    PairingElement<T> deleteElement(PairingElement<T> element) {
        if (element.child == null) {
            //when no children, just remove from sibling list
            element.removeFromChain();
            if (element == this.root){
                this.root = null;
            }
        } else {
            //preserve children
            moveChildrenToQueue(element);
            PairingElement<T> melded = mergeQueue();
            if (element == this.root) {
                this.root = melded;
            } else {
                element.removeFromChain();
                this.root = merge(root, melded);
            }
        }
        element.setDeleted(); //preserve removed
        if (element.prev != null) {
            element.prev.setNext(element.next); //preserve iterationlist
        }
        return element;
    }


}
