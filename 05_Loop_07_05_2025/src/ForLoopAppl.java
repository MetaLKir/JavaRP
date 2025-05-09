public class ForLoopAppl {
    public static void main(String[] args) {
        //gagarin();
        //printStars(25,5);
        double res = pow(2,10);
        System.out.println(res);
        int num = sumDigits(12345);
        System.out.println(num);

    }

    public static void gagarin(){
        for (int i = 10; i > 0; i--){
            System.out.println(i);
        }
        System.out.println("Poehali");
    }

    public static void printStars(int n, int k){
        for (int i = 1; i <= n; i++){
            if(i % k != 0)
                System.out.print('*');
            else
                System.out.println('*');
        }
    }

    public static double pow(double x, double n){
        double res = 1;
        for(; n > 0; n--){
            res *= x;
        }
        return res;
    }

    public static int sumDigits(int num){
        int res = 0;
        for(int digit; num != 0; res += digit, num /= 10){
            digit = num % 10;
        }
        return res;
    }
}


