public class Main {
    public static void main(String[] args) {
        System.out.println("main start");
        func6();
        func1();
        func2();
    }

    static void func1(){
        System.out.println("func1");
        func4();
    }

    static void func2(){
        System.out.println("func2");
        func6();
    }
    // func3 don't be called
    static void func3(){
        System.out.println("func3");
    }

    static void func4(){
        System.out.println("func4");
        func5();
    }

    static void func5(){
        System.out.println("func5");
        func6();
    }
    // func6 don't call other functions
    static void func6(){
        System.out.println("func6");
    }
}
