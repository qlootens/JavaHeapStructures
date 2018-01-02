package heap.binomial;

import heap.Element;
import heap.EmptyHeapException;
import heap.Heap;

public class BinomialHeap<T extends Comparable<T>> implements Heap<T> {

    private BinomialElement<T> root; //reference of root

    /**
     * Constructor BinomialHeap
     */
    public BinomialHeap() {
        root = null;
    }

    /**
     * Constructor BinomialHeap
     *
     * @param root: reference of BinomialElement, can be NULL
     */
    private BinomialHeap(BinomialElement<T> root) {
        this.root = root;
    }


    /**
     * Mutator to insert a BinomialElement in the BinomialHeap
     *
     * @param value: Value of the new BinomialElement
     * @return reference of the new BinomialElement
     */
    @Override
    public Element<T> insert(T value) {
        BinomialElement<T> newElement = new BinomialElement<T>(value, this);
        BinomialHeap<T> tmpBinoHeap = new BinomialHeap<T>(newElement);
        this.root = addHeap(tmpBinoHeap);
        return newElement;
    }


    /**
     * Mutator to detach a BinomialElement and reinsert in the BinomialHeap
     *
     * @param element: reference of the BinomialElement that will be detached and back insert in the BinomialHeap
     */
    void reEntryElement(BinomialElement<T> element) {
        element.setChild(null);
        element.setDegree(0);
        element.setParent(null);
        element.setNextHeap(null);
        BinomialHeap<T> tmpBinoHeap = new BinomialHeap<T>(element);
        this.root = addHeap(tmpBinoHeap);
    }


    /**
     * Find the smallest BinomialElement by taking the smallest root of the BinomialHeap
     *
     * @return reference of the smallest BinomialElement
     * @throws EmptyHeapException: if root is NULL
     */
    @Override
    public Element<T> findMin() throws EmptyHeapException {
        if (this.root == null) {
            throw new EmptyHeapException();
        } else {
            BinomialElement<T> minHeap = this.root;
            BinomialElement<T> nextHeap = minHeap.getNextHeap();

            while (nextHeap != null) {
                if (nextHeap.value().compareTo(minHeap.value()) < 0) {
                    minHeap = nextHeap;
                }
                nextHeap = nextHeap.getNextHeap();
            }

            return minHeap;
        }
    }

    /**
     * Mutator to remove the smallest element of the heap
     *
     * @return Value of the smallest BinomialElement before deletion
     * @throws EmptyHeapException: if root is NULL
     */
    @Override
    public T removeMin() throws EmptyHeapException {
        if (this.root == null) {
            throw new EmptyHeapException();
        }
        BinomialElement<T> minEle = this.root;
        BinomialElement<T> minPrev = null;
        BinomialElement<T> next = minEle.getNextHeap();
        BinomialElement<T> nextPrev = minEle;

        //find the smallest root in the root sequence
        while (next != null) {
            if (next.value().compareTo(minEle.value()) < 0) {
                minEle = next;
                minPrev = nextPrev;
            }
            nextPrev = next;
            next = next.getNextHeap();
        }

        removeRoot(minEle, minPrev); //remove the root in his binomial tree
        return minEle.value();
    }

    /**
     * Getter of root
     *
     * @return reference to the root
     */
    public BinomialElement<T> getRoot() {
        return this.root;
    }

    /**
     * Mutator for adding a BinomialHeap at this
     *
     * @param heap: reference of BinomialHeap, can be NULL
     * @return reference of root of the resulting BinomialHeap
     */
    private BinomialElement<T> addHeap(BinomialHeap<T> heap) {

        BinomialElement<T> newRoot = merge(this, heap);

        root = null;
        heap.root = null;

        if (newRoot == null) {
            return null;
        }

        BinomialElement<T> prevHeap = null;
        BinomialElement<T> currHeap = newRoot;
        BinomialElement<T> nextHeap = newRoot.getNextHeap();

        //Merge on all degrees and set all the references correct if necessary
        while (nextHeap != null) {

            if (currHeap.getDegree() != nextHeap.getDegree() || (nextHeap.getNextHeap() != null &&
                    nextHeap.getNextHeap().getDegree() == currHeap.getDegree())) {


                prevHeap = currHeap;
                currHeap = nextHeap; //Go to the next heap in the wile loop
            } else {

                //heapify the BinomialHeap
                if (currHeap.value().compareTo(nextHeap.value()) <= 0) {
                    currHeap.setNextHeap(nextHeap.getNextHeap());
                    mergeBinomialSameDegree(currHeap, nextHeap);
                } else {
                    if (prevHeap == null) {
                        newRoot = nextHeap;
                    } else {
                        prevHeap.setNextHeap(nextHeap);
                    }

                    mergeBinomialSameDegree(nextHeap, currHeap);
                    currHeap = nextHeap;
                }
            }

            nextHeap = currHeap.getNextHeap(); //Set the nextHeap for evaluation
        }

        return newRoot;
    }


    /**
     * Mutator to delete a BinomialElement in the BinomialHeap
     *
     * @param element: reference of the BinomialElement that will be deleted
     */
    void deleteElement(BinomialElement<T> element) {
        //let element become root of his tree
        bubbleUpToRoot(element);

        //find element in the root sequence and delete it
        if (root == element) {
            removeRoot(element, null);
        } else {
            BinomialElement<T> prev = root;
            while (prev.getNextHeap() != element) {

                prev = prev.getNextHeap();
            }
            removeRoot(element, prev);
        }
    }

    /**
     * Mutator to merge two binomial trees of the same degree by adding otherNodeTree at the minNodeTree
     *
     * @param minNodeTree:   reference of the smallest BinomialElement compared to otherNodeTree
     * @param otherNodeTree: reference of the other root
     */
    private void mergeBinomialSameDegree(BinomialElement<T> minNodeTree, BinomialElement<T> otherNodeTree) {
        otherNodeTree.setParent(minNodeTree);
        otherNodeTree.setNextHeap(minNodeTree.getChild());
        minNodeTree.setChild(otherNodeTree);
        minNodeTree.setDegree(minNodeTree.getDegree() + 1);
    }

    /**
     * Mutator for merging 2 Binomialheaps
     *
     * @param heap1: reference of BinomialHeap
     * @param heap2: reference of BinomialHeap
     * @return BinomialElement: reference of the new root
     */
    private BinomialElement<T> merge(BinomialHeap<T> heap1, BinomialHeap<T> heap2) {
        if (heap1.getRoot() == null) {
            return heap2.getRoot();
        } else if (heap2.getRoot() == null) {
            return heap1.getRoot();
        } else {
            BinomialElement<T> newRoot;
            BinomialElement<T> heap1Root = heap1.getRoot();
            BinomialElement<T> heap2Root = heap2.getRoot();

            //newRoot has to be the root with the smallest degree
            if (heap1.getRoot().getDegree() <= heap2.getRoot().getDegree()) {
                newRoot = heap1.getRoot();
                heap1Root = heap1Root.getNextHeap();
            } else {
                newRoot = heap2.getRoot();
                heap2Root = heap2Root.getNextHeap();
            }

            BinomialElement<T> tmpRoot = newRoot;

            //link the trees where necessary
            while (heap1Root != null && heap2Root != null) {

                if (heap1Root.getDegree() <= heap2Root.getDegree()) {
                    tmpRoot.setNextHeap(heap1Root);
                    heap1Root = heap1Root.getNextHeap();
                } else {
                    tmpRoot.setNextHeap(heap2Root);
                    heap2Root = heap2Root.getNextHeap();
                }

                tmpRoot = tmpRoot.getNextHeap();
            }

            if (heap1Root != null) {
                tmpRoot.setNextHeap(heap1Root);
            } else {
                tmpRoot.setNextHeap(heap2Root);
            }

            return newRoot;
        }
    }

    /**
     * Mutator to remove the root out of a BinomialTree
     *
     * @param rootElement:reference of root that will be deleted
     * @param prev:                 if in root sequence, reference of the previous root in the root sequence
     */
    private void removeRoot(BinomialElement<T> rootElement, BinomialElement<T> prev) {

        //remove root from the heap
        if (rootElement == root) {
            this.root = rootElement.getNextHeap();
        } else {
            prev.setNextHeap(rootElement.getNextHeap());
        }

        //reverse the order of root's children and make a new heap
        BinomialElement<T> newRoot = null;
        BinomialElement<T> child = rootElement.getChild();
        while (child != null) {

            BinomialElement<T> next = child.getNextHeap();
            child.setNextHeap(newRoot);
            child.setParent(null);
            newRoot = child;
            child = next;
        }
        BinomialHeap<T> newHeap = new BinomialHeap<T>(newRoot);

        //union the heaps and set its root as this.root
        this.root = addHeap(newHeap);
    }

    public void print() {
        System.out.println("Binomial heap:");
        if (root != null) {
            root.print(0);
        }
    }


    /**
     * Bubble the element up to the root of his tree
     *
     * @param element: reference of the element that has to become root
     */
    private void bubbleUpToRoot(BinomialElement<T> element) {

        BinomialElement<T> parent = element.getParent();
        if (parent != null) {
            swapChildParent(element, parent);
            bubbleUpToRoot(element);
        }

    }


    /**
     * Mutator to swap Child with Parent and set all his references
     *
     * @param child:  reference of the child
     * @param parent: reference of the parent
     */
    private void swapChildParent(BinomialElement<T> child, BinomialElement<T> parent) {
        BinomialElement<T> p_parent = parent.getParent();
        BinomialElement<T> p_child = parent.getChild();
        BinomialElement<T> c_child = child.getChild();
        BinomialElement<T> c_next_heap = child.getNextHeap();
        BinomialElement<T> p_next_heap = parent.getNextHeap();

        //set parent of parent
        if (p_parent == null) {
            if (parent == root) {
                this.root = child;
            } else {

                BinomialElement<T> tmp = root;
                while (tmp.getNextHeap() != parent) {
                    tmp = tmp.getNextHeap();

                }
                tmp.setNextHeap(child);
            }

        } else {

            BinomialElement<T> tmp = p_parent.getChild();
            if (tmp == parent) {
                tmp.setChild(child);
            } else {
                while (tmp.getNextHeap() != parent) {
                    tmp = tmp.getNextHeap();

                }
                tmp.setNextHeap(child);
            }
            if (p_parent.getChild() == parent) {
                p_parent.setChild(child);
            }
        }

        //Set children of child
        if (c_child != null) {
            BinomialElement<T> tmp = c_child;
            while (tmp != null) {
                tmp.setParent(parent);
                tmp = tmp.getNextHeap();

            }
        }

        //Set children of parent
        if (p_child == child) {
            parent.setParent(child);
            child.setChild(parent);

            BinomialElement<T> tmp = c_next_heap;
            while (tmp != null) {
                tmp.setParent(child);
                tmp = tmp.getNextHeap();
            }

        } else {
            BinomialElement<T> tmp = p_child;
            child.setChild(p_child);
            p_child.setParent(child);
            while (tmp.getNextHeap() != null) {
                tmp.setParent(child);
                if (tmp.getNextHeap() == child) {
                    tmp.setNextHeap(parent);
                    tmp = c_next_heap;
                    if (c_next_heap != null) {
                        c_next_heap.setParent(child);
                    } else {
                        break;
                    }
                } else {
                    tmp = tmp.getNextHeap();
                }

            }
            if (tmp != null) {
                tmp.setParent(child);
            }
        }

        parent.setParent(child);
        parent.setNextHeap(c_next_heap);
        parent.setChild(c_child);


        child.setNextHeap(p_next_heap);
        child.setParent(p_parent);

        int degree = child.getDegree();
        child.setDegree(parent.getDegree());
        parent.setDegree(degree);


    }

}