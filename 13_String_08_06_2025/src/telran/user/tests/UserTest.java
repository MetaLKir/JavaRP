package telran.user.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.user.model.User;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;
    String email = "peter@gmail.com";
    String password = "123456Ab!";

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

    @Test
    void testCorrectPassword() {
        user.setPassword("qwertY%8");
        assertEquals("qwertY%8", user.getPassword());
    }

    @Test
    void testPasswordLength() {
        user.setPassword("qertY%8");
        assertEquals(password, user.getPassword());
    }

    @Test
    void testPasswordDigits() {
        user.setPassword("qwertY%E");
        assertEquals(password, user.getPassword());
    }

    @Test
    void testPasswordSpecSymbols() {
        user.setPassword("qwertY_8");
        assertEquals(password, user.getPassword());
        user.setPassword("qwertYw8");
        assertEquals(password, user.getPassword());
    }

    @Test
    void testPasswordLowerCase() {
        user.setPassword("QWERTY%8");
        assertEquals(password, user.getPassword());
    }

    @Test
    void testPasswordUpperCase() {
        user.setPassword("qwerty%8");
        assertEquals(password, user.getPassword());
    }
}
