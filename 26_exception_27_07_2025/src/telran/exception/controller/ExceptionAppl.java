package telran.exception.controller;

import telran.exception.model.AnyNumberException;
import telran.exception.model.InfinityException;

public class ExceptionAppl {
    public static void main(String[] args) {
        int a = 5;
        int b = 1;

        int x = 0;
        try {
            x = solution(a, b);
            System.out.println("result = " + x);
        } catch (AnyNumberException e) {
            System.out.println(e.getMessage());
        }catch (InfinityException e) {
            System.out.println(e);
        } finally {
            System.out.println("finish try/catch");
        }
        System.out.println("Bye, bye!");
    }

    private static int solution(int a, int b) throws AnyNumberException, InfinityException {
        if (a == 0 & b == 0) {
            throw new AnyNumberException("Solution any number");
        }
        if (a != 0 && b == 0) {
            throw new InfinityException("Infinity");
        }
        int res = a / b;
        return res;
    }
}
