public class WhileLoopAppl {
    public static void main(String[] args) {
        //haharin();
        //printStars(25, 5);
//        double res = pow(2.2, 2);
//        System.out.println("Power result = " + res);
        int sumDig = sumDigits(6814729);
        System.out.println(sumDig);
    }

    public static void haharin(){
        int counter = 10;
        while(counter > 0){
            System.out.println(counter);
            counter--;
        }
        System.out.println("Poehali");
    }

    public static void printStars(int n, int k){
        int i = 1;
        while (i <= n){
            if(i % k != 0)
                System.out.print('*');
            else
                System.out.println('*');
            i++;
        }
    }

    public static double pow(double x, double n){
        double res = 1;
        while (n > 0){
            res *= x;
            n--;
        }
        return res;
    }

    public static int sumDigits(int num){
        int res = 0;
        while(num != 0){
            int digit = num % 10;
            num /= 10;
            res += digit;
        }
        return res;
    }
}
