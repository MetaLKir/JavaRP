package telran.citizens.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.citizens.dao.Citizens;
import telran.citizens.dao.CitizensImpl;
import telran.citizens.entity.Person;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CitizensTest {
    Citizens citizens = new CitizensImpl();
    Person personUniqueId;
    Person personDuplicatedId;

    @BeforeEach
    void setUp() {
        citizens.add(new Person(1, "Name1", "LastName2", LocalDate.of(2003, 1, 1)));
        citizens.add(new Person(2, "Name2", "LastName3", LocalDate.of(2001, 2, 2)));
        citizens.add(new Person(3, "Name3", "LastName1", LocalDate.of(2002, 3, 3)));

        personUniqueId = new Person(4, "Name4", "LastName4", LocalDate.of(2004, 4, 4));
        personDuplicatedId = new Person(2, "Name5", "LastName5", LocalDate.of(2005, 5, 5));
    }

    @Test
    void addPositive() {
        assertTrue(citizens.add(personUniqueId));
        assertEquals(4, citizens.size());
    }

    @Test
    void addNegative() {
        assertFalse(citizens.add(null));
        assertEquals(3, citizens.size());

        assertFalse(citizens.add(personDuplicatedId));
        assertEquals(3, citizens.size());
    }

    @Test
    void removePositive() {
        assertTrue(citizens.remove(2));
        assertEquals(2, citizens.size());
    }

    @Test
    void removeNegative() {
        assertFalse(citizens.remove(666));
        assertEquals(3, citizens.size());
    }

    @Test
    void findByIdPositive() {
        assertEquals(personDuplicatedId, citizens.find(2));
    }

    @Test
    void findByIdNegative() {
        assertNull(citizens.find(666));
        assertNotEquals(personDuplicatedId, citizens.find(1));
        assertNotEquals(personDuplicatedId, citizens.find(3));
    }

    //======================================================================
    @Test
    void testFind() {
    }

    @Test
    void testFind1() {
    }

    @Test
    void getAllPersonsSortedById() {
    }

    @Test
    void getAllPersonsSortedByAge() {
    }

    @Test
    void getAllPersonsSortedByLastName() {
    }

    @Test
    void size() {
    }
}