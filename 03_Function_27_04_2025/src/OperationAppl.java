// + - * / % ()
public class OperationAppl {
    public static void main(String[] args) {
        int x = 55;
        int y = 20;
        double z = x / (y * 1.); // 0.5
        System.out.println(z);
        z = x *3.3;
        System.out.println(z);
        x = (18 % 7);
        System.out.println(x);

        x = 10;
        boolean check = x == 10; // < > == != >= <=
        System.out.println(check);

        // ~0 => побитовая инверсия

        int age = 15;
        double vol = age >= 18 ? 2 : 42;
        System.out.println(vol);
    }
}
