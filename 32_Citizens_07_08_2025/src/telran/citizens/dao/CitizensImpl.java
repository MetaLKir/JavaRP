package telran.citizens.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import telran.citizens.entity.Person;

public class CitizensImpl implements Citizens {
	private static Comparator<Person> lastNameComparator;
	private static Comparator<Person> ageComparator;

	static {
		lastNameComparator = (p1, p2) -> {
			String s1 = p1.getLastName();
			String s2 = p2.getLastName();
			int res = s1 == null && s2 == null ? 0 : s1 == null ? 1 : s2 == null ? -1 : s1.compareTo(s2);
			return res != 0 ? res : Integer.compare(p1.getId(), p2.getId());
		};
		ageComparator = (p1, p2) -> {
			Integer age1 = p1.getBirthDate() == null ? null : p1.getAge();
			Integer age2 = p2.getBirthDate() == null ? null : p2.getAge();
			int res = age1 == null && age2 == null ? 0 : age1 == null ? 1 : age2 == null ? -1 : age1.compareTo(age2);

			return res != 0 ? res : Integer.compare(p1.getId(), p2.getId());
		};
	}

	private List<Person> idList;
	private List<Person> lastNameList;
	private List<Person> ageList;

	public CitizensImpl() {
		idList = new ArrayList<>();
		lastNameList = new ArrayList<>();
		ageList = new ArrayList<>();
	}

	public CitizensImpl(List<Person> citizens) {
		this();
		citizens.forEach(p -> add(p));
	}

	@Override
	public boolean add(Person person) {
		if (person == null)
			return false;
		int index = Collections.binarySearch(idList, person);
		if (index >= 0)
			return false;
		index = -index - 1;
		idList.add(index, person);

		index = Collections.binarySearch(lastNameList, person, lastNameComparator);
		if (index <= 0)
			index = -index - 1;
		lastNameList.add(index, person);

		index = Collections.binarySearch(ageList, person, ageComparator);
		if (index <= 0)
			index = -index - 1;
		ageList.add(index, person);

		return true;
	}

	@Override
	public boolean remove(int id) {
		Person personToRemove = find(id);
		if (personToRemove == null)
			return false;

		idList.remove(personToRemove);
		lastNameList.remove(personToRemove);
		ageList.remove(personToRemove);
		return true;
	}

	@Override
	public Person find(int id) {
		Person pattern = new Person(id, null, null, LocalDate.now());
		int index = Collections.binarySearch(idList, pattern);
		return index >= 0 ? idList.get(index) : null;
	}

	@Override
	public Iterable<Person> find(int minAge, int maxAge) {
		List<Person> res = new ArrayList<>();
		for (Person p : ageList) {
			if (p.getAge() >= minAge && p.getAge() <= maxAge)
				res.add(p);
		}
		return res;
	}

	@Override
	public Iterable<Person> find(String lastName) {
		Person p = new Person(Integer.MIN_VALUE, null, lastName, LocalDate.now());
		int from = -Collections.binarySearch(lastNameList, p, lastNameComparator) - 1;
		p = new Person(Integer.MAX_VALUE, null, lastName, LocalDate.now());
		int to = -Collections.binarySearch(lastNameList, p, lastNameComparator) - 1;
		return lastNameList.subList(from, to);
	}

	@Override
	public Iterable<Person> getAllPersonsSortedById() {
		return idList;
	}

	@Override
	public Iterable<Person> getAllPersonsSortedByAge() {
		return ageList;
	}

	@Override
	public Iterable<Person> getAllPersonsSortedByLastName() {
		return lastNameList;
	}

	@Override
	public int size() {
		return idList.size();
	}
}
