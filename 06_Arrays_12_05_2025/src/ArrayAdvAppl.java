public class ArrayAdvAppl {
    public static void main(String[] args) {
        int[] arr = new int[10];
        printArray(arr);
        fillArray(arr, 10, 99);
        printArray(arr);
        int res = sumArray(arr);
        System.out.println("Sum = " + res);
        double avg = average(arr);
        System.out.println("Avg = " + avg);
        res = max(arr);
        System.out.println("Max " + res);
        res = search(arr, arr[7]);
        System.out.println("Index = " + res);
    }

    private static int search(int[] arr, int i) {
        // TODO
        return 0;
        // if no value, return -1;
    }

    private static int max(int[] arr) {
        // TODO
        return 0;
    }

    private static double average(int[] arr) {
        // TODO
        return 0;
    }

    private static int sumArray(int[] arr) {
        // TODO
        return 0;
    }

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
