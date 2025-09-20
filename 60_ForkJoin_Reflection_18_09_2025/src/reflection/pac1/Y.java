package reflection.pac1;

public class Y implements X{
    String yStr = "yStr";

    @Override
    public void action() {
        System.out.println("action of class y: " + yStr);
    }
}
