package arrays.tools;

public class ArrayTools {

    public static void fillArray(int[] arr, int from, int to) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (from + Math.random() * (to - from + 1));
        }
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    public static void selectSort(int[] arr){
        // look for the lowest element
        // swap it with the first element (don't swap if it's already the lowest)
        // repeat excluding sorted indexes
        for (int i = 0, temp, minIndex; i < arr.length - 1; i++) { // -1 because on the last iteration only one element left, nothing to swap with
            // look for the index of the min element
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex])
                    minIndex = j;
            }
            // swap elements
            if (i != minIndex){ // no need to swap if index is the same
                temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    public static void bubbleSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void bubbleSort1(int[] arr) {
        while(bubbling(arr));
    }

    private static boolean bubbling(int[] arr) {
        boolean res = false;
        for (int j = 0; j < arr.length - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                res = true;
            }
        }
        return res;
    }

    public static int binarySearch(int[] arr, int value){
        int l = 0;
        int r = arr.length - 1;
        while(l <= r){
            int mid = (l + r) / 2;
            if(arr[mid] == value)
                return mid;
            if(arr[mid] > value)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return -l -1;
    }
}
