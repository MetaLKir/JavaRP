// .setName(String) .getName()
public class Name extends Thread {
    @Override
    public void run() {
        System.out.println("running " + getName());
    }

    public static void main(String[] args) {
        Name n = new Name();
        Name n1 = new Name();

        System.out.println("Name n: " + n.getName());
        System.out.println("Name n1: " + n1.getName());

        n.setName("horse 1");
        System.out.println("Name n: " + n.getName());

        n.start();
        n1.start();
    }
}
