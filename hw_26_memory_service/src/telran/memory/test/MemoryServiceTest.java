package telran.memory.test;

import org.junit.jupiter.api.Test;
import telran.memory.utils.MemoryService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MemoryServiceTest {
    private int[] arr;

    @Test
    void testGetMaxAvailableMemory() {
        int maxMemory = MemoryService.getMaxAvailableMemory();
        boolean flag;

        try {
            arr = new int[maxMemory];
            flag = true;
        } catch (Throwable e) {
            flag = false;
        }
        arr = null;
        assertTrue(flag);

        try {
            arr = new int[maxMemory + 1];
            flag = true;
        } catch (Throwable e) {
            flag = false;
        }
        assertFalse(flag);
    }
}
