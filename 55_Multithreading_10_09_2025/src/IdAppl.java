// .getId()     .threadId()
public class IdAppl extends Thread {

    @Override
    public void run() {
        System.out.println(getName() + " id (deprecated) = " + getId()); // .getId() deprecated
        System.out.println(getName() + " id (actual) = " + threadId());
    }

    public static void main(String[] args) {
        IdAppl i1 = new IdAppl();
        i1.start();
        IdAppl i2 = new IdAppl();
        i2 .start();
    }
}
