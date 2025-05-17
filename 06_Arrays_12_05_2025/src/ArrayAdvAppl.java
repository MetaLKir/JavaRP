public class ArrayAdvAppl {
    public static void main(String[] args) {
        int[] arr = new int[10];
        printArray(arr);
        fillArray(arr, 10, 100);
        printArray(arr);
        int res = sumArray(arr);
        System.out.println("Sum = " + res);
        double avg = average(arr);
        System.out.println("Avg = " + avg);
        res = max(arr);
        System.out.println("Max " + res);
        res = search(arr, arr[7]);
        System.out.println("Index = " + res);
        reverseArray(arr);
        printArray(arr);
    }

    private static void reverseArray(int[] arr) {
        for (int i = 0, j = arr.length - 1, temp; i < j; i++, j--) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    private static int search(int[] arr, int num) {
        int index = -1;     // if no value, return -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num){
                index = i;
                break;
            }
        }
        return index;
    }

    private static int max(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++){
            if (max < arr[i])
                max = arr[i];
        }
        return max;
    }

    private static double average(int[] arr) {
        return (double) sumArray(arr) / arr.length;
    }

    private static int sumArray(int[] arr) {
        int sum = 0;
        for (int i: arr) {
            sum += i;
        }
        return sum;
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
