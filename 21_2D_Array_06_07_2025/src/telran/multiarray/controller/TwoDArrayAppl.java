package telran.multiarray.controller;

public class TwoDArrayAppl {
    public static void main(String[] args) {
        int[][] arr = new int[4][];
        arr[0] = new int[5];
        arr[1] = new int[3];
        arr[2] = new int[2];
        arr[3] = new int[4];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }

        int[][][] arr3D = new int[3][][];
        arr3D[0] = new int[2][];
        arr3D[0][0] = new int[5];
    }
}
