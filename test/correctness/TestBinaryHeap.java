package test.correctness;

import heap.Element;
import heap.EmptyHeapException;
import heap.binary.BinaryElement;
import heap.binary.BinaryHeap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import static org.junit.Assert.assertEquals;


public class TestBinaryHeap {

    private int AMOUNT_ELEMENTS = 10000;

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    @Test
    public void testBinaryHeap(){
        ArrayList<Integer> values = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            values.add(i);
        }

        Collections.shuffle(values);

        BinaryHeap<Integer> testHeap = new BinaryHeap<>();
        for (Integer i: values){
            testHeap.insert(i);
        }

        assertEquals("Geen Binaire Hoop.", true, checkIfArrayIsBinaryHeap(testHeap.getBinaryHeapArray()));
    }

    @Test
    public void testBinaryHeapMinimum() throws EmptyHeapException {
        ArrayList<Integer> valuesUpdate = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            valuesUpdate.add(i);
        }

        Collections.shuffle(valuesUpdate);

        ArrayList<BinaryElement<Integer>> elements = new ArrayList<>();
        BinaryHeap<Integer> testHeap = new BinaryHeap<>();

        for (Integer i: valuesUpdate){
            elements.add((BinaryElement<Integer>) testHeap.insert(i));
        }

        BinaryElement<Integer> mininumElement = elements.get(0);

        for (BinaryElement<Integer> element: elements){
            if (mininumElement.value() > element.value()){
                mininumElement = element;
            }
        }

        assertEquals("Kleinste element is incorrect!", true, (mininumElement == testHeap.findMin()));

    }

    @Test
    public void testBinaryHeapUpdates(){
        ArrayList<Integer> values = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            values.add(i);
        }

        Collections.shuffle(values);

        ArrayList<Element<Integer>> elements = new ArrayList<>();
        BinaryHeap<Integer> testHeap = new BinaryHeap<>();

        for (Integer i: values){
            elements.add(testHeap.insert(i));
        }

        int minimum = 0;
        int maximum = 3 * AMOUNT_ELEMENTS;
        int randomNum;

        randomNum = minimum + (int)(Math.random() * maximum);

        for (int j = 0; j <= AMOUNT_ELEMENTS-1; j++){
            elements.get(j).update(randomNum);
            randomNum = minimum + (int)(Math.random() * maximum);
        }

        assertEquals("Geen Binaire Hoop.", true, checkIfArrayIsBinaryHeap(testHeap.getBinaryHeapArray()));

    }


    @Test
    public void testBinaryHeapRemovals() throws EmptyHeapException {
        ArrayList<Integer> valuesUpdate = new ArrayList<>();

        for (int i = AMOUNT_ELEMENTS; i >= 1; i--){
            valuesUpdate.add(i);
        }

        Collections.shuffle(valuesUpdate);

        ArrayList<BinaryElement<Integer>> elements = new ArrayList<>();
        BinaryHeap<Integer> testHeap = new BinaryHeap<>();

        for (Integer i: valuesUpdate){
            elements.add((BinaryElement<Integer>) testHeap.insert(i));
        }

        int randomNum = (int) (0 + (Math.random() * AMOUNT_ELEMENTS));

        Collections.shuffle(elements);

        for (int i = 0; i < randomNum; i++){

            elements.get(i).remove();

        }

        assertEquals("Geen binaire hoop na removals elementen.", true, checkIfArrayIsBinaryHeap(testHeap.getBinaryHeapArray()));

    }


    private boolean checkIfArrayIsBinaryHeap(ArrayList<Integer> arrayList){

        for (int i = 0; i < arrayList.size(); i++){
            int lChild = getLeftChildIndex(i);
            int rChild = getRightChildIndex(i);
            if (lChild >= arrayList.size() || rChild >= arrayList.size()){
                break;
            }
            if (lChild < arrayList.size()){
                if (arrayList.get(i) > arrayList.get(lChild)){
                    return false;
                }
            }
            if (rChild < arrayList.size()){
                if (arrayList.get(i) > arrayList.get(rChild)){
                    return false;
                }
            }
        }
        return true;

    }


}
