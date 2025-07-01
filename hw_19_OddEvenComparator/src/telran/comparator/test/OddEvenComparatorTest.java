package telran.comparator.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.comparator.model.OddEvenComparator;

import java.util.Arrays;

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
        Arrays.sort(origin, new OddEvenComparator());
        assertArrayEquals(expected, origin);
    }
}
