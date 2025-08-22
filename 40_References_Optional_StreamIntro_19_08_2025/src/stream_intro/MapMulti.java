package stream_intro;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MapMulti {
    public static void main(String[] args) {
        List<String> s1 = List.of("a,b", "c", "d,e,f");

        List<String> out1 = s1.stream().
                flatMap(s -> Arrays.stream(s.split(","))).
                toList();
        System.out.println(out1);


        List<String> out2 = s1.stream().
                        <String>mapMulti((s, sink) -> {
                    for (String part : s.split(",")) {
                        sink.accept(part);
                    }
                }).
                toList();
        System.out.println(out2);

        // ======== ClassWork ======== //
        System.out.println("=".repeat(50));
        Stream.of("a1", "b23", "c", "009").
                        <Character>mapMulti((s, sink) -> {
                    for (Character c : s.toCharArray()) {
                        if (Character.isDigit(c))
                            sink.accept(c);
                    }
                }).
                forEach(System.out::println);

        System.out.println("=".repeat(50));
        Stream.of("a1", "b23", "c", "009").
                flatMap(s -> Arrays.stream(s.split(""))).
                filter(c -> Character.isDigit(c.charAt(0))).
                forEach(System.out::println);

//        IntStream digits = Stream.of("a1", "b23", "c", "009").
//                mapToInt.filter(C)
    }
}
