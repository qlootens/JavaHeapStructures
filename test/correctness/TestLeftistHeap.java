package test.correctness;

import heap.EmptyHeapException;
import heap.leftist.LeftistElement;
import heap.leftist.LeftistHeap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class TestLeftistHeap {

    private int AMOUNT_ELEMENTS = 10000;

    @Test
    public void testLeftistHeap() throws EmptyHeapException {
        ArrayList<Integer> values = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            values.add(i);
        }

        Collections.shuffle(values);

        ArrayList<LeftistElement<Integer>> elements = new ArrayList<>();
        LeftistHeap<Integer> testHeap = new LeftistHeap<>();

        for (Integer i: values){
            elements.add((LeftistElement<Integer>) testHeap.insert(i));
        }

        assertEquals("Geen LeftistHeap.", true, isLeftistHeap(elements));
    }

    @Test
    public void testLeftistHeapMinimum() throws EmptyHeapException {
        ArrayList<Integer> valuesUpdate = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            valuesUpdate.add(i);
        }

        Collections.shuffle(valuesUpdate);

        ArrayList<LeftistElement<Integer>> elements = new ArrayList<>();
        LeftistHeap<Integer> testHeap = new LeftistHeap<>();

        for (Integer i: valuesUpdate){
            elements.add((LeftistElement<Integer>) testHeap.insert(i));
        }

        LeftistElement<Integer> mininumElement = elements.get(0);

        for (LeftistElement<Integer> element: elements){
            if (mininumElement.value() > element.value()){
                mininumElement = element;
            }
        }

        assertEquals("Kleinste element is incorrect!", true, (mininumElement == testHeap.findMin()));

    }

    @Test
    public void testLeftistHeapUpdates() throws EmptyHeapException {

        ArrayList<Integer> values = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            values.add(i);
        }

        Collections.shuffle(values);

        ArrayList<LeftistElement<Integer>> elements = new ArrayList<>();
        LeftistHeap<Integer> testHeap = new LeftistHeap<>();

        for (Integer i: values){
            elements.add((LeftistElement<Integer>) testHeap.insert(i));
        }

        int minimum = 0;
        int maximum = 3 * AMOUNT_ELEMENTS;
        int randomNum;

        randomNum = minimum + (int)(Math.random() * maximum);

        for (int j = 0; j <= AMOUNT_ELEMENTS-1; j++){
            elements.get(j).update(randomNum);
            randomNum = minimum + (int)(Math.random() * maximum);
        }

        assertEquals("Geen LeftistHeap na updates elementen.", true, isLeftistHeap(elements));

    }

    @Test
    public void testLeftistHeapRemovals() throws EmptyHeapException {
        ArrayList<Integer> valuesUpdate = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            valuesUpdate.add(i);
        }

        Collections.shuffle(valuesUpdate);

        ArrayList<LeftistElement<Integer>> elements = new ArrayList<>();
        LeftistHeap<Integer> testHeap = new LeftistHeap<>();

        for (Integer i: valuesUpdate){
            elements.add((LeftistElement<Integer>) testHeap.insert(i));
        }

        int randomNum = (int) (0 + (Math.random() * AMOUNT_ELEMENTS));

        Collections.shuffle(elements);

        for (int i = 0; i < randomNum; i++){
            elements.get(i).remove();
        }

        LeftistElement<Integer> root = (LeftistElement<Integer>) testHeap.findMin();

        assertEquals("Geen LeftistHeap na removals elementen.", true, isLeftistHeap(root));

    }





    public boolean isLeftistHeap(LeftistElement<Integer> root ) throws EmptyHeapException {
        ArrayList<LeftistElement<Integer>> elements = new ArrayList<>();
        addElementsIntoArray(elements, root);

        for (LeftistElement<Integer> parent: elements) {

            if (parent.hasLeftChild() && parent.hasRightChild()) {
                LeftistElement<Integer> right = parent.getRightChild();
                LeftistElement<Integer> left = parent.getRightChild();
                if (left.getNpl() < right.getNpl()) {
                    return false;
                }
            }

            if (parent.hasLeftChild()) {
                if (parent.getLeftChild().value().compareTo(parent.value()) < 0) {
                    return false;
                }

            }
            if (parent.hasRightChild()) {
                if (parent.getRightChild().value().compareTo(parent.value()) < 0) {
                    return false;
                }
            }

            if (parent.getNpl() != getNpl(parent,0)){
                return false;
            }

        }
        return true;
    }

    public boolean isLeftistHeap(ArrayList<LeftistElement<Integer>> elements) throws EmptyHeapException {

        for (LeftistElement<Integer> parent: elements) {

            if (parent.hasLeftChild() && parent.hasRightChild()) {
                LeftistElement<Integer> right = parent.getRightChild();
                LeftistElement<Integer> left = parent.getRightChild();
                if (left.getNpl() < right.getNpl()) {
                    return false;
                }
            }

            if (parent.hasLeftChild()) {
                if (parent.getLeftChild().value().compareTo(parent.value()) < 0) {
                    return false;
                }

            }
            if (parent.hasRightChild()) {
                if (parent.getRightChild().value().compareTo(parent.value()) < 0) {
                    return false;
                }
            }

        }
        return true;
    }

    public void addElementsIntoArray(ArrayList<LeftistElement<Integer>> list, LeftistElement<Integer> root){

        if (root == null){

        } else {
            list.add(root);
            addElementsIntoArray(list, root.getRightChild());
            addElementsIntoArray(list, root.getLeftChild());
        }

    }

    public int getNpl(LeftistElement<Integer> node, int npl){
        int npl_1 = npl;
        int npl_2 = npl;

        if (node.hasRightChild()&&node.hasLeftChild()){
            if (node.hasRightChild()){
                npl_1 = getNpl(node.getRightChild(), npl+1);
            }
            if (node.hasLeftChild()){
                npl_2 = getNpl(node.getLeftChild(), npl+1);
            }
        }

        return Math.min(npl_1, npl_2);


    }


}
