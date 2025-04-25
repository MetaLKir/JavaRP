public class FunctionAppl {
    public static void main(String[] args){
        System.out.println("Hello, world!");
        iamprogrammer();
        System.out.println("Bye bye .!.");
    }

    public static void iamprogrammer(){
        System.out.println("I'm a programmer!");
        author();
    }

    private static void author() {
        System.out.println("John Smith");
        fn3();
    }

    private static void fn3() {
        System.out.println("FN3");
    }
}
