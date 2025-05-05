public class TaxIsraelAppl {

    public static void main(String[] args){
        double tax = calcTaxIsrael(4000, 2.25);
        System.out.println(tax);
    }

    public static double calcTaxIsrael(double salary, double taxAllowance){
        double res = -taxAllowance *219;
        if(salary < 7_010){
            res += salary * 0.1;
            return res >= 0 ? res : 0;
        }
        else{
            res = res + 7010 * 0.1;
        }
        if (salary >= 7010 && salary <= 10_060){
            res += (salary - 7010) * 0.14;
            return res >= 0 ? res : 0;
        }
        else{
            res = res + (10_060 - 7010) * 0.14;
        }



        return res;
    }
}
