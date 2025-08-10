package telran.citizens.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.citizens.dao.Citizens;
import telran.citizens.dao.CitizensImpl;
import telran.citizens.entity.Person;

class CitizensTest {
	Citizens citizens;
	static final LocalDate NOW = LocalDate.now();
	Person p1, p2, p3, p4, p5, p6, p7;

	@BeforeEach
	void setUp() {
		// add to list initially
		p1 = new Person(1, "FirstName1", "LastName1", NOW.minusYears(10));
		p2 = new Person(2, "FirstName2", "LastName2", NOW.minusYears(20));
		p3 = new Person(3, "FirstName3", "Smith", NOW.minusYears(20));
		p4 = new Person(4, "FirstName4", "Smith", NOW.minusYears(40));
		// for manipulations during tests
		p5 = new Person(5, "FirstName5", "LastName5", NOW.minusYears(50));
		p6 = new Person(6, "John", "Doe", NOW.minusYears(60));
		p7 = new Person(7, "John", "Doe", NOW.minusYears(50));

		citizens = new CitizensImpl(List.of(p1, p2, p3, p4));
		assertEquals(4, citizens.size());
	}

	// ========== AddNegative =========
	@Test
	void add_nullPerson() {
		citizens.add(null);
		assertEquals(4, citizens.size());
	}

	@Test
	void add_Duplicate() {
		assertFalse(citizens.add(p1));
		assertEquals(4, citizens.size());
	}

	// ========== AddPositive ==========
	@Test
	void add_Regular() {

		assertTrue(citizens.add(p5));
		assertEquals(5, citizens.size());
	}

	@Test
	void add_Duplicates() {
		assertTrue(citizens.add(p7));
		assertTrue(citizens.add(p5));
		assertTrue(citizens.add(p6));
		assertEquals(7, citizens.size());

		List<Person> sortedById = (List<Person>) citizens.getAllPersonsSortedById();
		Person[] sortedByIdExpected = { p1, p2, p3, p4, p5, p6, p7 };
		assertArrayEquals(sortedByIdExpected, sortedById.toArray());

		List<Person> sortedByLastName = (List<Person>) citizens.getAllPersonsSortedByLastName();
		Person[] sortedByLastNameExpected = { p6, p7, p1, p2, p5, p3, p4 };
		assertArrayEquals(sortedByLastNameExpected, sortedByLastName.toArray());

		List<Person> sortedByAge = (List<Person>) citizens.getAllPersonsSortedByAge();
		Person[] sortedByAgeExpected = { p1, p2, p3, p4, p5, p7, p6 };
		assertArrayEquals(sortedByAgeExpected, sortedByAge.toArray());
	}

	// ========= Remove ==========
	@Test
	void remove() {
		assertFalse(citizens.remove(-1));
		assertEquals(4, citizens.size());

		assertFalse(citizens.remove(666));
		assertEquals(4, citizens.size());

		assertTrue(citizens.remove(1));
		assertEquals(3, citizens.size());

		assertFalse(citizens.remove(1));
		assertEquals(3, citizens.size());

		assertNull(citizens.find(1));
	}

	// ========= Find ==========
	@Test
	void testFind() {
		Person p = citizens.find(1);
		assertNotNull(p);
		assertEquals(p1, p);

		assertNull(citizens.find(-1));
		assertNull(citizens.find(666));
	}

	@Test
	void testFindByAge_positive() {
		List<Person> pAge = (List<Person>) citizens.find(20, 50);
		Person[] pAgeExpected = { p2, p3, p4 };
		assertArrayEquals(pAgeExpected, pAge.toArray());
		assertEquals(3, pAge.size());

		citizens.add(p5);
		citizens.add(p6);
		citizens.add(p7);
		pAge = (List<Person>) citizens.find(20, 50);
		pAgeExpected = new Person[] { p2, p3, p4, p5, p7 };
		assertArrayEquals(pAgeExpected, pAge.toArray());
		assertEquals(5, pAge.size());

		pAge = (List<Person>) citizens.find(2, 15);
		pAgeExpected = new Person[] { p1 };
		assertEquals(1, pAge.size());
		assertArrayEquals(pAgeExpected, pAge.toArray());
	}

	@Test
	void testFindByAge_negative() {
		List<Person> personAge = (List<Person>) citizens.find(2, 8);
		assertTrue(personAge.isEmpty());

		Person p = new Person(10, "Baba", "Yaga", LocalDate.now());
		citizens.add(p);
		citizens.find(20, 30);
	}

	@Test
	void testFindByLastName_Positive() {
		List<Person> personsLastName = (List<Person>) citizens.find("Smith");
		List<Person> personsLastNameExpected = List.of(p3, p4);
		assertEquals(2, personsLastName.size());
		assertIterableEquals(personsLastNameExpected, personsLastName);

		citizens.add(p5);
		citizens.add(p6);
		citizens.add(p7);
		personsLastNameExpected = List.of(p6, p7);
		personsLastName = (List<Person>) citizens.find("Doe");
		assertEquals(2, personsLastName.size());
		assertIterableEquals(personsLastNameExpected, personsLastName);
	}

	@Test
	void testFindByLastName_Negative() {
		List<Person> personsLastName = (List<Person>) citizens.find("Jackson");
		System.out.println(personsLastName);
		assertTrue(personsLastName.isEmpty());

		personsLastName = (List<Person>) citizens.find(null);
		assertTrue(personsLastName.isEmpty());
	}

//	@Test
//	void getAllPersonsSortedById() {
//
//	}
//
//	@Test
//	void getAllPersonsSortedByAge() {
//	}
//
//	@Test
//	void getAllPersonsSortedByLastName() {
//	}
//
//	@Test
//	void size() {
//	}
}