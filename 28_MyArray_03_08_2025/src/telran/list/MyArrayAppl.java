package telran.list;

import telran.list.model.MyArrayList;

import java.util.Arrays;

public class MyArrayAppl {

    public static void main(String[] args) {
        System.out.println("===== Test MyArrayList with String =====");
        testStrings();
        System.out.println("===== Test MyArrayList with Integer =====");
        testIntegers();
    }

    private static void testIntegers() {
    }

    private static void testStrings() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("AAA");
        list.add("BBB");
        list.add("CCC");
        printList(list);

        list.add(1, "SSS");
        printList(list);

        System.out.println(list.get(1));

        String str1 = list.set(1, "XXX");
        System.out.println(str1);
        printList(list);

        System.out.println(list.contains((str1)));
        System.out.println(list.contains(("AAA")));

        System.out.println(list.indexOf("XXX"));
        System.out.println(list.indexOf("X"));

        String s1 = list.remove(1);
        System.out.println(s1);
        printList(list);
        list.remove("BBB");
        printList(list);

        list.clear();
        printList(list);
    }

    private static void printList(MyArrayList<String> list) {
        System.out.println("List (" + list.size()+ ") " + toStr(list));
    }

    private static String toStr(MyArrayList<String> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
