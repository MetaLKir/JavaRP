package terlan.multiarray.controller;

public class InterviewTestAppl {
    public static void main(String[] args) {
        int[][] arr = new int[10_000][10_000];
        long t1 = System.currentTimeMillis();
        fillArrayOpt(arr);
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);


//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                System.out.print(arr[i][j] + "\t");
//            }
//            System.out.println();
//        }
    }

    private static void fillArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = i * j;
            }
        }
    }

    private static void fillArrayOpt(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i][i] = i * i;
            for (int j = i + 1; j < arr[i].length; j++) {
                arr[i][j] = arr[j][i] = i * j;
            }
        }
    }
}
