package telran.list;

import java.util.Arrays;

public class Example {
    public static void main(String[] args) {
        int a = 10;
        System.out.println(a);
        int[] arr = {1,3,5};
        System.out.println(Arrays.toString(arr));

        changeA(a);
        changeArr(arr);
        System.out.println(a);
        System.out.println(Arrays.toString(arr));

    }

    public static void changeA(int a){
        a = a + 10;
    }

    public static void changeArr(int[] arr){
        arr[0] = 1000;
    }
}
