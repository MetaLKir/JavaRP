package references;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public class RefAppl {
    public static void main(String[] args) {
        int x = 10;
        int y = 0;
        y += x; // y = y + x;
        x++; // x = x + 1;

        //=====================================
        List<Integer> arr = Arrays.asList(10, 5, 78, 2, 18, 61);
        for (Integer in : arr) {
            System.out.print(in + " ");
        }
        System.out.println();

        System.out.println("======== Functional interface ========");
        Consumer<Integer> printer1 = new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                System.out.print(t + " ");
            }
        };
        arr.forEach(printer1);
        System.out.println();

        System.out.println("======== Functional interface  + lamda block ========");
        Consumer<Integer> printer2 = v -> {
            System.out.print(v + " ");
        };
        arr.forEach(printer2);
        System.out.println();

        System.out.println("======== Functional interface  + lamda expression ========");
        Consumer<Integer> printer3 = v -> System.out.print(v + " ");
        arr.forEach(printer3);
        System.out.println();

        System.out.println("======== references ========");
        arr.forEach(System.out::print);
        System.out.println();
        arr.forEach(System.out::print);
        System.out.println();
        arr.forEach(RefAppl::printWithSpace);

        System.out.println("======== Object ========");
        Person p1 = new Person("Vasya");

        System.out.println("======== Functional interface ========");
        Runnable greet1 = new Runnable() {
            @Override
            public void run() {
                p1.greet();
            }
        };
        greet1.run();

        System.out.println("======== Functional interface + lamda block ========");
        Runnable greet2 = () -> {
            p1.greet();
        };
        greet2.run();

        System.out.println("======== Functional interface + lamda expression ========");
        Runnable greet3 = () -> p1.greet();
        greet3.run();

        System.out.println("======== references ========");
        Runnable greet4 = p1::greet;
        greet4.run();

        System.out.println("======== Object ========");
        System.out.println("======== Functional interface ========");
        Function<String, Integer> length1 = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        System.out.println("String length: " + length1.apply("Jopa"));

        System.out.println("======== Functional interface + lambda block ========");
        Function<String, Integer> length2 = st -> {
            return st.length();
        };
        System.out.println("String length: " + length2.apply("Jopa"));

        System.out.println("======== Functional interface + lambda expression ========");
        Function<String, Integer> length3 = st -> st.length();
        System.out.println("String length: " + length3.apply("Jopa"));

        System.out.println("======== list sort with reference ========");
        Function<String, Integer> length4 = String::length;
        System.out.println("String length: " + length4.apply("Jopa"));
        Comparator<String> byIgnoreCase = String::compareToIgnoreCase;
        List<String> list1 = new ArrayList<>(List.of("Fasff", "adfe", "Adsfasf", "rwet", "fasf"));
        Collections.sort(list1);
        System.out.println(list1);
        list1.sort(byIgnoreCase);
        System.out.println(list1);
        System.out.println("======== list sort with lambda expression ========");
        List<String> list2 = new ArrayList<>(List.of("Fasff", "adfe", "Adsfasf", "rwet", "fasf"));
        Comparator<String> byIgnoreCase1 = (s1, s2) -> s1.compareToIgnoreCase(s2);
        Collections.sort(list2);
        System.out.println(list2);
        list1.sort(byIgnoreCase1);
        System.out.println(list2);

        System.out.println("======== CONSTRUCTOR ========");
        System.out.println("======== Functional interface ========");
        Supplier<Person> suppl1 = new Supplier<Person>() {
            @Override
            public Person get() {
                return new Person();
            }
        };
        Person p2 = suppl1.get();
        System.out.println(p2);

        String name = "name2";
        Function<String, Person> suppl11 = new Function<String, Person>() {
            @Override
            public Person apply(String s) {
                return new Person(s);
            }
        };
        System.out.println("======== Functional interface + lamda block ========");
        Supplier<Person> suppl2 = () -> {
            return new Person();
        };
        System.out.println("======== Functional interface + lamda expression ========");
        Supplier<Person> suppl3 = () -> new Person();
        System.out.println("======== references ========");
        Supplier<Person> suppl4 = Person::new;
        Function<String, Person> suppl44 = Person::new;
        Person p4 = suppl4.get();
        p4.greet();
        Person p44 = suppl44.apply("Valentine");
        p44.greet();

        System.out.println("======== Constructor arrays ========");
        System.out.println("======== Functional interface ========");
        IntFunction<String[]> intF1 = new IntFunction<String[]>() {
            @Override
            public String[] apply(int value) {
                return new String[value];
            }
        };
        String[] str1 = intF1.apply(5);
        System.out.println(str1.length);
        System.out.println("======== Functional interface + lamba block ========");
        IntFunction<String[]> intF2 = n -> {
            return new String[n];
        };
        String[] str2 = intF2.apply(10);
        System.out.println(str2.length);
        System.out.println("======== Functional interface + lambda expression ========");
        IntFunction<String[]> intF3 = n -> new String[n];
        String[] str3 = intF3.apply(15);
        System.out.println(str3.length);
        System.out.println("======== reference ========");
        IntFunction<String[]> intF4 = String[]::new;
        String[] str4 = intF4.apply(20);
        System.out.println(str4.length);


    }


    static void printWithSpace(Object o) {
        System.out.print(o + " ");
    }
}
