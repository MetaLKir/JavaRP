package reflection.pac2;

public class Y implements X {
    String yStr = "yStr";

    public Y(String yStr) {
        this.yStr = yStr;
    }

    @Override
    public void action() {
        System.out.println("action of class y: " + yStr);
    }
}
