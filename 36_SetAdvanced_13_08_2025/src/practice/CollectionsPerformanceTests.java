package practice;

import java.util.*;

public class CollectionsPerformanceTests {
    static final int N = 10_000;
    static final int CONTAINS = 10_000;
    static final int REMOVE = 1_000;

    // ArrayLists when need to add to end. It's better if not many insertion to the head

    // TreeSet если нужен уже отсортированный вариант; не может содержать null

    public static void main(String[] args) {
        int[] values = fillArray(N);
        int[] containsArr = fillArrayForContains(values, CONTAINS);
        int[] removeArr = fillArrRemoveExists(values, REMOVE);
        int[] removeArrNotExist = fillArrRemoveNotExists(values, REMOVE);
        add_ArrayList(values);
        add_LinkedList(values);
        add_HashSet(values);
        add_LinkedHashSet(values);
        add_TreeSet(values);
        //===============================
        System.out.println("=".repeat(50));
        contains_ArrayList(values, containsArr);
        contains_LinkedList(values, containsArr);
        contains_HashSet(values, containsArr);
        contains_LinkedHashSet(values, containsArr);
        contains_TreeSet(values, containsArr);
        //===============================
        System.out.println("=".repeat(50));
        remove_ArrayList(values, removeArr);
        remove_LinkedList(values, removeArr);
        remove_HashSet(values, removeArr);
        remove_LinkedHashSet(values, removeArr);
        remove_TreeSet(values, removeArr);
        //========= Remove not exist ==========
        System.out.println("============== Remove not exist ==================");
        remove_ArrayList(values, removeArrNotExist);
        remove_LinkedList(values, removeArrNotExist);
        remove_HashSet(values, removeArrNotExist);
        remove_LinkedHashSet(values, removeArrNotExist);
        remove_TreeSet(values, removeArrNotExist);
        System.out.println("============== Iterate for each ==================");
        iterate_ArrayList(values);
        iterate_LinkedList(values);
        iterate_HashSet(values);
        iterate_LinkedHashSet(values);
        iterate_TreeSet(values);

    }

    private static void iterate_TreeSet(int[] values) {
        Set<Integer> list = new TreeSet<>();
        for (int i : values) {
            list.add(i);
        }
        long sum = 0;
        long start = System.nanoTime();
        for(int i : list){
            sum += i;
        }
        long end = System.nanoTime();
        System.out.println("Iteration TreeSet time: " + (end - start) / 100 + " mc" + ", sum: " + sum);
    }

    private static void iterate_LinkedHashSet(int[] values) {
        Set<Integer> list = new LinkedHashSet<>(N);
        for (int i : values) {
            list.add(i);
        }
        long sum = 0;
        long start = System.nanoTime();
        for(int i : list){
            sum += i;
        }
        long end = System.nanoTime();
        System.out.println("Iteration LinkedHashSet time: " + (end - start) / 100 + " mc" + ", sum: " + sum);
    }

    private static void iterate_HashSet(int[] values) {
        Set<Integer> list = new HashSet<>(N);
        for (int i : values) {
            list.add(i);
        }
        long sum = 0;
        long start = System.nanoTime();
        for(int i : list){
            sum += i;
        }
        long end = System.nanoTime();
        System.out.println("Iteration HashSet time: " + (end - start) / 100 + " mc" + ", sum: " + sum);
    }

    private static void iterate_LinkedList(int[] values) {
        List<Integer> list = new LinkedList<>();
        for (int i : values) {
            list.add(i);
        }
        long sum = 0;
        long start = System.nanoTime();
        for(int i : list){
            sum += i;
        }
        long end = System.nanoTime();
        System.out.println("Iteration LinkedList time: " + (end - start) / 100 + " mc" + ", sum: " + sum);
    }

    private static void iterate_ArrayList(int[] values) {
        List<Integer> list = new ArrayList<>(N);
        for (int i : values) {
            list.add(i);
        }
        long sum = 0;
        long start = System.nanoTime();
        for(int i : list){
            sum += i;
        }
        long end = System.nanoTime();
        System.out.println("Iteration ArrayList time: " + (end - start) / 100 + " mc" + ", sum: " + sum);
    }

    private static void remove_TreeSet(int[] values, int[] removeArr) {
        Set<Integer> set = new TreeSet<>();
        for (int i : values) {
            set.add(i);
        }
        long start = System.nanoTime();
        int count = 0;
        for (int i : removeArr) {
            if (set.remove( i)) count++;
        }
        long end = System.nanoTime();
        System.out.println("Remove TreeSet time: " + (end - start) / 100 + " mc" + ", removed: " + count);
    }

    private static void remove_LinkedHashSet(int[] values, int[] removeArr) {
        Set<Integer> set = new LinkedHashSet<>(N);
        for (int i : values) {
            set.add(i);
        }
        long start = System.nanoTime();
        int count = 0;
        for (int i : removeArr) {
            if (set.remove( i)) count++;
        }
        long end = System.nanoTime();
        System.out.println("Remove LInkedHashSet time: " + (end - start) / 100 + " mc" + ", removed: " + count);
    }

    private static void remove_HashSet(int[] values, int[] removeArr) {
        Set<Integer> set = new HashSet<>(N);
        for (int i : values) {
            set.add(i);
        }
        long start = System.nanoTime();
        int count = 0;
        for (int i : removeArr) {
            if (set.remove(i)) count++;
        }
        long end = System.nanoTime();
        System.out.println("Remove HashSet time: " + (end - start) / 100 + " mc" + ", removed: " + count);
    }

    private static void remove_LinkedList(int[] values, int[] removeArr) {
        List<Integer> list = new LinkedList<>();
        for (int i : values) {
            list.add(i);
        }
        long start = System.nanoTime();
        int count = 0;
        for (int i : removeArr) {
            if (list.remove((Integer) i)) count++;
        }
        long end = System.nanoTime();
        System.out.println("Remove LinkedList time: " + (end - start) / 100 + " mc" + ", removed: " + count);
    }

    private static void remove_ArrayList(int[] values, int[] removeArr) {
        List<Integer> list = new ArrayList<>(N);
        for (int i : values) {
            list.add(i);
        }
        long start = System.nanoTime();
        int count = 0;
        for (int i : removeArr) {
            if (list.remove((Integer) i)) count++;
        }
        long end = System.nanoTime();
        System.out.println("Remove ArrayList time: " + (end - start) / 100 + " mc" + ", removed: " + count);
    }

    private static int[] fillArrRemoveExists(int[] values, int remove) {
        Random r = new Random();
        int[] arr = new int[remove];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = values[r.nextInt(0, values.length)];
        }
        return arr;
    }

    private static int[] fillArrRemoveNotExists(int[] values, int remove) {
        Random r = new Random();
        int[] arr = new int[remove];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(100_000, 200_000);
        }
        return arr;
    }

    private static void contains_TreeSet(int[] values, int[] containsArr) {
        Set<Integer> set = new TreeSet<>();
        for (int i : values) {
            set.add(i);
        }
        long start = System.nanoTime();
        int count = 0;
        for (int i : containsArr) {
            if (set.contains(i)) count++;
        }
        long end = System.nanoTime();
        System.out.println("Contains TreeSet time: " + (end - start) / 100 + " mc" + ", found: " + count);
    }

    private static void contains_LinkedHashSet(int[] values, int[] containsArr) {
        Set<Integer> set = new LinkedHashSet<>(N);
        for (int i : values) {
            set.add(i);
        }
        long start = System.nanoTime();
        int count = 0;
        for (int i : containsArr) {
            if (set.contains(i)) count++;
        }
        long end = System.nanoTime();
        System.out.println("Contains LinkedHashSet time: " + (end - start) / 100 + " mc" + ", found: " + count);
    }

    private static void contains_HashSet(int[] values, int[] containsArr) {
        Set<Integer> set = new HashSet<>(N);
        for (int i : values) {
            set.add(i);
        }
        long start = System.nanoTime();
        int count = 0;
        for (int i : containsArr) {
            if (set.contains(i)) count++;
        }
        long end = System.nanoTime();
        System.out.println("Contains HashSet time: " + (end - start) / 100 + "mc" + ", found: " + count);
    }

    private static void contains_LinkedList(int[] values, int[] containsArr) {
        List<Integer> list = new LinkedList<>();
        for (int i : values) {
            list.add(i);
        }
        long start = System.nanoTime();
        int count = 0;
        for (int i : containsArr) {
            if (list.contains(i)) count++;
        }
        long end = System.nanoTime();
        System.out.println("Contains LinkedList time: " + (end - start) / 100 + " mc" + ", found: " + count);
    }

    private static void contains_ArrayList(int[] values, int[] containsArr) {
        List<Integer> list = new ArrayList<>(N);
        for (int i : values) {
            list.add(i);
        }
        long start = System.nanoTime();
        int count = 0;
        for (int i : containsArr) {
            if (list.contains(i)) count++;
        }
        long end = System.nanoTime();
        System.out.println("Contains ArrayList time: " + (end - start) / 100 + " mc" + ", found: " + count);

    }

    private static int[] fillArrayForContains(int[] values, int contains) {
        Random r = new Random();
        int[] arr = new int[contains];
        int half = contains / 2;
        for (int i = 0; i < half; i++) {
            arr[i] = values[r.nextInt(0, values.length)];
        }
        for (int i = half; i < arr.length; i++) {
            arr[i] = r.nextInt(100_000, 200_000);
        }
        return arr;
    }


    private static void add_ArrayList(int[] values) {
        List<Integer> list = new ArrayList<>(N);
        long start = System.nanoTime();
        for (int i : values) {
            list.add(i);
        }
        long end = System.nanoTime();
        System.out.println("Fill ArrayList time: " + (end - start) / 100 + " mc");
    }

    private static void add_LinkedList(int[] values) {
        List<Integer> list = new LinkedList<>();
        long start = System.nanoTime();
        for (int i : values) {
            list.add(i);
        }
        long end = System.nanoTime();
        System.out.println("Fill LinkedList time: " + (end - start) / 100 + " mc");
    }


    private static void add_HashSet(int[] values) {
        Set<Integer> set = new HashSet<>(N);
        long start = System.nanoTime();
        for (int i : values) {
            set.add(i);
        }
        long end = System.nanoTime();
        System.out.println("Fill HashSet time: " + (end - start) / 100 + " mc");
    }

    private static void add_LinkedHashSet(int[] values) {
        Set<Integer> set = new LinkedHashSet<>(N);
        long start = System.nanoTime();
        for (int i : values) {
            set.add(i);
        }
        long end = System.nanoTime();
        System.out.println("Fill LinkedHashSet time: " + (end - start) / 100 + " mc");
    }

    private static void add_TreeSet(int[] values) {
        Set<Integer> set = new TreeSet<>();
        long start = System.nanoTime();
        for (int i : values) {
            set.add(i);
        }
        long end = System.nanoTime();
        System.out.println("Fill TreeSet time: " + (end - start) / 100 + " mc");
    }

    private static int[] fillArray(int n) {
        int[] arr = new int[n];
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(0, 100_000);
        }
        return arr;
    }
}
