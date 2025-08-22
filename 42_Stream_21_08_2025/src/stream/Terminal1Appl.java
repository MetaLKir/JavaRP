package stream;

import java.util.Optional;
import java.util.stream.*;

public class Terminal1Appl {
    public static void main(String[] args) {

        int sum1 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).
                reduce(0, (a, b) -> a + b);
        System.out.println(sum1);

        Optional<Integer> op = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).
                reduce(Integer::sum);
        System.out.println(op.get());

        int sum2 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).
                reduce(Integer::sum).orElse(100);
        System.out.println(sum2);
    }


}
