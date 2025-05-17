public class ArraySortAppl {
    public static void main(String[] args) {
        int[] arr = new int[10];
        fillArray(arr, 10, 100);
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);
        int index = binarySearch(arr, 34);
        System.out.println("Index = " + index);

    }

    public static void selectSort(int[] arr){
        // ищем минимальное, ставим на первое место,
        // потом ищем следующее минимальное и ставим его на следующее место
    }
    // **** написать сортировки самостоятельно, сколько найду
    // вставка, merge, quick, heap

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

    private static void bubbleSort1(int[] arr) {
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
