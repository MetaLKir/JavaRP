public class TypesAppl {
    public static void main(String[] args){
        byte bits = 20; // from -128 to 127
        System.out.println("byte: " + bits);

        short a = 32_767; // from -32_768 to 32_767
        System.out.println("short: " + a);

        int b = -2_147_483_648; // from - 2_147_483_648 to 2_147_483_647
        System.out.println("int: " + b);

        long l = 123_412_512_661_346_346L; // -9223372036854775808 to 9223372036854775807
        System.out.println("long: " + l);

        float f = 20.9f; // from 3.4e−038 to 3.4e+038
        System.out.println("float: " + f);

        double d = 125125.343; // from 1.7e-308 to 1.7e+308
        System.out.println("double: " + d);

        char c = '@';
        System.out.println("char: " + c);

        char c1 = 64;
        System.out.println("char1: " + c1);

        boolean flag = true; // true, false
        System.out.println("boolean: " + flag);


    }
}
