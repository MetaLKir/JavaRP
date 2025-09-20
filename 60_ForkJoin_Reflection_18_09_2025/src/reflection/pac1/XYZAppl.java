package reflection.pac1;

public class XYZAppl {

    public static void main(String[] args) throws ClassNotFoundException {
        X x1 = new Y();
        X x2 = new Z();
        X x3 = new Y();
        X x4 = new Z();

        // ways to get class
        Class classX = x4.getClass();
        System.out.println(classX);
        Class classX1 = Y.class;
        System.out.println(classX1);
        Class classX2 = Class.forName("reflection.pac1.Y");
        System.out.println(classX2);
        Class wrapper = Integer.TYPE;
        System.out.println(wrapper);
    }
}
