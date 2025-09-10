public class Cat implements Runnable {
    private String name;
    private char gender;
    private int meow;

    public Cat(String name, char gender, int meow) {
        this.name = name;
        this.gender = gender;
        this.meow = meow;
    }

    @Override
    public void run() {
        for (int i = 0; i < meow; i++) {
            if(gender == 'm')
                System.out.println("cat " + name + " says meow");
            else
                System.out.println("catty " + name + " says myawu");
        }
    }

    public static void main(String[] args) {
        Cat c1 = new Cat("Cat1", 'm', 10);
        Cat c2 = new Cat("Cat2", 'j', 15);
        Cat c3 = new Cat("Cat3", 'm', 12);
        Cat c4 = new Cat("Cat4", 'g', 16);
//        c1.run();
//        c2.run();
//        c3.run();
//        c4.run();
        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        Thread t3 = new Thread(c3);
        Thread t4 = new Thread(c4);
        Thread[] threads = {t1, t2, t3, t4};
        for (Thread t : threads) t.start();
    }
}
