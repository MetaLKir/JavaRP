package reflection.pac2;

public class Z implements X {
    String zStr = "zStr";

    public Z(String zStr) {
        this.zStr = zStr;
    }

    @Override
    public void action() {
        System.out.println("action of class z: " + zStr);
    }
}
