public class CircusAppl {
    public static void main(String[] args) {
        lightOn();
        entertainers();
        lightOff();
    }

    private static void lightOff() {
        System.out.println("Light off");
    }

    private static void entertainers() {
        juggler();
        singer();
        clown();
    }

    private static void clown() {
        System.out.println("My name is Crusty");
        System.out.println("I'm a clown");
    }

    private static void singer() {
        System.out.println("My name is Philippo");
        System.out.println("I'm a singer");
    }

    private static void juggler() {
        System.out.println("My name is John Smith");
        System.out.println("I'm a juggler");
    }

    private static void lightOn(){
        System.out.println("Light on");
    }
}
