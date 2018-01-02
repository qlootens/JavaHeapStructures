package heap.skew;

import heap.Element;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

public class SkewElement<T extends Comparable<T>> implements Element<T> {
    private T value; //value of the SkewElement
    public SkewElement<T> leftChild; //reference of the left child
    public SkewElement<T> rightChild; //reference of the right child
    public SkewElement<T> parent; //reference of the parent
    private SkewHeap<T> heap; //reference of the corresponding heap

    /**
     * Constructor SkewElement
     *
     * @param value: value of the SkewElement
     * @param heap: reference of the corresponding heap
     */
    SkewElement(T value, SkewHeap<T> heap){
        this.heap = heap;
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }


    /**
     * Getter of the value of the SkewElement
     *
     * @return value
     */
    @Override
    public T value() {
        return value;
    }

    /**
     * Remove the SkewElement out of the SkewHeap
     */
    @Override
    public void remove() {
        this.heap.removeElement(this);

    }

    /**
     * @param value: new value of the SkewElement, need to be a Comparable
     */
    @Override
    public void update(T value) {
        this.heap.removeElement(this);
        this.value = value;
        this.heap.addElement(this);
    }

    /**
     * Utility method to print the tree
     *
     * @param out: Writer
     * @throws IOException: IO
     */
    public void printTree(OutputStreamWriter out) throws IOException {
        if (rightChild != null) {
            rightChild.printTree(out, true, "");
        }
        printNodeValue(out);
        if (leftChild != null) {
            leftChild.printTree(out, false, "");
        }
    }

    /**
     * Utility method for printing the value of SkewElement
     *
     * @param out
     * @throws IOException
     */
    private void printNodeValue(OutputStreamWriter out) throws IOException {
        if (value == null) {
            out.write("<null>");
        } else {
            out.write(value.toString());
        }
        out.write('\n');
    }

    /**
     * Utility method for printing the tree
     *
     * @param out
     * @param isRight
     * @param indent
     * @throws IOException
     */
    private void printTree(OutputStreamWriter out, boolean isRight, String indent) throws IOException {
        if (rightChild != null) {
            rightChild.printTree(out, true, indent + (isRight ? "        " : " |      "));
        }
        out.write(indent);
        if (isRight) {
            out.write(" /");
        } else {
            out.write(" \\");
        }
        out.write("----- ");
        printNodeValue(out);
        if (leftChild != null) {
            leftChild.printTree(out, false, indent + (isRight ? " |      " : "        "));
        }
    }
}
