package telran.comparator.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.comparator.model.OddEvenComparator;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class OddEvenComparatorTest {
    Integer[] origin;
    Integer[] expected;

    @BeforeEach
    void setUp(){
        origin = new Integer[] {1,2,3,4,5,6,7,8,9,3};
        expected = new Integer[] {2,4,6,8,9,7,5,3,3,1};
    }

    @Test
    void testOddEvenCompareSort(){
        //origin = new Integer[] {1,4,3,2,5,6,7,8,9,3};

        Arrays.sort(origin, new OddEvenComparator());
        System.out.println("=== Test 0 ===");
        System.out.println(Arrays.toString((origin)));
        assertArrayEquals(expected, origin);
    }

    @Test
    void testOddEvenCompareSort1(){
        Comparator<Integer> comp = (o1, o2) -> {
            if(o1 % 2 == 0 && o2 % 2 == 1){
                return -1;
            }
            if (o1 % 2 == 1 && o2 % 2 == 0){
                return 1;
            }
            if(o1 % 2 == 0 && o2 % 2 == 0){
                return o1 - o2;
            }
            if (o1 % 2 == 1 && o2 % 2 == 1){
                return o2 - o1;
            }
            return 0;
        };

        Arrays.sort(origin, comp);
        System.out.println("=== Test 1 ===");
        System.out.println(Arrays.toString((origin)));
        assertArrayEquals(expected, origin);
    }
}
