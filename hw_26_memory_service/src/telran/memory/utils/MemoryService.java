package telran.memory.utils;

public class MemoryService {
    public static int getMaxAvailableMemory() {
        // TODO: homework
        // когда масив упадёт, сохранить предыдущее значение
        int res = 0;

        for (int i = 0; true; i++) {
            try {
                res = i;
                int[] arr = new int[i];
            } catch (RuntimeException e) {
                return res - 1;
            }
        }


        // в цикле создавать массив с размером итератора
        // поймать когда рухнет

        // Runtime.getRuntime().maxMemory();


    }
}
