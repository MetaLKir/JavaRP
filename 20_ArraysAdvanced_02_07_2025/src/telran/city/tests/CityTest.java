package telran.city.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.city.model.City;

import java.util.Arrays;
import java.util.Comparator;

public class CityTest {
    City[] usa;

    @BeforeEach
    void setUp(){
        usa = new City[8];
        usa[0] = new City("Denver", 600_000);
        usa[1] = new City("Boston", 670_000);
        usa[2] = new City("Chicago", 2_700_000);
        usa[3] = new City("Chicago", 3_100_000);
        usa[4] = new City("Chicago", 2_500_000);
        usa[5] = new City("Atlanta", 470_000);
        usa[6] = new City("New York", 8_500_000);
        usa[7] = new City("Dallas", 1_300_000);
    }

    @Test
    void testComparable(){
        System.out.println("Original array");
        System.out.println(Arrays.toString(usa));
        Arrays.sort(usa);
        System.out.println("Natural order: " + Arrays.toString(usa));
        City pattern = new City(null, 2_700_000);
        int index = Arrays.binarySearch(usa, pattern);
        System.out.println("Index = " + index);
    }

    @Test
    void textNameComparator(){
        Comparator<City> comp = (c1, c2) -> {
            int res = c1.getName().compareTo(c2.getName());
            return res == 0 ? c1.getPopulation() - c2.getPopulation() : res;
        };
        Arrays.sort(usa, comp);
        System.out.println("Comparator sort by Name: ");
        printArray(usa);

        City pattern = new City("Chicago", 0);
        int index = Arrays.binarySearch(usa, pattern, comp);
        System.out.println("Index = " + index);
    }

    @Test
    void testArrayCopy() {
        City[] copyArr = Arrays.copyOf(usa, usa.length * 2);
        Arrays.sort(copyArr, 0, usa.length);
        System.out.println("Copy array after sort: ");
        printArray(copyArr);

        City pattern = new City(null, 670_000);
        int index = Arrays.binarySearch(copyArr, 0, usa.length, pattern);
        System.out.println("Index = " + index);

        System.out.println("Original array after copy: ");
        printArray(usa);
    }

    @Test
    void testCopyOfRange(){
        City[] copyArr = Arrays.copyOfRange(usa, 2, 6);
        System.out.println("Test array copy of range: ");
        printArray(copyArr);
    }

    @Test
    void  testSystemArrayCopy(){
        System.out.println("Test system array copy: ");
        City[] copyArr = new City[usa.length * 2];
        System.arraycopy(usa, 0, copyArr, 5, usa.length);
        printArray(copyArr);
    }

    private City[] copyArray(City[] usa) {
        City[] res = new City[usa.length];
        for (int i = 0; i < usa.length; i++) {
            res[i] = usa[i];
        }
        return res;
    }

    private void printArray(Object[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
