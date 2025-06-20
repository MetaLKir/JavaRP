package telran.stringbuilder.controller;

public class StringPerformanceTest {

    private static final String WORD = "meow";
    private static final int MAX_ITERATION = 100_000;

    public static void main(String[] args) {
        // String
        String str = "";
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < MAX_ITERATION; i++) {
            str += WORD;
        }
        long t2 = System.currentTimeMillis();
        System.out.println("Time for String concatenation: " + (t2 - t1));

        // StringBuilder
        StringBuilder builder = new StringBuilder("");
        long t3 = System.currentTimeMillis();
        for (int i = 0; i < MAX_ITERATION; i++) {
            builder.append(WORD);
        }
        long t4 = System.currentTimeMillis();
        System.out.println("Time for StringBuilder: " + (t4 - t3));
    }
}
