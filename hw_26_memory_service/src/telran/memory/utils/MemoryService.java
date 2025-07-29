package telran.memory.utils;

public class MemoryService {
    public static int getMaxAvailableMemory() {
        int maxSize = 0;
        int step = 1_000_000_000;

        while (step > 0) {
            try {
                int[] arr = new int[maxSize + step];
                maxSize += step;
            } catch (OutOfMemoryError e) {
                step /= 10;
            }
        }

        System.out.println("maxSize = " + maxSize);
        return maxSize;
    }
}
