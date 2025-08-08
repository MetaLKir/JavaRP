package telran.list.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.list.interfaces.IList;
import telran.list.model.MyLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    IList<String> stringList;
    IList<Integer> intList;

    @BeforeEach
    void setUp() {
        stringList = new MyLinkedList<>();
        intList = new MyLinkedList<>();
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
            //
        }
        try {
            stringList.add(6, "SSS");
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            //
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
            //
        }
        try {
            stringList.get(6);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            //
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
            //
        }
        try {
            stringList.remove(3);
            fail("Expected IndexOutOfBoundException");
        } catch (IndexOutOfBoundsException e) {
            //
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
            //
        }
        try {
            stringList.set(10, "GGG");
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            //
        }
    }
    //===============Integer===============
    @Test
    void testIntegerIteratorPositive() {
        Iterator<Integer> it = intList.iterator();
        assertTrue(it.hasNext());
        assertEquals(1, it.next());
        assertEquals(2, it.next());
        assertEquals(3, it.next());
        assertFalse(it.hasNext());
    }
    @Test
    void testIntegerIteratorNegative() {
        Iterator<Integer> it = intList.iterator();
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
    void testIntegerAddPositive() {
        assertTrue(intList.add(1, 5));
        assertEquals(4, intList.size());
        assertEquals(5, intList.get(1));
        assertEquals(2, intList.get(2)); // moved by 1 after adding XXX
    }

    @Test
    void testIntegerAddNegative() {
        try {
            intList.add(-1, 666);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            //
        }
        try {
            intList.add(6, 666);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            //
        }
    }

    @Test
    void testIntegerGetPositive() {
        assertEquals(2, intList.get(1));
        assertEquals(3, intList.get(2));
    }

    @Test
    void testIntegerGetNegative() {
        try {
            intList.get(-1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            //
        }
        try {
            intList.get(6);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            //
        }
    }

    @Test
    void testSIntegerSize() {
        assertEquals(3, intList.size());
        intList.add(666);
        assertEquals(4, intList.size());
        assertNotEquals(3, intList.size());
    }

    @Test
    void testIntegerIndexOfLastIndexOf() {
        assertEquals(1, intList.indexOf(2));
        assertEquals(1, intList.lastIndexOf(2));
        intList.add(2);
        assertEquals(1, intList.indexOf(2));
        assertEquals(3, intList.lastIndexOf(2));

        intList.add(null);
        assertEquals(4, intList.indexOf(null));
        assertEquals(4, intList.lastIndexOf(null));
        intList.add(8);
        assertEquals(3, intList.lastIndexOf(2));


        //======negative_scenario=====
        assertEquals(-1, intList.indexOf(666));
        assertEquals(-1, intList.lastIndexOf(666));
    }

    @Test
    void testIntegerRemoveByIndexPositive() {
        assertEquals((Integer) 2, intList.remove(1));
        assertEquals(2, intList.size());
        assertFalse(intList.contains(2));
    }
    @Test
    void testIntegerRemoveByIndexNegative() {
        try {
            stringList.remove(-1);
            fail("Expected IndexOutOfBoundException");
        } catch (IndexOutOfBoundsException e) {
            //
        }
        try {
            stringList.remove(3);
            fail("Expected IndexOutOfBoundException");
        } catch (IndexOutOfBoundsException e) {
            //
        }
    }
    @Test
    void testIntegerRemoveByObjectPositive() {
        assertTrue(intList.remove((Integer) 1)); // cast to make it object, otherwise will use removeByIndex
        assertEquals(2, intList.size());
        assertFalse(intList.contains(1));
    }
    @Test
    void testIntegerRemoveByObjectNegative() {
        assertFalse(intList.remove((Integer) 666));
    }

    @Test
    void testIntegerClear() {
        intList.clear();
        assertTrue(intList.isEmpty());
        assertEquals(0, intList.size());
    }

    @Test
    void testSIntegerSetPositive() {
        assertEquals(2, intList.set(1, 18));
        assertEquals(18, intList.get(1));
    }

    @Test
    void testIntegerSetNegative() {
        try {
            intList.set(-1, 666);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            //
        }
        try {
            intList.set(10, 666);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            //
        }
    }
}