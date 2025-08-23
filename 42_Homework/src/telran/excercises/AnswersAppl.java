package telran.excercises;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AnswersAppl {
    public static void main(String[] args) {
        /* ======== EXERCISE 1 ========
        1) Нормализация имён
            Цель: фильтрация, преобразования, ссылки на методы экземпляров и классов.
            Вход: List<String> names = List.of(" alice ", "BOB", null, " Eve ");
            Задание: уберите null, обрежьте пробелы, приведите к виду Capitalized (первая буква — заглавная, остальные — строчные).
            Ожидаемо: ["Alice", "Bob", "Eve"]
         */
        System.out.println("=".repeat(20) + "EXERCISE_1" + "=".repeat(20));
        List<String> names = Arrays.asList(" alice ", "BOB", null, " Eve ");
        System.out.println("In list: " + names);
        List<String> resultList = names.stream().
                filter(Objects::nonNull).
                map(String::trim).
                map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase()).
                toList();
        System.out.println("Out list: " + resultList);
        
        
        /* ======== EXERCISE 2 ========
        2) Парсинг чисел из строк и сумма
            Цель: map, фильтрация валидных чисел, ссылку на статический метод.
            Вход: List<String> xs = List.of("10"," -3 ","x","42");
            Задание: оставить только целые (возможно с -), распарсить и получить сумму.
            Ожидаемо: 49
         */
        System.out.println("=".repeat(20) + "EXERCISE_2" + "=".repeat(20));
        List<String> xs = List.of("10", " -3 ", "x", "42");
        int sum = xs.stream().
                map(String::trim).
                filter(s -> s.matches("-?[0-9]+")).
                mapToInt(Integer::valueOf).
                sum();
        System.out.println("Actual sum = " + sum);


        /* ======== EXERCISE 3 ========
            3) Сортировка строк без учёта регистра и печать
            Цель: sorted, Comparator.comparing с ссылкой на метод, печать ссылкой.
            Вход: List<String> titles = List.of("java", "Zebra", "apple");
            Задание: отсортируйте по toLowerCase(), затем выведите по строке.
            Ожидаемо:
                apple
                java
                Zebra
         */
        System.out.println("=".repeat(20) + "EXERCISE_3" + "=".repeat(20));
        List<String> titles = List.of("java", "Zebra", "apple");
        titles.stream().
                sorted(Comparator.comparing(String::toLowerCase)).
                forEach(System.out::println);


        /* ======== EXERCISE 4 ========
        4) Группировка слов по длине
            Цель: groupingBy, counting, ссылка String::length (через comparingInt или в маппере).
            Вход: "lorem ipsum dolor sit amet amet"
            Задание: получить Map<Integer, Long> длина → сколько слов такой длины.
            Ожидаемо: {5=3, 4=2, 3=1}
         */
        System.out.println("=".repeat(20) + "EXERCISE_4" + "=".repeat(20));
        String input = "lorem ipsum dolor sit amet amet";
        List<String> inputList = List.of(input.split(" "));
        Map<Integer, Long> resultMap = inputList.stream().
                collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(resultMap);


        /* ======== EXERCISE 5 ========
        5) Уникальные слова из предложений (flatMap)
            Цель: flatMap, distinct, sorted.
            Вход:
            List<String> sentences = List.of(
              "Hello world",
              "world of Streams",
              "HELLO lambda"
            );
            Задание: получить отсортированный набор уникальных слов в нижнем регистре.
            Ожидаемо: [hello, lambda, of, streams, world]
         */
        System.out.println("=".repeat(20) + "EXERCISE_5" + "=".repeat(20));
        List<String> sentences = List.of(
                "Hello world",
                "world of Streams",
                "HELLO lambda"
        );
        List<String> resultExercise4 =
                sentences.stream().flatMap(s -> Arrays.stream(s.toLowerCase().split(" "))).
                        distinct().
                        sorted().
                        toList();
        System.out.println(resultExercise4);


        /* ======== EXERCISE 6 ========
        6) Первые 10 простых чисел (IntStream)
            Цель: IntStream, фильтр по ссылке на свой метод.
            Вход: диапазон 1..1_000
            Задание: напишите static boolean isPrime(int n) и выведите первые 10 простых.
            Ожидаемо: 2 3 5 7 11 13 17 19 23 29
         */
        System.out.println("=".repeat(20) + "EXERCISE_6" + "=".repeat(20));
        IntStream.
                iterate(1, x -> ++x).
                filter(AnswersAppl::isPrime).
                limit(10).
                forEach(System.out::println);
    }

    private static boolean isPrime(int number) {
        if (number <= 1) return false;

        for (int i = 2; i < number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
