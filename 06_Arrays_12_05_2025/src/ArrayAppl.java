public class ArrayAppl {
    public static void main(String[] args) {
        int[] arr = new int[5];
        System.out.println(arr[2]);
        arr[2] = 100500;
        System.out.println(arr[2]);
        arr[1] = arr[2] * 2;
        System.out.println(arr[1]);
        System.out.println(arr.length);
        int[] primes = {2, 3, 5, 7, 11, 12, 15, 17};
        System.out.println(primes.length);
        primes[2] = -1;
        System.out.println(primes[2]);

        printArray(primes);
    }

    private static void printArray(int[] arr) {
        for(int i = 0; i < arr.length; i++){
            System.out.println("[" + i + "] = " + arr[i] );
        }
    }
}
