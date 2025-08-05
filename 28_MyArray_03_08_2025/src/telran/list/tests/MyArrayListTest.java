package telran.list.tests;

import org.junit.jupiter.api.*;

import telran.list.model.MyArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    MyArrayList<String> stringList;
    MyArrayList<Integer> intList;

    @BeforeEach
    void setUp() {
        stringList = new MyArrayList<>();
        intList = new MyArrayList<>();
        stringList.add("AAA");
        stringList.add("BBB");
        stringList.add("CCC");
        intList.add(1);
        intList.add(2);
        intList.add(3);
    }

    //===============String==================
    @Test
    void testStringIteratorPositive() {
        Iterator<String> it = stringList.iterator();
        assertTrue(it.hasNext());
        assertEquals("AAA", it.next());
        assertEquals("BBB", it.next());
        assertEquals("CCC", it.next());
        assertFalse(it.hasNext());
    }

    @Test
    void testStringIteratorNegative() {
        Iterator<String> it = stringList.iterator();
        while (it.hasNext()) {
            it.next();
        }
        try {
            it.next();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testStringAddPositive() {
        assertTrue(stringList.add(1, "XXX"));
        assertEquals(4, stringList.size());
        assertEquals("XXX", stringList.get(1));
        assertEquals("BBB", stringList.get(2)); // moved by 1 after adding XXX
    }

    @Test
    void testStringAddNegative() {
        try {
            stringList.add(-1, "SSS");
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            stringList.add(6, "SSS");
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
        }
    }


    @Test
    void testStringGetPositive() {
        assertEquals("BBB", stringList.get(1));
        assertEquals("CCC", stringList.get(2));
    }

    @Test
    void testStringGetNegative() {
        try {
            stringList.get(-1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            stringList.get(6);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
        }
    }

    @Test
    void testStringSize() {
        assertEquals(3, stringList.size());
        stringList.add("XXX");
        assertEquals(4, stringList.size());
        assertNotEquals(3, stringList.size());
    }

    @Test
    void testStringIndexOfLastIndexOf() {
        assertEquals(1, stringList.indexOf("BBB"));
        assertEquals(1, stringList.lastIndexOf("BBB"));
        stringList.add("BBB");
        assertEquals(1, stringList.indexOf("BBB"));
        assertEquals(3, stringList.lastIndexOf("BBB"));

        stringList.add(null);
        assertEquals(4, stringList.indexOf(null));
        assertEquals(4, stringList.lastIndexOf(null));
        stringList.add("CCC");
        assertEquals(3, stringList.lastIndexOf("BBB"));


        //======negative_scenario=====
        assertEquals(-1, stringList.indexOf("SSS"));
        assertEquals(-1, stringList.lastIndexOf("SSS"));
    }

    @Test
    void testStringRemoveByIndexPositive() {
        assertEquals("BBB", stringList.remove(1));
        assertEquals(2, stringList.size());
        assertFalse(stringList.contains("BBB"));
    }

    @Test
    void testStringRemoveByIndexNegative() {
        try {
            stringList.remove(-1);
            fail("Expected IndexOutOfBoundException");
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            stringList.remove(3);
            fail("Expected IndexOutOfBoundException");
        } catch (IndexOutOfBoundsException e) {
        }
    }

    @Test
    void testStringRemoveByObjectPositive() {
        assertTrue(stringList.remove("BBB"));
        assertEquals(2, stringList.size());
        assertFalse(stringList.contains("BBB"));
    }

    @Test
    void testStringRemoveByObjectNegative() {
        assertFalse(stringList.remove("!!!"));
    }

    @Test
    void testStringClear() {
        stringList.clear();
        assertTrue(stringList.isEmpty());
        assertEquals(0, stringList.size());
    }

    @Test
    void testStringSetPositive() {
        assertEquals("BBB", stringList.set(1, "GGG"));
        assertEquals("GGG", stringList.get(1));
    }

    @Test
    void testStringSetNegative() {
        try {
            stringList.set(-1, "GGG");
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            stringList.set(10, "GGG");
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
        }
    }
    //===============Integer===============
    @Test
    void testIntegerRemoveByIndexPositive() {
        assertEquals((Integer) 2, intList.remove(1));
        assertEquals(2, intList.size());
        assertFalse(intList.contains(2));
    }
    @Test
    void testIntegerRemoveByObjectPositive() {
        assertTrue(intList.remove((Integer) 1)); // cast to make it object, otherwise will use removeByIndex
        assertEquals(2, intList.size());
        assertFalse(intList.contains(1));
    }
}