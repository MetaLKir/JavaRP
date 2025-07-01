package telran.arrays.tests;

import org.junit.jupiter.api.*;
import telran.arrays.tools.ArrayTools;

public class ArraysTest {
    Integer[] arrNum;
    String[] arrStr;

    @BeforeEach
    void setUp(){
        arrNum = new Integer[] {9, 7, 4, 7, 2, 5, 9, 1, 0};
        arrStr = new String[] {"one", "two", "three", "four", "five"};
    }

    @Test
    void testPrintArray(){
        System.out.println("Test printArray");
        ArrayTools.printArray(arrNum);
        ArrayTools.printArray(arrStr);
    }

    @Test
    void testFindFirst(){
        System.out.println("Test findFirst");
        Integer res = ArrayTools.findFirst(arrNum, n -> n % 2 == 0);
        System.out.println(res);

        String str = ArrayTools.findFirst(arrStr, s -> s.length() == 4);
        System.out.println(str);
    }

    @Test
    void testBubbleSortInteger(){
        System.out.println("Test bubbleSortInteger");
        ArrayTools.bubbleSort(arrNum);
        ArrayTools.printArray(arrNum);
    }

    @Test
    void testBubbleSortString(){
        System.out.println("Test bubbleSortString");
        ArrayTools.bubbleSort(arrStr);
        ArrayTools.printArray(arrStr);
    }

    @Test
    void testBubbleSortStringComparator(){
        System.out.println("Test bubbleSortStringComparator");
        ArrayTools.bubbleSort(arrStr, (s1, s2) -> s1.length() - s2.length());
        ArrayTools.printArray(arrStr);
    }
}
