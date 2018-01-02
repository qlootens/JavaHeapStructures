package heap.pairing;
import heap.Element;

import java.util.Comparator;

public class ComparatorQueue<T extends Comparable<T>> implements Comparator<Element<T>>{



    @Override
    public int compare(Element<T> tElement, Element<T> t1) {
        if (tElement.value().compareTo(t1.value()) < 0)
        {
            return -1;
        }
        if (tElement.value().compareTo(t1.value()) > 0)
        {
            return 1;
        }
        return 0;
    }
}
