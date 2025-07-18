package telran.bitwise.controller;

public class BitwiseAppl {
    public static void main(String[] args) {
        System.out.println("==== bitwise and ====");
        int a = 5; // 0101
        int b = 3; // 0011
        int c = a & b; // AND; if both bits are 1, then 1; 0001
        System.out.println("AND bitwise: " + c);
        System.out.println(6 & 3); // 0110 & 0011 = 0010

        a = 15;
        b = 7;
        System.out.println(a & b); // 1111 & 0111 = 0111

        System.out.println("==== bitwise or ====");
        c = 5 | 3; // OR; if 1 in one of them, then 1
        System.out.println(c);
        System.out.println(8 | 7); // 1000 | 0111 = 1111
        System.out.println(15 | 2);

        System.out.println("==== bitwise not ====");
        byte g = 5; // 0101
        System.out.println(~g); // NOT;

        System.out.println("==== bitwise << ====");
        a = 5; // 0101
        System.out.println(a << 1); // move symbols to left by 1; it's like * 2
        System.out.println(a << 2); // move symbols to left by 2; it's like * 4

        System.out.println("==== bitwise >> ====");
        a = 11;
        System.out.println(a >> 1); // move symbols to right by 1; it's like / 2
        System.out.println(a >> 2); // move symbols to right by 2; it's like / 4

        System.out.println("==== bitwise >>> ====");
        int k = -1; // 1111 1111 -> 0111 1111; move to left without plus/minus sign
        System.out.println(k >>> 1);

        System.out.println("==== check is even number ====");
        int n = 10;
        System.out.println(n % 2 == 0);
        System.out.println((n & 1) == 0); // works faster
    }
}
