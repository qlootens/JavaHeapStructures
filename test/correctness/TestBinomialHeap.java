package test.correctness;

import heap.EmptyHeapException;
import heap.binomial.BinomialElement;
import heap.binomial.BinomialHeap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class TestBinomialHeap {

    private int AMOUNT_ELEMENTS = 10000;

    @Test
    public void testBinomialHeap() throws EmptyHeapException {
        ArrayList<Integer> values = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            values.add(i);
        }

        Collections.shuffle(values);

        ArrayList<BinomialElement<Integer>> elements = new ArrayList<>();
        BinomialHeap<Integer> testHeap = new BinomialHeap<>();

        for (Integer i: values){
            elements.add((BinomialElement<Integer>) testHeap.insert(i));
        }

        assertEquals("Geen BinomialHeap.", true, isBinomialHeap(elements, testHeap));
    }

    @Test
    public void testBinomialHeapMinimum() throws EmptyHeapException {
        ArrayList<Integer> valuesUpdate = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            valuesUpdate.add(i);
        }

        Collections.shuffle(valuesUpdate);

        ArrayList<BinomialElement<Integer>> elements = new ArrayList<>();
        BinomialHeap<Integer> testHeap = new BinomialHeap<>();

        for (Integer i: valuesUpdate){
            elements.add((BinomialElement<Integer>) testHeap.insert(i));
        }

        BinomialElement<Integer> mininumElement = elements.get(0);

        for (BinomialElement<Integer> element: elements){
            if (mininumElement.value() > element.value()){
                mininumElement = element;
            }
        }

        assertEquals("Kleinste element is incorrect!", true, (mininumElement == testHeap.findMin()));

    }


    @Test
    public void testBinomialHeapUpdates() throws EmptyHeapException {

        ArrayList<Integer> values = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            values.add(i);
        }

        Collections.shuffle(values);

        ArrayList<BinomialElement<Integer>> elements = new ArrayList<>();
        BinomialHeap<Integer> testHeap = new BinomialHeap<>();

        for (Integer i: values){
            elements.add((BinomialElement<Integer>) testHeap.insert(i));
        }

        int minimum = 0;
        int maximum = 3 * AMOUNT_ELEMENTS;
        int randomNum;

        randomNum = minimum + (int)(Math.random() * maximum);

        for (int j = 0; j <= AMOUNT_ELEMENTS-5; j++){
            elements.get(j).update(randomNum);
            if (!isBinomialHeap(elements, testHeap)){
                break;
            }
            randomNum = minimum + (int)(Math.random() * maximum);
        }
        assertEquals("Geen BinomialHeap na updates elementen.", true, isBinomialHeap(elements, testHeap));
    }

    @Test
    public void testBinaryHeapRemovals() throws EmptyHeapException {
        ArrayList<Integer> valuesUpdate = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            valuesUpdate.add(i);
        }

        Collections.shuffle(valuesUpdate);

        ArrayList<BinomialElement<Integer>> elements = new ArrayList<>();
        BinomialHeap<Integer> testHeap = new BinomialHeap<>();

        for (Integer i: valuesUpdate){
            elements.add((BinomialElement<Integer>) testHeap.insert(i));
        }

        int randomNum = (int) (0 + (Math.random() * AMOUNT_ELEMENTS));

        Collections.shuffle(elements);

        for (int i = 0; i < randomNum; i++){

            elements.get(i).remove();

        }
        ArrayList<BinomialElement<Integer>> list = new ArrayList<>();
        addElementsIntoArray(list, testHeap.getRoot());

        assertEquals("Geen binaire hoop na removals elementen.", true, isBinomialHeap(list, testHeap));

    }




    public boolean isBinomialHeap(ArrayList<BinomialElement<Integer>> elements, BinomialHeap<Integer> t) throws EmptyHeapException {

        for (BinomialElement<Integer> parent: elements) {

            if (parent.getParent() != null){
                if (parent.value().compareTo(parent.getParent().value())<0){
                    //System.out.println(t.getRoot().value());

                    System.out.println(parent.value());
                    System.out.println(parent.getParent().value());
                    return false;
                }
            }
        }
        return true;
    }


    public void addElementsIntoArray(ArrayList<BinomialElement<Integer>> list, BinomialElement<Integer> root){
        if (root == null){

        } else {
            list.add(root);
            addElementsIntoArray(list, root.getChild());
            addElementsIntoArray(list, root.getNextHeap());
        }

    }

}
