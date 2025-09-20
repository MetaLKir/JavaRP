package reflection.pac1;

public class Z implements X {
    String zStr = "zStr";

    @Override
    public void action() {
        System.out.println("action of class z: " + zStr);
    }
}
