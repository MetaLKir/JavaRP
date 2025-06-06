package telran.wrapper.controller;

public class WrapperClassesAppl {
    public static void main(String[] args) {
        int x = 10;
        Integer y = 20;
        System.out.println(x);
        System.out.println(y);
        y = y + 5;
        System.out.println(y);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.BYTES);
        System.out.println(Long.MIN_VALUE);
        System.out.println(Byte.MAX_VALUE);
        String str1 = Integer.toString(x);
        System.out.println(str1);
        str1 = "100500";
        x = Integer.parseInt(str1);
        System.out.println(x);
        String str2 = "3.1415";
        double pi = Double.parseDouble(str2);
        System.out.println(pi);
        str1 = "tRuE";
        Boolean check = Boolean.parseBoolean(str1);
        System.out.println(check);

        double a = 0.0 / 0.0;
        if(Double.isNaN(a) || Double.isInfinite(a))
            System.out.println("Wrong result");
        else
            System.out.println(a);
    }
}
