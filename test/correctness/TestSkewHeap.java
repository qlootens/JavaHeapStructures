package test.correctness;

import heap.EmptyHeapException;
import heap.skew.SkewElement;
import heap.skew.SkewHeap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class TestSkewHeap {
    private int AMOUNT_ELEMENTS = 10000;

    @Test
    public void testSkewHeaps() throws EmptyHeapException {
        ArrayList<Integer> values = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            values.add(i);
        }

        Collections.shuffle(values);

        ArrayList<SkewElement<Integer>> elements = new ArrayList<>();
        SkewHeap<Integer> testHeap = new SkewHeap<>();

        for (Integer i: values){
            elements.add((SkewElement<Integer>) testHeap.insert(i));
        }

        assertEquals("Geen PairingHeap.", true, isPairingHeap(elements,testHeap));
    }

    @Test
    public void testSkewHeapsMin() throws EmptyHeapException {
        ArrayList<Integer> valuesUpdate = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            valuesUpdate.add(i);
        }

        Collections.shuffle(valuesUpdate);

        ArrayList<SkewElement<Integer>> elements = new ArrayList<>();
        SkewHeap<Integer> testHeap = new SkewHeap<>();

        for (Integer i: valuesUpdate){
            elements.add((SkewElement<Integer>) testHeap.insert(i));
        }

        SkewElement<Integer> mininumElement = elements.get(0);

        for (SkewElement<Integer> element: elements){
            if (mininumElement.value() > element.value()){
                mininumElement = element;
            }
        }

        assertEquals("Kleinste element is incorrect!", true, (mininumElement == testHeap.findMin()));

    }

    @Test
    public void testSkewHeapsUpdates() throws EmptyHeapException {

        ArrayList<Integer> values = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            values.add(i);
        }

        Collections.shuffle(values);

        ArrayList<SkewElement<Integer>> elements = new ArrayList<>();
        SkewHeap<Integer> testHeap = new SkewHeap<>();

        for (Integer i: values){
            elements.add((SkewElement<Integer>) testHeap.insert(i));
        }

        int minimum = 0;
        int maximum = 3 * AMOUNT_ELEMENTS;
        int randomNum;

        randomNum = minimum + (int)(Math.random() * maximum);

        for (int j = 0; j <= AMOUNT_ELEMENTS-1; j++){
            elements.get(j).update(randomNum);
            randomNum = minimum + (int)(Math.random() * maximum);
        }

        assertEquals("Geen PairingHeap na updates elementen.", true, isPairingHeap(elements,testHeap));

    }

    @Test
    public void testSkewHeapRemovals() throws EmptyHeapException {
        ArrayList<Integer> valuesUpdate = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            valuesUpdate.add(i);
        }

        Collections.shuffle(valuesUpdate);

        ArrayList<SkewElement<Integer>> elements = new ArrayList<>();
        SkewHeap<Integer> testHeap = new SkewHeap<>();

        for (Integer i: valuesUpdate){
            elements.add((SkewElement<Integer>) testHeap.insert(i));
        }

        int randomNum = (int) (0 + (Math.random() * AMOUNT_ELEMENTS));

        Collections.shuffle(elements);

        for (int i = 0; i < randomNum; i++){
            elements.get(i).remove();
        }

        SkewElement<Integer> root = (SkewElement<Integer>) testHeap.findMin();

        assertEquals("Geen PairingHeap na removals elementen.", true, isPairingHeap(elements,testHeap));

    }


    public boolean isPairingHeap(ArrayList<SkewElement<Integer>> elements, SkewHeap<Integer> t) throws EmptyHeapException {

        for (SkewElement<Integer> parent: elements) {

            if (parent != t.getRoot()){
                if (parent.rightChild != null){
                    if (parent.value().compareTo(parent.rightChild.value())>0){
                        System.out.println(parent.value());
                        System.out.println(parent.rightChild.value());

                        return false;
                    }
                }
                if (parent.leftChild != null){
                    if (parent.value().compareTo(parent.leftChild.value())>0){
                        //System.out.println(t.getRoot().value());
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
