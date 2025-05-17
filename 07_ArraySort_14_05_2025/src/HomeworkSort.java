public class HomeworkSort {
    // selection sort
    // * написать сортировки самостоятельно, сколько найду: вставка, merge, quick, heap
    public static void main(String[] args) {
        int[] arr = new int[10];
        fillArray(arr,10, 99);
        printArray(arr);
        selectSort(arr);
        printArray(arr);
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

    //==================================================
    private static void fillArray(int[] arr, int from, int to) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (from + Math.random() * (to - from + 1));
        }
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }
}
