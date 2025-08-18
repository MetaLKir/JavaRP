import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ReferencesAppl {

    public static void main(String[] args) {
        // references for static method
        // Class::staticMethod

        // create list for work
        List<Integer> list =
                new ArrayList<>(Arrays.asList(1, 2, 10, 5, -4, 20));

        System.out.println("Integer list: " + list);

        // anonymous method
        List<Integer> res = find(list, new Predicate<Integer>() {
            @Override
            public boolean test(Integer t) {
                return t % 2 == 0;
            }
        });
        System.out.println("Even numbers (anonymous method): " + res);

        // lambda expression
        List<Integer> res1 = find(list, n -> n % 2 == 0);
        System.out.println("Even numbers (lambda expression): " + res1);

        // lambda with body block
        List<Integer> res2 = find(list, (n) -> {
            return n % 2 == 0;
        });
        System.out.println("Even numbers (lamba body-block): " + res2);

        // method reference
        List<Integer> res3 = find(list, ReferencesAppl::isEven);
        System.out.println("Even numbers (method reference): " + res3);
        List<Integer> res4 = find(list, ReferencesAppl::isOdd);
        System.out.println("Odd numbers (method reference): " + res4);

        // sort list with method reference
        list.sort(Integer::compare);
        System.out.println("List rorted with method ref: " + list);

        System.out.println("\nPrint list with .forEach and \"println\" method ref: ");
        list.forEach(System.out::println);
        System.out.println();
    }

    private static List<Integer> find(List<Integer> list, Predicate<Integer> predicate) {
        List<Integer> res = new ArrayList<>();
        for (Integer n : list) {
            if (predicate.test(n))
                res.add(n);
        }
        return res;
    }

    public static boolean isEven(Integer x) {
        return x % 2 == 0;
    }
    public static boolean isOdd(Integer x) {
        return x % 2 != 0;
    }
}
