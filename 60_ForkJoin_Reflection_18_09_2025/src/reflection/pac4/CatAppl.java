package reflection.pac4;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

public class CatAppl {

    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.toString(Cat.class.getFields()));
        System.out.println(Arrays.toString(Cat.class.getDeclaredFields()));

        Field catField = Cat.class.getDeclaredField("age");
        System.out.println(catField);

        // how to get fields
        Cat cat = new Cat("cat1", 3, 666);
        catField.setAccessible(true);
        int age = catField.getInt(cat);
        System.out.println("Private age of cat: " + age);
        catField.set(cat, 1000);
        System.out.println(cat);


        // how to get constructors
        Constructor[] constructors = cat.getClass().getConstructors();
        System.out.println("Constructors amount: " + constructors.length);
        for (Constructor c : constructors) {
            Class[] paramTypes = c.getParameterTypes();
            for (Class p : paramTypes) {
                System.out.println(p.getName() + " ");
            }
        }
    }


}
