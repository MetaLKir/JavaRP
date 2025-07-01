package telran.comparator.model;

import java.util.Comparator;

public class OddEvenComparator implements Comparator<Integer> {
   /*
        TODO: method .compare:
            - Evens in the beginning, ascending
            - Odds in the end, descending
    */

    @Override
    public int compare(Integer o1, Integer o2) {
        if (o2 % 2 == 0){
            return o1 - o2;
        }
        else {
            return -(o1 - o2);
        }
    }
}
