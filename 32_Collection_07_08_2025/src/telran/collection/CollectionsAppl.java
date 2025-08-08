package telran.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CollectionsAppl {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("HTML", "CSS", "Javascript", "Typescript", "React", "Redux", "Bootstrap",
                "Material UI", "HTML", "CSS", "HTML");
        String[] str = {"HTML", "CSS", "JavaScript", "TypeScript", "React", "Redux", "Bootstrap", "Material UI"};

        List<String> list2 = List.of(str);
        List<String> list3 = Arrays.asList(str);

        System.out.println("=====Sort1=====");
        Collections.sort(list1);
        System.out.println(list1);

        System.out.println(Arrays.toString(str));

//        Collections.sort(list1, (s1,s2) ->Integer.compare(s1.length(), s2.length()));
//        System.out.println(list1);
//        System.out.println(Collections.binarySearch(list1, "Bootstrap"));

        System.out.println(Collections.binarySearch(list1, "CS"));

//        System.out.println("=====Shuffle=====");
//        Collections.shuffle(list1);
//        System.out.println(list1);
//        Random r = new Random(123);
//        Collections.shuffle(list3, r);
//        System.out.println(list3);

        System.out.println("=====Sort3=====");
        System.out.println(list3);
        Collections.sort(list3);
        System.out.println(list3);

//        System.out.println("=====Fill=====");
//        Collections.fill(list1, "Java");
//        System.out.println(list1);
//        //Collections.fill(list2, "Java");
//        Collections.fill(list3, "Java");
//        System.out.println(list3);

        System.out.println("=====Min-Max=====");
        String min = Collections.min(list1);
        String max = Collections.max(list1);
        System.out.println("min: " + min + "| max: " + max);
        min = Collections.min(list1, (s1,s2) ->Integer.compare(s1.length(), s2.length()));
        max = Collections.max(list1, (s1,s2) ->Integer.compare(s1.length(), s2.length()));
        System.out.println("min: " + min + "| max: " + max);

        System.out.println("=====Frequency=====");
        int count = Collections.frequency(list1, "HTML");
        System.out.println(count);

        System.out.println("=====Replace=====");
        System.out.println(list1);
        Collections.replaceAll(list1, "HTML", "HTML5");
        System.out.println(list1);

        System.out.println("=====Rotate====="); // move elements to the right. elements which go out of bounds will be placed in the beginning
        System.out.println(list1);
        Collections.rotate(list1, 3);
        System.out.println(list1);

        System.out.println("=====Reverse=====");
        System.out.println(list1);
        Collections.reverse(list1);
        System.out.println(list1);
        System.out.println("=====Disjoint====="); // check is there no the same elements in 2 collections
        List<String> listNew = Arrays.asList("C", "C++");
        System.out.println(Collections.disjoint(list1, listNew));
        listNew = Arrays.asList("CSS", "C++");
        System.out.println(Collections.disjoint(list1, listNew));
        System.out.println("=====emptyList=====");
        Collections.emptyList(); // immutable
    }
}
