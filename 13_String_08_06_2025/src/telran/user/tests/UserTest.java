package telran.user.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.user.model.User;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;
    String email = "peter@gmail.com";
    String password = "1234";

    @BeforeEach
    void setUp(){
        user = new User(email, password);
    }

    @Test
    void testCorrectEmail() {
        user.setEmail("peter@yahoo.com");
        assertEquals("peter@yahoo.com", user.getEmail());
    }

    @Test
    void testWithoutAt(){
        user.setEmail("peter.yahoo.com");
        assertEquals(email, user.getEmail());
    }

    @Test
    void testManyAt(){
        user.setEmail("peter@ya@hoo.com");
        assertEquals(email, user.getEmail());
    }

    @Test
    void testDotAfterAt(){
        user.setEmail("peter@yahoocom");
        assertEquals(email, user.getEmail());
    }

    @Test
    void testLastDot(){
        user.setEmail("peter@yahoo.com.");
        assertEquals(email, user.getEmail());
        user.setEmail("peter@yahoo.co.m");
        assertEquals(email, user.getEmail());
    }

    @Test
    void testIncorrectSymbol(){
        user.setEmail("pet!er@yahoo.com");
        assertEquals(email, user.getEmail());
    }

}
