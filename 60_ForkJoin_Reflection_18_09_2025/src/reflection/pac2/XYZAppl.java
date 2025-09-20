package reflection.pac2;

public class XYZAppl {
    static final String PACKAGE = "reflection.pac2.";

    public static void main(String[] args) {

        for (int i = 0; i < args.length; i += 2) {
            try {
                Class<X> clazz = (Class<X>) Class.forName(PACKAGE + args[i]);
                X x = clazz.getConstructor(String.class).newInstance(args[i + 1]);
                x.action();
            } catch (Exception e) {
                System.out.println("Errorchik: " + e.getMessage());
            }
        }
    }
}
