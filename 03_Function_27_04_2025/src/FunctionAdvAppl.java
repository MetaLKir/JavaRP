public class FunctionAdvAppl {
    public static void main(String[] args) {
        double res = pi();
        System.out.println(res);
        printPi(res);
        res = circleLength(3);
        System.out.println(res);
        res = max(50, 6);
//        res = abs(-50); // TODO homework, expects 50
//        res = min(50, 6); // TODO homework, expect 6
        System.out.println(res);
    }

    public static double max(int x, int y){
        return x > y ? x : y;

    }

    public static double circleLength(double radius){
        return 2 * pi() * radius;
    }

    public static void printPi(double pi){
        System.out.println(pi);
    }
    private static double pi() {
        return 3.1415926;
    }
}
