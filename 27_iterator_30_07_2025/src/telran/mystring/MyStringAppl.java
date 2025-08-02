package telran.mystring;

import telran.mystring.model.MyString;

import java.util.Iterator;

public class MyStringAppl {
    public static void main(String[] args) {
        MyString myString = new MyString("Mama myla ramu");
        System.out.println(myString);
        myString.addChar('.');
        myString.addChar('.');
        myString.addChar('.');
        System.out.println(myString);

        System.out.println("=== Iterator ===");
        // print to upper case
        for (Character character : myString){
            System.out.print(Character.toUpperCase(character));
        }
        System.out.println();


        // how to remove 'a'
        Iterator<Character> iter = myString.iterator();
        while (iter.hasNext()){
            Character c = iter.next();
            if (c == 'a'){
                iter.remove();
            }
        }
        // BAD VERSION, NOT WORK
//        for (Character character : myString){
//            if (character == 'a')
//                myString.removeChar(character);
//        }

        // print without 'm'
        for (Character character : myString){
            if (character != 'm')
                System.out.print(character);
        }
        System.out.println();


        Iterator<Character> iterator = myString.iterator();
        while (iterator.hasNext()){
            Character c = iterator.next();
            if (c != 'a')
                System.out.print(c);
        }
        System.out.println();

//        StringBuilder stringBuilder = myString.getStr();
//        for (int i = 0; i < stringBuilder.length(); i++) {
//            System.out.print(Character.toUpperCase((stringBuilder.charAt(i))));
//
//        }
//        System.out.println();
//        stringBuilder.setLength(4);
//        System.out.println(stringBuilder);
//        System.out.println(myString);
    }
}
