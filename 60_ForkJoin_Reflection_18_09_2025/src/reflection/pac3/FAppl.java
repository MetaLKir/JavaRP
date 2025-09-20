package reflection.pac3;

import java.lang.reflect.Method;

public class FAppl {

    public static void main(String[] args) {
        // get all methods of class through reflection
//        for(Method m : F.class.getMethods()) {
//            System.out.println(m.getName());
//        }

        // get only declared methods of class through reflection
//        for(Method m : F.class.getDeclaredMethods()) {
//            System.out.println(m.getName());
//        }

        F objF = new F();
        for (int i = 0; i < args.length; i+= 2) {
            try {
                Method m = F.class.getDeclaredMethod(args[i], String.class);
                m.setAccessible(true); // gives access to private methods
                m.invoke(objF, args[i + 1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
