package telran.mystring.model;

import java.util.Iterator;

public class MyString implements Iterable<Character>{
    private StringBuilder str;

    public MyString(String str) {
        this.str = new StringBuilder(str);
    }

//    public StringBuilder getStr() {
//        return str;
//    }

    public void setStr(StringBuilder str) {
        this.str = str;
    }

    public void addChar(char c){
        str.append(c);
    }

    public void removeChar(char c){
        int index = str.indexOf(Character.toString(c));
        str.deleteCharAt(index);
    }

    @Override
    public String toString() {
        return str + "";
    }

    @Override
    public Iterator<Character> iterator() {
        return new MyStringIterator(str);
    }
}
