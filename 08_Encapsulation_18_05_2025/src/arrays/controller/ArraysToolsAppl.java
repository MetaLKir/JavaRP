package arrays.controller;

import arrays.tools.ArrayTools;

public class ArraysToolsAppl {
    public static void main(String[] args) {
        int[] arr = new int[10];
        ArrayTools.printArray(arr);
        ArrayTools.fillArray(arr, 10, 99);
        ArrayTools.printArray(arr);
        ArrayTools.bubbleSort1(arr);
        ArrayTools.printArray(arr);
    }
}
