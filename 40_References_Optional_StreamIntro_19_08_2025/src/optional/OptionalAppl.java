package optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalAppl {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("user1", 25),
                new User("user2", 28),
                new User("user3", 32),
                new User("user4", 27));

        User user1 = findUserByAge(users, 25);
        System.out.println(user1.getName());

        User user2 = findUserByAge(users, 29);
//        System.out.println(user2.getName()); // null exception
        Optional<User> op1 = findUserByAge_op(users, 66);
        Optional<User> op2 = findUserByAge_op(users, 25);
        System.out.println("======== isPresent ========");
        if(op1.isPresent()) {
            User user3 = op1.get();
            System.out.println(user3.getName());
        } else
            System.out.println("User not found");

        User user4 = op2.get();
        System.out.println(user4.getName());
        System.out.println("======== ifPresent ========");
        op1.ifPresent(u -> System.out.println(u.getName()));
        op2.ifPresent(u -> System.out.println(u.getName()));
        System.out.println("======== orElse ========");
        User user5 = op1.orElse(new User("user!!!", -1));
        System.out.println(user5.getName());
        User user6 = op2.orElse(new User("user!!!", -1));
        System.out.println(user6.getName());
        System.out.println("======== orElseGet ========");
        User user7 = op1.orElseGet(() -> new User("user!!!", -1));
        System.out.println(user7.getName());
        User user8 = op2.orElseGet(() -> new User("user!!!", -1));
        System.out.println(user8.getName());
        System.out.println("======== orElseThrow ========");
        User user9 = op1.orElseThrow(UserNowFoundException::new);
        System.out.println(user9.getName());

    }


    public static User findUserByAge(List<User> users, int age) {
        for (User u : users) {
            if (u.getAge() == age)
                return u;
        }
        return null;
    }
    public static Optional<User> findUserByAge_op(List<User> users, int age) {
        User us = null;
        for (User u : users) {
            if (u.getAge() == age){
                us = u;
                break;
            }
        }
        return Optional.ofNullable(us);
    }
}
