package telran.list;

import telran.list.model.MyLinkedList;

import java.util.Iterator;

public class MyLinkedListAppl {
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        System.out.println(list.size());
        list.add("AAA");
        System.out.println(list.size());
        list.add(1, "BBB");
        System.out.println(list.size());
        list.add("CCC");
        System.out.println(list.size());
        printList(list);

        list.add(1, "XXX");
        printList(list);

        String str = list.set(1,"ooo");
        printList(list);
        System.out.println(str);

        System.out.println(list.remove("AAA"));
        printList(list);

        list.clear();
        System.out.println(list.size());
        printList(list);
    }

    public static void printList(MyLinkedList<?> list){
        Iterator<?> it = list.iterator();
        while (it.hasNext()){
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }
}
