package telran.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListAppl {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("HTML");
        list.add("CSS");
        list.add("JavaScript");
        list.add("TypeScript");
        list.add("React");
        list.add("Redux");
        list.add("Bootstrap");
        list.add("Material UI");
        System.out.println(list);

        list.remove("JavaScript");
        System.out.println(list);

        list.add(2, "JavaScript");
        System.out.println(list);

//        list.add(9,"JavaScript");
//        System.out.println(list);

        list.add(8, "JavaScript");
        System.out.println(list);

        System.out.println("=".repeat(50));
        list.forEach(t -> System.out.print(t + " | "));
        System.out.println();
        System.out.println("=".repeat(50));

        String s1 = list.set(2, "Java");
        System.out.println(list);
        System.out.println(s1);

        // if we create list in such way, we can't add elements to it!!!
        List<String> list1 = Arrays.asList("HTML", "CSS", "JavaScript", "TypeScript", "React", "Redux", "Bootstrap", "Material UI");
        System.out.println(list1);
        //list1.add("Java");
        String s2 = list1.set(2, "Java");
        System.out.println(list1);
        System.out.println(s2);

        String[] str = {"HTML", "CSS", "JavaScript", "TypeScript", "React", "Redux", "Bootstrap", "Material UI"};
        List<String> list2 = Arrays.asList(str); // can't add, can't change size, affects on initial array
        System.out.println(list2);
        String s3 = list2.set(2, "Java");
        System.out.println(list2);
        System.out.println(s3);
        // original arr on which list is based is also changed
        System.out.println(Arrays.toString(str));

        //========================================
        List<String> list3 = List.of("HTML", "CSS", "JavaScript", "TypeScript", "React", "Redux", "Bootstrap", "Material UI");
        // list3.add("HTML");
        // String s4 = list3.set(2, "Java"); // Not allowed

        List<String> list4 = List.of(str); // can't add, can't change elements, can't change size, doesn't affect on initial array
        System.out.println(list4.get(3));

        str[0] = "HTML5";
        System.out.println(list2);
        System.out.println(list4);

        StringBuilder sb = new StringBuilder("HTML");
        List<StringBuilder> ll = List.of(sb);
        System.out.println(ll);
        sb.append(5);
        System.out.println(ll);
        //======================================================
        list4 = new ArrayList<>(list4); // return immutable list to mutable state
        list4.set(3, "Node.js");
        System.out.println(list4);

        System.out.println(sum(2, 5));
        System.out.println(sum(2, 5, 4));
        System.out.println(sum(2, 5, 4, 6, 8, 9));

    }

    public static int sum(int a, int b, int... rest) {
        int res = a + b;
        for (int i = 0; i < rest.length; i++) {
            res += rest[i];
        }
        return res;
    }
}
