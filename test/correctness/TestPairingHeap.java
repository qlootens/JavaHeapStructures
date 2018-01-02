package test.correctness;

import heap.EmptyHeapException;
import heap.pairing.PairingElement;
import heap.pairing.PairingHeap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import static org.junit.Assert.assertEquals;


public class TestPairingHeap {

    private int AMOUNT_ELEMENTS = 10000;

    @Test
    public void testPairingHeaps() throws EmptyHeapException {
        ArrayList<Integer> values = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            values.add(i);
        }

        Collections.shuffle(values);

        ArrayList<PairingElement<Integer>> elements = new ArrayList<>();
        PairingHeap<Integer> testHeap = new PairingHeap<>();

        for (Integer i: values){
            elements.add((PairingElement<Integer>) testHeap.insert(i));
        }

        assertEquals("Geen PairingHeap.", true, isPairingHeap(elements,testHeap));
    }

    @Test
    public void testPairingHeapsMin() throws EmptyHeapException {
        ArrayList<Integer> valuesUpdate = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            valuesUpdate.add(i);
        }

        Collections.shuffle(valuesUpdate);

        ArrayList<PairingElement<Integer>> elements = new ArrayList<>();
        PairingHeap<Integer> testHeap = new PairingHeap<>();

        for (Integer i: valuesUpdate){
            elements.add((PairingElement<Integer>) testHeap.insert(i));
        }

        PairingElement<Integer> mininumElement = elements.get(0);

        for (PairingElement<Integer> element: elements){
            if (mininumElement.value() > element.value()){
                mininumElement = element;
            }
        }

        assertEquals("Kleinste element is incorrect!", true, (mininumElement == testHeap.findMin()));

    }

    @Test
    public void testPairingHeapsUpdates() throws EmptyHeapException {

        ArrayList<Integer> values = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            values.add(i);
        }

        Collections.shuffle(values);

        ArrayList<PairingElement<Integer>> elements = new ArrayList<>();
        PairingHeap<Integer> testHeap = new PairingHeap<>();

        for (Integer i: values){
            elements.add((PairingElement<Integer>) testHeap.insert(i));
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
    public void testPairingHeapRemovals() throws EmptyHeapException {
        ArrayList<Integer> valuesUpdate = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            valuesUpdate.add(i);
        }

        Collections.shuffle(valuesUpdate);

        ArrayList<PairingElement<Integer>> elements = new ArrayList<>();
        PairingHeap<Integer> testHeap = new PairingHeap<>();

        for (Integer i: valuesUpdate){
            elements.add((PairingElement<Integer>) testHeap.insert(i));
        }

        int randomNum = (int) (0 + (Math.random() * AMOUNT_ELEMENTS));

        Collections.shuffle(elements);

        for (int i = 0; i < randomNum; i++){
            elements.get(i).remove();
        }

        PairingElement<Integer> root = (PairingElement<Integer>) testHeap.findMin();

        assertEquals("Geen PairingHeap na removals elementen.", true, isPairingHeap(elements,testHeap));

    }


    public boolean isPairingHeap(ArrayList<PairingElement<Integer>> elements, PairingHeap<Integer> t) throws EmptyHeapException {

        for (PairingElement<Integer> parent: elements) {

            if (parent != t.root){
                if (parent.child != null){
                    if (parent.value().compareTo(parent.child.value())>0){
                        //System.out.println(t.getRoot().value());
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
