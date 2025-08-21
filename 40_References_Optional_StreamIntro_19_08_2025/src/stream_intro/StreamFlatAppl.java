package stream_intro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamFlatAppl {

    public static void main(String[] args) {
//        String[] str = Stream.of("Pratchett's first novel, " +
//                "The Carpet People, was published in 1971. " +
//                "The first Discworld novel, The Colour of Magic, " +
//                "was published in 1983, after which Pratchett wrote " +
//                "an average of two books a year. The final Discworld " +
//                "novel, The Shepherd's Crown, was published in " +
//                "August 2015, five months after his death.").
//                flatMap(s-> Arrays.asList(s.split("")).stream()).
//                toArray(String[]::new);
//
//        for(String s : str){
//            System.out.print(s + " ");
//        }

        List<Company> list = new ArrayList<>(Arrays.asList(
                new Company("Company1", new ArrayList<>(Arrays.asList("Name1", "Name2", "Name3"))),
                new Company("Company2", new ArrayList<>(Arrays.asList("Name2", "Name3", "Name4"))),
                new Company("Company3", new ArrayList<>(Arrays.asList("Name4", "Name5", "Name6")))
        ));
        // if I want to see unique employees, this won't work
        list.stream().
                distinct().
                forEach(System.out::println);
        System.out.println("=".repeat(50));
        // need this
        list.stream().
                flatMap(c -> c.employee.stream()).
                distinct().
                forEach(System.out::println);

        System.out.println("=".repeat(50));
        list.stream().
                peek((c -> System.out.println("company -> " + c.getName()))).
                flatMap(c -> c.employee.stream()).
                peek(name -> System.out.println("after flatMap -> " + name)).
                distinct().
                peek(name -> System.out.println("after distinct -> " + name)).
                forEach(System.out::println);

        // mapMulty()
        System.out.println("=".repeat(50));
        Stream.of("sdafadfs", "jkoijoigf", "fgaewg").
                <Character>mapMulti((str, sink)->{
                    for(char c : str.toCharArray())
                        sink.accept(c);
        }).
                forEach(System.out::print);
    }
}
