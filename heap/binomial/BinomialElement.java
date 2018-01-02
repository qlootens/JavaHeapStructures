package heap.binomial;

import heap.Element;

public class BinomialElement<T extends Comparable<T>> implements Element<T> {

    private T value; //value of BinomialElement
    private int degree; //degree of the BinomialElement in the binomialHeap
    private BinomialElement<T> parent; //reference of parent
    private BinomialElement<T> child; //reference of child
    private BinomialElement<T> nextHeap; //reference to nextHeap
    private BinomialHeap<T> binomialHeap; //reference of the binomialHeap

    /**
     * Constructor of BinomialElement.
     *
     * @param value:        value of the BinomialElement, has to be a Comparable
     * @param binomialHeap: reference of the corresponding BinomialHeap
     */
    BinomialElement(T value, BinomialHeap<T> binomialHeap) {
        this.value = value;
        this.binomialHeap = binomialHeap;
        this.degree = 0;
    }

    /**
     * Getter of value
     *
     * @return T: a Comparable
     */
    @Override
    public T value() {
        return value;
    }

    /**
     * Mutator to remove this BinomialElement in BinomialHeap.
     */
    @Override
    public void remove() {
        this.binomialHeap.deleteElement(this);
    }

    /**
     * Mutator to update the value of the BinomialElement.
     *
     * @param value: new value, has to be a Comparable
     */
    @Override
    public void update(T value) {
        this.value = value;
        this.binomialHeap.deleteElement(this);
        this.binomialHeap.reEntryElement(this);
    }

    /**
     * Getter of nextHeap
     *
     * @return BinomialElement: reference to next Heap
     */
    public BinomialElement<T> getNextHeap() {
        return nextHeap;
    }

    /**
     * Getter of Parent
     *
     * @return BinomialElement: reference to parent
     */
    public BinomialElement<T> getParent() {
        return parent;
    }

    /**
     * Getter of Child
     *
     * @return BinomialElement: reference to child
     */
    public BinomialElement<T> getChild() {
        return child;
    }

    /**
     * Getter of degree
     *
     * @return int: degree of the BinomialElement
     */
    int getDegree() {
        return degree;
    }

    /**
     * Setter of degree
     *
     * @param degree: new degree
     */
    void setDegree(int degree) {
        this.degree = degree;
    }

    /**
     * Setter of nextheap
     *
     * @param nextHeap: new reference of nextHeap, can be NULL
     */
    void setNextHeap(BinomialElement<T> nextHeap) {
        this.nextHeap = nextHeap;
    }

    /**
     * Setter of Parent
     *
     * @param parent: new reference of parent, can be NULL
     */
    public void setParent(BinomialElement<T> parent) {
        this.parent = parent;
    }


    /**
     * Setter of Child
     *
     * @param child: new reference of child, can be NULL
     */
    void setChild(BinomialElement<T> child) {
        this.child = child;
    }

    void print(int level) {
        BinomialElement<T> curr = this;
        while (curr != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < level; i++) {
                sb.append(" ");
            }
            sb.append(curr.value().toString());
            System.out.println(sb.toString());
            if (curr.child != null) {
                curr.child.print(level + 1);
            }
            curr = curr.getNextHeap();
        }
    }

    /**
     * Setter of Value
     *
     * @param value: new value
     */
    public void setValue(T value) {
        this.value = value;
    }


}
