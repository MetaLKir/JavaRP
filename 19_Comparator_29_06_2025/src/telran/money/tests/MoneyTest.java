package telran.money.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.money.model.Money;

import java.util.Arrays;
import java.util.Random;

import static telran.arrays.tools.ArrayTools.bubbleSort;
import static telran.arrays.tools.ArrayTools.printArray;

public class MoneyTest {
    Money[] arr;
    Random random = new Random();

    @BeforeEach
    void setUp(){
        arr = new Money[10];
        for (int i = 0; i < arr.length; i++) {
            double length = random.nextDouble(129, 151);
            double width = random.nextDouble(70, 73);
            int value = random.nextInt(20, 101);
            arr[i] = new Money(width, length, value);
        }
    }

    @Test
    void testComparableMoney(){
        System.out.println("Test Comparable_Money");
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);
    }

    @Test
    void testComparatorLengthMoney(){
        System.out.println("Test Comparator_Length_Money");
        printArray(arr);
        Arrays.sort(arr, (m1, m2) -> (int) (m1.getLength() - m2.getLength()));
        printArray(arr);
    }

    @Test
    void testComparatorAreaMoney(){
        System.out.println("Test Comparator_Area_Money");
        printArray(arr);
        Arrays.sort(arr, (m1, m2) -> (int) (m1.getArea() - m2.getArea()));
        printArray(arr);
    }
}
